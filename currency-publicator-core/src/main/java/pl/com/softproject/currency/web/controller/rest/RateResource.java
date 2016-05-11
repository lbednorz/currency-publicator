package pl.com.softproject.currency.web.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.service.RateHistoryService;
import pl.com.softproject.currency.service.RateService;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main Rest Controller for curriences manipulations
 */
@RestController
@RequestMapping(RateResource.URL)
public class RateResource {

    private final Logger log = LoggerFactory.getLogger(RateResource.class);

    public static final String URL = "/api/currencies";

    public static final String GET_CURRENT = "/current";
    public static final String GET_HISTORY_OF_CURRIENCIES = "/hist/{code}";
    public static final String GET_CHANGE = "/change/{oryg}/{target}/{value}";
    public static final String GET_CURRIENCES = "/list";
    public static final String GET_INITIAL_IMPORT = "/import";
    @Autowired
    private RateService rateService;
    @Autowired
    private RateHistoryService rateHistoryService;

    @RequestMapping(value = RateResource.GET_CURRENT,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RateDto>> getCurrent() {
        try {
            return new ResponseEntity<>(rateService.getAllCurrentRates(), HttpStatus.OK);
        } catch (LoadRatesException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = RateResource.GET_HISTORY_OF_CURRIENCIES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RateDto>> getHistory(@PathVariable(value = "code") String code) {
        try {
            return new ResponseEntity<>(rateHistoryService.getCurrencyLastMonthHistoryRates(code), HttpStatus.OK);
        } catch (IncorrectCurrencyCodeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = RateResource.GET_CHANGE,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> getChange(@PathVariable(value = "oryg") String oryg,
                                                @PathVariable(value = "target") String target,
                                                @PathVariable(value = "value") BigDecimal value) {
        try {

            return new ResponseEntity<>(rateService.convertValue(oryg, target, value), HttpStatus.OK);
        } catch (LoadRatesException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BigDecimal(0), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IncorrectCurrencyCodeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BigDecimal(0), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = RateResource.GET_CURRIENCES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> allCurriencies( ) {
        try {
            return new ResponseEntity<>(rateService.getCurriences(), HttpStatus.OK);
        } catch (LoadRatesException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new HashMap<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = RateResource.GET_INITIAL_IMPORT,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getInitialImport() {
        try {
            rateHistoryService.updateHistory();
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (LoadRatesException e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

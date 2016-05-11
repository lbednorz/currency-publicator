package pl.com.softproject.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.softproject.currency.assembler.RateDtoAssembler;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.services.CurrentRateService;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;
import pl.com.softproject.currency.service.helper.CurrencyConverter;
import pl.com.softproject.currency.service.helper.CurrencyHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Leszek Bednorz
 */
@Service
@Transactional
public class RateServiceImpl implements RateService {


    @Autowired
    private CurrentRateService currentRateService;

    @Autowired
    private RateDtoAssembler rateAssembler;

    @Autowired
    private CurrencyConverter currencyConverter;

    @Autowired
    private CurrencyHelper currencyHelper;


    /**
     * Return all current currency rate based on USD
     * @return
     */
    @Override
    public List<RateDto> getAllCurrentRates() throws LoadRatesException {
        return rateAssembler.assemby(currentRateService.getCurrentRates());

    }

    /**
     * Return single current currency
     * @return
     */
    public RateDto getCurrentRate(String currencyCode) throws LoadRatesException, IncorrectCurrencyCodeException {
       return currencyHelper.findCurrencyRate(getAllCurrentRates(), currencyCode);
    }

    /**
     * Return single current currency from base currency code
     * @return
     */
    public RateDto getSingleCurrentRateInBase(String currencyCode, String toCurrencyCode) throws LoadRatesException, IncorrectCurrencyCodeException {
        return currencyConverter.convertSingleRateByBaseRate(getCurrentRate(toCurrencyCode), getCurrentRate(currencyCode));
    }


    /**
     * Convert values from one currecy to second currency based on current values
     * @param currencyCodeFrom
     * @param currencyCodeTo
     * @param value
     * @return
     */
    @Override
    public BigDecimal convertValue(String currencyCodeFrom, String currencyCodeTo, BigDecimal value) throws LoadRatesException, IncorrectCurrencyCodeException {
        return currencyConverter.covertSingleValue(getAllCurrentRates(),currencyCodeFrom, currencyCodeTo, value );

    }
    /**
     * Return map of available curriences code and description
     * @return
     */
    @Override
    public Map<String, String> getCurriences()  throws LoadRatesException{
        return currentRateService.getCurriences();
    }

}

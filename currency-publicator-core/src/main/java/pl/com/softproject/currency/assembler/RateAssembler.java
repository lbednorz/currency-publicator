package pl.com.softproject.currency.assembler;

import org.springframework.stereotype.Service;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.model.SingleRateDTO;
import pl.com.softproject.currency.model.Rate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class RateAssembler
 * Assemby ImportDTO (element with rates) to standard list of rates
 * @author Leszek Bednorz
 */
@Service
public class RateAssembler implements Serializable {


    public List<Rate> assemby(ImportDTO importDTO){
        List<Rate> rates = new ArrayList<>();

        for(SingleRateDTO singleRate : importDTO.getRates()){
            Rate rate = new Rate();
            rate.setCurrency(singleRate.getCountryCode());
            rate.setImportDate(importDTO.getImportDateTime());
            rate.setValue(singleRate.getRate());
            rates.add(rate);
        }
        return rates;
    }
}

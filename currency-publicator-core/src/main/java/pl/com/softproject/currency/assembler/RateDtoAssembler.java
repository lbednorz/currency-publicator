package pl.com.softproject.currency.assembler;

import org.springframework.stereotype.Service;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.model.SingleRateDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class RateDtoAssembler
 * Assemby ImportDTO (element with rates) to standard list of rates DTO elements
 * @author Leszek Bednorz
 */
@Service
public class RateDtoAssembler implements Serializable {


    public List<RateDto> assemby(ImportDTO importDTO){
        List<RateDto> rates = new ArrayList<>();

        for(SingleRateDTO singleRate : importDTO.getRates()){
            RateDto rateDto = new RateDto();
            rateDto.setCountryCode(singleRate.getCountryCode());
            rateDto.setImportDate(importDTO.getImportDateTime());
            rateDto.setValue(singleRate.getRate());
            rates.add(rateDto);
        }
        return rates;
    }
}

package pl.com.softproject.currency.service.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Helper class for converting currency
 * @author Leszek  Bednorz
 */
@Service
public class CurrencyConverterImpl implements CurrencyConverter{

    Logger log = Logger.getLogger(CurrencyConverterImpl.class);

    @Autowired
    private CurrencyHelper currencyHelper;


    @Override
    public BigDecimal covertSingleValue(List<RateDto> currentRates, String convertFrom, String convertTo, BigDecimal valueToConvert)  throws IncorrectCurrencyCodeException {
        RateDto currentCurrencyCodeFromRateInUsd = currencyHelper.findCurrencyRate(currentRates, convertFrom);
        RateDto currentCurrencyCodeToRateInUsd =  currencyHelper.findCurrencyRate(currentRates, convertTo);

        BigDecimal one = new BigDecimal(1);
        BigDecimal dividedFrom = one.divide(currentCurrencyCodeFromRateInUsd.getValue(),  10, RoundingMode.HALF_UP);
        BigDecimal valueFrom = dividedFrom.multiply(valueToConvert);

        return  currentCurrencyCodeToRateInUsd.getValue().multiply(valueFrom) ;

    }





    public RateDto convertSingleRateByBaseRate(RateDto baseRateValue, RateDto rate) {
        if(baseRateValue.getCountryCode().equals(rate.getCountryCode())) return rate;
        BigDecimal one = new BigDecimal(1);
        BigDecimal divided = one.divide(rate.getValue(),  10, RoundingMode.HALF_UP);
        BigDecimal converted  = divided.multiply(baseRateValue.getValue());
        rate.setValue(converted);
        return rate;
    }



}

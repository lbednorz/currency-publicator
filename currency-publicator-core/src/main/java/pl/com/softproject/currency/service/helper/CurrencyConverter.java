package pl.com.softproject.currency.service.helper;

import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class CurrencyConverter
 * Interface form detaulf currency conversion API
 * @author Leszek  Bednorz
 */
public interface CurrencyConverter {

    public RateDto convertSingleRateByBaseRate(RateDto baseRateValue, RateDto rate);

    public BigDecimal covertSingleValue(List<RateDto> currentRates, String convertFrom, String convertTo, BigDecimal valueToConvert)  throws IncorrectCurrencyCodeException;
}

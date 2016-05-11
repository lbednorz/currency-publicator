package pl.com.softproject.currency.service.helper;

import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.util.List;

/**
 * Helper inrerface for operation on currencies list
 *
 * @author Leszek  Bednorz
 */
public interface CurrencyHelper {

    public RateDto findCurrencyRate(List<RateDto> rates, String currencyCode) throws IncorrectCurrencyCodeException;
}


package pl.com.softproject.currency.service.helper;

import org.springframework.stereotype.Service;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class for operation on currencies list
 */
@Service
public class CurrencyHelperImpl  implements  CurrencyHelper{

    public RateDto findCurrencyRate(List<RateDto> rates, String currencyCode)  throws IncorrectCurrencyCodeException {
        List<RateDto> filteredRates =  rates.stream().filter(p -> p.getCountryCode().equals(currencyCode)).collect(Collectors.toList());
        if(filteredRates == null || filteredRates.size() == 0) throw new IncorrectCurrencyCodeException("cannot find currency code in rates");
        return filteredRates.get(0);
    }
}


package pl.com.softproject.currency.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.model.SingleRateDTO;
import pl.com.softproject.currency.integration.sources.openexchangerates.OpenExchangeRatesSource;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class CurrentRateServiceImpl
 *
 */
@Service
public class CurrentRateServiceImpl implements CurrentRateService {

    @Autowired
    private OpenExchangeRatesSource openExchangeRatesSource;

    @Override
    public ImportDTO getCurrentRates() throws LoadRatesException {
        return openExchangeRatesSource.getCurrentRates();
    }

    @Override
    public BigDecimal getCurrentRateByCurrency(String currencyCode) throws LoadRatesException {
        ImportDTO dto = getCurrentRates();
        for (SingleRateDTO rate: dto.getRates()){
             if(rate.getCountryCode().equals(currencyCode.toUpperCase())){
                 return rate.getRate();
             }
        }
        return null;
    }
    @Override
    public Map<String, String> getCurriences() throws LoadRatesException {
        return openExchangeRatesSource.getCurriences();
    }

    public OpenExchangeRatesSource getOpenExchangeRatesSource() {
        return openExchangeRatesSource;
    }

    public void setOpenExchangeRatesSource(OpenExchangeRatesSource openExchangeRatesSource) {
        this.openExchangeRatesSource = openExchangeRatesSource;
    }


}

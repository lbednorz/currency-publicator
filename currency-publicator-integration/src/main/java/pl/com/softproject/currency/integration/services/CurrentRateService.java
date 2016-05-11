package pl.com.softproject.currency.integration.services;

import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class CurrentRateService
 *

 */
public interface  CurrentRateService  {

    public ImportDTO getCurrentRates() throws LoadRatesException;

    public BigDecimal getCurrentRateByCurrency(String currencyCode) throws LoadRatesException ;

    public Map<String, String> getCurriences() throws LoadRatesException;
}

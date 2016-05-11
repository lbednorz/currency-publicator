package pl.com.softproject.currency.service;

import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Main Service API for currency operation.
 *
 * @author Leszek Bednorz
 */
public interface RateService {

    /**
     * Return all current currency rate based on USD
     * @return
     */
    public List<RateDto> getAllCurrentRates() throws LoadRatesException;



    /**
     * Return single current currency
     * @return
     */
    public RateDto getCurrentRate(String currencyCode) throws LoadRatesException, IncorrectCurrencyCodeException;


    /**
     * Convert values from one currecy to second currency based on current values
     * @param currencyCodeFrom
     * @param currencyCodeTo
     * @param value
     * @return
     */
    public BigDecimal convertValue(String currencyCodeFrom, String currencyCodeTo, BigDecimal value) throws LoadRatesException, IncorrectCurrencyCodeException;

    /**
     * Return map of available curriences code and description
     * @return
     */
    public Map<String, String> getCurriences()   throws LoadRatesException;

}

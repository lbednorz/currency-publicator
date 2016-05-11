package pl.com.softproject.currency.service;

import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.util.List;

/**
 * Main Service API for currency rates history
 *
 * @author Leszek Bednorz
 */
public interface RateHistoryService {

    /**
     * Load last month of currency changes based on currencyCode
     * @return
     */
    public List<RateDto> getCurrencyLastMonthHistoryRates(String currencyCode) throws IncorrectCurrencyCodeException;


    /**
     * Load and store initial history data
     */
    public void updateHistory() throws LoadRatesException;

}

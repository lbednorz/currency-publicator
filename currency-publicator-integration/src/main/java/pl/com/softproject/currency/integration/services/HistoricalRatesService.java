package pl.com.softproject.currency.integration.services;

import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;

import java.util.Date;

/**
 * Class HistoricalRatesService
 *
 */
public interface HistoricalRatesService {


    public ImportDTO getHistoryRates(Date dateToLoad) throws LoadRatesException;

}

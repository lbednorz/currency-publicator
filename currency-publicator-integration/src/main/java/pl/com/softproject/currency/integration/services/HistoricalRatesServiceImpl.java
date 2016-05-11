package pl.com.softproject.currency.integration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.openexchangerates.OpenExchangeRatesSource;

import java.util.Date;

/**
 * Class HistoricalRatesService
 *
 */
@Service
public class HistoricalRatesServiceImpl  implements  HistoricalRatesService{


    @Autowired
    private OpenExchangeRatesSource openExchangeRatesSource;


    @Override
    public ImportDTO getHistoryRates(Date dateToLoad) throws LoadRatesException{
        return openExchangeRatesSource.getHirstoricalRates(dateToLoad);
    }



}

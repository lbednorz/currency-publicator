package pl.com.softproject.currency.integration.sources;

import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;

import java.util.Date;
import java.util.Map;

/**
 * Interface Source
 *
 * Definition of currencies data source API
 *
 * @author Leszek Bednorz
 */
public interface Source {


    /**
     * Load current rates from outside server
     * @return
     */
    public ImportDTO getCurrentRates() throws LoadRatesException;

    /**
     * Load historical rates from outside server
     * by date
     * @return
     */
    public ImportDTO getHirstoricalRates(Date date ) throws LoadRatesException;

    public Map<String, String> getCurriences() throws LoadRatesException ;


}

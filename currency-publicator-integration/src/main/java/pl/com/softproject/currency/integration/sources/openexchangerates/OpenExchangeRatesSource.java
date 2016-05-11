package pl.com.softproject.currency.integration.sources.openexchangerates;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.softproject.currency.integration.sources.Source;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.openexchangerates.assembler.ImportDataAssembler;
import pl.com.softproject.currency.integration.sources.openexchangerates.model.ImportOpenExchangeDTO;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Implementation of source interface for OpenExchangeRates service
 *
 * @author Leszek Bednorz
 */
@Service
public class OpenExchangeRatesSource implements Source {

    Logger logger = Logger.getLogger(OpenExchangeRatesSource.class);

    private String apiKey = "3fe6da37fd194db59b162e2cdbf96fa8";
    private String latestUrl = "https://openexchangerates.org/api/latest.json?app_id=";
    private String historicalUrl = "https://openexchangerates.org/api/historical/%s.json?app_id=";
    private String curriencesUrl = "https://openexchangerates.org/api/currencies.json";

    private SimpleDateFormat defaultHistoriicalUrlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ImportDataAssembler importDataAssembler;




    @Override
    public ImportDTO getCurrentRates() throws LoadRatesException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ImportOpenExchangeDTO loadedData =  mapper.readValue(new URL(latestUrl+apiKey), ImportOpenExchangeDTO.class);
            if(logger.isDebugEnabled()) {
                logger.debug("current rates data loaded: "+loadedData.getTimestamp());
            }
            return importDataAssembler.assemby(loadedData);

        }catch(Exception e){
           throw new LoadRatesException(e.getMessage());
        }
    }

    @Override
    public ImportDTO getHirstoricalRates(Date date) throws LoadRatesException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            if(logger.isDebugEnabled()) {
                logger.debug(prepareHistoricalUrl(date));
            }
            ImportOpenExchangeDTO loadedData = mapper.readValue(new URL(prepareHistoricalUrl(date)), ImportOpenExchangeDTO.class);
            loadedData.setTimestamp(date.getTime());
            if(logger.isDebugEnabled()) {
                logger.debug("current rates data loaded: "+loadedData.getTimestamp());
            }
            return importDataAssembler.assemby(loadedData);

        }catch(Exception e){
            throw new LoadRatesException(e.getMessage());
        }

    }

    public Map<String, String> getCurriences() throws LoadRatesException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            HashMap<String, String> loadedData = mapper.readValue(new URL(curriencesUrl), HashMap.class);
           for(String key :loadedData.keySet()){
               System.out.println(key+" 0 "+loadedData.get(key));
           }

            return loadedData;
        }catch(Exception e){
            throw new LoadRatesException(e.getMessage());
        }
    }

    /**
     * Creating proper historical url
     * sample:
     * https://openexchangerates.org/api/historical/2016-03-02.json?app_id=
     * @param date
     * @return
     */
    private String prepareHistoricalUrl(Date date) {
        return String.format(historicalUrl,defaultHistoriicalUrlDateFormat.format(date) )+apiKey;
    }






    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


}

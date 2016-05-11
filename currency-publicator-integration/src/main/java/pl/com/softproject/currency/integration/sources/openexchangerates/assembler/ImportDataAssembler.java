package pl.com.softproject.currency.integration.sources.openexchangerates.assembler;

import org.springframework.stereotype.Service;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.model.SingleRateDTO;
import pl.com.softproject.currency.integration.sources.openexchangerates.model.ImportOpenExchangeDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class ImportDataAssembler
 * Converting rate import data from OpenExchange specific format to standard app DTO format
 * @author Leszek Bednorz
 */
@Service
public class ImportDataAssembler  {

    public ImportDTO assemby(ImportOpenExchangeDTO sourceDTO){
        ImportDTO importDTO = new ImportDTO();
        importDTO.setBaseCountryCode(sourceDTO.getBase());
        importDTO.setDisclaimer(sourceDTO.getDisclaimer());
        importDTO.setImportDateTime(new Date(sourceDTO.getTimestamp()));
        importDTO.setLicense(sourceDTO.getLicense());
        for(String key : sourceDTO.getRates().keySet()  ){
            SingleRateDTO rate = new SingleRateDTO();
            rate.setCountryCode(key);
            rate.setRate(new BigDecimal(sourceDTO.getRates().get(key)));
            importDTO.getRates().add(rate);
        }
        return importDTO;
    }

}

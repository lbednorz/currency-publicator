package pl.com.softproject.currency.integration.sources.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class ImportOpenExchangeDTO
 *
 * Single import of rates
 * @author Leszek Bednorz
 */
public class ImportDTO implements Serializable {


    private String disclaimer;
    private String license;
    private String baseCountryCode;
    private Date importDateTime;
    private List<SingleRateDTO> rates = new ArrayList<>();

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getBaseCountryCode() {
        return baseCountryCode;
    }

    public void setBaseCountryCode(String baseCountryCode) {
        this.baseCountryCode = baseCountryCode;
    }

    public Date getImportDateTime() {
        return importDateTime;
    }

    public void setImportDateTime(Date importDateTime) {
        this.importDateTime = importDateTime;
    }

    public List<SingleRateDTO> getRates() {
        return rates;
    }

    public void setRates(List<SingleRateDTO> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ImportDTO{" +
                "disclaimer='" + disclaimer + '\'' +
                ", license='" + license + '\'' +
                ", baseCountryCode='" + baseCountryCode + '\'' +
                ", importDateTime=" + importDateTime +
                ", rates=" + rates +
                '}';
    }
}

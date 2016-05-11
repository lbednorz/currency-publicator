package pl.com.softproject.currency.integration.sources.openexchangerates.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Class ImportOpenExchangeDTO
 *
 * Single import of rates
 * @author Leszek Bednorz
 */
public class ImportOpenExchangeDTO implements Serializable {


    private String disclaimer;
    private String license;
    private String base;
    private Long timestamp;
    private Map<String,String>  rates;

    @Override
    public String toString() {
        return "ImportOpenExchangeDTO{" +
                "disclaimer='" + disclaimer + '\'' +
                ", license='" + license + '\'' +
                ", base='" + base + '\'' +
                ", timestamp=" + timestamp +
                ", rates=" + rates +
                '}';
    }

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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }
}

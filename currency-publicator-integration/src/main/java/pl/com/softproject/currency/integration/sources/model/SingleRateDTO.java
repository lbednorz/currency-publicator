package pl.com.softproject.currency.integration.sources.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class SingleRateOpenExchangeDTO
 *
 * Single currency rate
 * @author Leszek Bednorz
 */
public class SingleRateDTO implements Serializable {

    private String countryCode;
    private BigDecimal rate;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal   rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "SingleRateDTO{" +
                "countryCode='" + countryCode + '\'' +
                ", rate=" + rate +
                '}';
    }
}

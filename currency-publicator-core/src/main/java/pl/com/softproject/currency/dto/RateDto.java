package pl.com.softproject.currency.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Detault DTO version of Rate entity.
 * @author Leszek Bednorz
 */
public class RateDto {

    private Long id;
    private String countryCode;
    private Date importDate;
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

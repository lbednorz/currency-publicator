package pl.com.softproject.currency.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Main entity for storing historical currency rates data
 * @author Leszek Bednorz
 */
@Entity
@Table(name = "rate")
@org.hibernate.annotations.GenericGenerator(name = "test-increment-strategy", strategy = "increment")
public class Rate {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "test-increment-strategy")
    private Long id;
    private String currency;
    private Date importDate;
    @Column(precision = 17, scale = 7, columnDefinition = "DECIMAL(17,7)")
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

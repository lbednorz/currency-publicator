package pl.com.softproject.currency.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rate.class)
public abstract class Rate_ {

	public static volatile SingularAttribute<Rate, Date> importDate;
	public static volatile SingularAttribute<Rate, String> currency;
	public static volatile SingularAttribute<Rate, Long> id;
	public static volatile SingularAttribute<Rate, BigDecimal> value;

}


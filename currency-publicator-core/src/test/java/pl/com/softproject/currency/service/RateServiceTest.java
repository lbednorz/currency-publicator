package pl.com.softproject.currency.service;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class RateServiceTest
 */
@RunWith(JUnit4.class)
public class RateServiceTest {

    static RateService rateService;

    static Logger logger = Logger.getLogger(RateServiceTest.class);


    @BeforeClass
    public static void oneTimeSetUp() {
        logger.info("loading spring test context ");
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring-config-db-test.xml"});

        rateService = (RateService)context.getBean("rateServiceImpl");
    }

    @Test
    public void testCurrentRatesLoadedProper() {

        try {
            List<RateDto> rates = rateService.getAllCurrentRates();
            assertNotNull(rates);
            logger.info("loaded rates: "+rates.size());
            assertTrue(rates.size() > 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void testCurrentSigneRateProper() {
        try {
            RateDto rate = rateService.getCurrentRate("PLN");
            assertNotNull(rate);
            logger.info("loaded rates: "+rate.getValue());
            assertTrue(rate.getCountryCode().equals("PLN"));
        }catch(Exception e){
            assertTrue("Throw exception", false);
        }
    }

    @Test
    public void testCurrentSigneRateException() {
        try {
            rateService.getCurrentRate("PLNNN");
            assertTrue("shoud throw exception", false);
        }catch(IncorrectCurrencyCodeException e){
            logger.info("Test ok, proper exception throwed");
        }catch(LoadRatesException le){
            assertTrue("shoud throw IncorrectCurrencyCodeException", false);
        }
    }



    @Test
    public void getConvertValue() {
        try {
            BigDecimal convertedValue =  rateService.convertValue("PLN", "ALL", new BigDecimal(12));
            logger.info("convertedValue: "+convertedValue);
            assertNotNull(convertedValue);
        }catch(Exception e){
            assertTrue("Throw exception", false);
        }
    }

    @Test
    public void getConvertValueWrongCurrency() {
        try {
            rateService.convertValue("PLN", "ALLB", new BigDecimal(12));
            assertTrue("shoud throw exception", false);
        }catch(IncorrectCurrencyCodeException e){
            logger.info("Test ok, proper exception throwed");
        }catch(LoadRatesException le){
            assertTrue("shoud throw IncorrectCurrencyCodeException", false);
        }
    }
}

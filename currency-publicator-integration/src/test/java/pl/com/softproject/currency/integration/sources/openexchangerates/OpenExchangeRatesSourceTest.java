package pl.com.softproject.currency.integration.sources.openexchangerates;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class OpenExchangeRatesSourceTest
 *
 */
@RunWith(JUnit4.class)
public class OpenExchangeRatesSourceTest {

    static OpenExchangeRatesSource source;

    Logger logger = Logger.getLogger(OpenExchangeRatesSourceTest.class);

    SimpleDateFormat paramFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeClass
    public static void oneTimeSetUp() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring-config-test.xml"});
        source = (OpenExchangeRatesSource)context.getBean("openExchangeRatesSource");
    }


    @Test
    public void testCurrentRatesLoadedProper() {
        try {
            ImportDTO results = source.getCurrentRates();
            logger.info(results);
            assertNotNull(results);
            assertNotNull(results.getRates());
            assertTrue(results.getRates().size() > 0 );
        }catch(LoadRatesException lrs){
            assertTrue(lrs.getMessage(), false);
        }
    }

    @Test
    public void testHistoricalRatesLoadedProper() {
        try {
            Date paramDate = paramFormatter.parse("2016-04-24");

            ImportDTO results = source.getHirstoricalRates(paramDate);
            logger.info(results);
            assertNotNull(results);
            assertNotNull(results.getRates());
            assertTrue(results.getRates().size() > 0 );
        }catch(Exception lrs){
            assertTrue(lrs.getMessage(), false);
        }
    }

    @Test
    public void testHistoricalRatesLoadedusingNull() {
        try {
            source.getHirstoricalRates(null);
            assertTrue("shoud throw exception before this line", false );
        }catch(LoadRatesException lrs){
            assertTrue(lrs.getMessage(), true);
        }
    }

    @Test
    public void getLoadData() {
        try {
            source.getCurriences();

        }catch(LoadRatesException lrs){
            assertTrue(lrs.getMessage(), false);
        }
    }
}

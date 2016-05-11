package pl.com.softproject.currency.integration.sources.openexchangerates;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.currency.integration.services.HistoricalRatesService;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class OpenExchangeRatesSourceTest
 *
 */
@RunWith(JUnit4.class)
public class HistoricalRateServiceTest {

    static HistoricalRatesService historicalRatesService;


    static Logger logger = Logger.getLogger(OpenExchangeRatesSourceTest.class);
    @BeforeClass
    public static void oneTimeSetUp() {
        logger.info("loading spring test context ");
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring-config-test.xml"});

        historicalRatesService = (HistoricalRatesService)context.getBean("historicalRatesServiceImpl");
    }

    @Test
    public void testCurrentRatesLoadedProper() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ImportDTO results = historicalRatesService.getHistoryRates((sdf).parse("2016-01-05"));
            logger.info(results);
            assertNotNull(results);
            assertNotNull(results.getRates());
            assertTrue(results.getRates().size() > 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

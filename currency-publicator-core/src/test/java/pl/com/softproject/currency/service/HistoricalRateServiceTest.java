package pl.com.softproject.currency.service;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.currency.dto.RateDto;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Class RateServiceTest
 */
@RunWith(JUnit4.class)
public class HistoricalRateServiceTest {

    static RateHistoryService rateHistoryService;

    static Logger logger = Logger.getLogger(HistoricalRateServiceTest.class);


    @BeforeClass
    public static void oneTimeSetUp() {
        logger.info("loading spring test context ");
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring-config-db-test.xml"});

//        for (String name: context.getBeanDefinitionNames()    ) {
//            System.out.println(name);
//        }
        rateHistoryService = (RateHistoryService)context.getBean("rateHistoryServiceImpl");

    }


    @Test
    public void getConvertValueWrongCurrency() {
        try {
            rateHistoryService.updateHistory();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<RateDto> rates = rateHistoryService.getCurrencyLastMonthHistoryRates("PLN");
            logger.info("rate loaded: "+rates.size());
            for (RateDto rate: rates){
                logger.info(rate.getCountryCode()+": "+sdf.format(rate.getImportDate())+" - "+rate.getValue() );
            }

        }catch(Exception e){
            e.printStackTrace();
            assertTrue("shoud throw IncorrectCurrencyCodeException", false);
        }
    }
}

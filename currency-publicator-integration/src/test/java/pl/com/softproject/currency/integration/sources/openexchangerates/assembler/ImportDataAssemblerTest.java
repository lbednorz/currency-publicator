package pl.com.softproject.currency.integration.sources.openexchangerates.assembler;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.integration.sources.openexchangerates.OpenExchangeRatesSourceTest;
import pl.com.softproject.currency.integration.sources.openexchangerates.model.ImportOpenExchangeDTO;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class ImportDataAssemblerTest
 *
 */
@RunWith(JUnit4.class)
public class ImportDataAssemblerTest  {

    Logger logger = Logger.getLogger(OpenExchangeRatesSourceTest.class);

//    ImportDataAssembler importDataAssembler = new ImportDataAssembler();


    String base = "PL";
    String disclamer = "test";
    String licence = "licence";
    Date dateToTest = new Date();
    long timestamp = dateToTest.getTime();
    String rateCountry = "PL";
    String rateValue = "3";

    @Test
    public void testAssemblerProper() {

            ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"spring-config-test.xml"});
            ImportDataAssembler importDataAssembler = (ImportDataAssembler)context.getBean("importDataAssembler");


            ImportOpenExchangeDTO dto = prepareImportOpenExchangeDTOSample();

            ImportDTO results = importDataAssembler.assemby(dto);
            logger.info(results);
            assertNotNull(results);
            assertNotNull(results.getRates());
            assertTrue(results.getRates().size() == 1 );
            assertTrue(results.getBaseCountryCode().equals(base));
            assertTrue(results.getLicense().equals(licence));
            assertTrue(results.getDisclaimer().equals(disclamer));
            assertTrue(results.getImportDateTime().getTime() == timestamp);
    }

    private ImportOpenExchangeDTO prepareImportOpenExchangeDTOSample() {
        ImportOpenExchangeDTO dto = new ImportOpenExchangeDTO();
        dto.setBase(base);
        dto.setDisclaimer(disclamer);
        dto.setLicense(licence);
        dto.setTimestamp(timestamp);
        dto.setRates(new HashMap<>());
        dto.getRates().put(rateCountry, rateValue);
        return dto;
    }

}

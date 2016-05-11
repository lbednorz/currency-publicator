package pl.com.softproject.currency.service;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.softproject.currency.assembler.RateAssembler;
import pl.com.softproject.currency.assembler.RateToDtoAssembler;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.integration.services.HistoricalRatesService;
import pl.com.softproject.currency.integration.sources.exceptions.LoadRatesException;
import pl.com.softproject.currency.integration.sources.model.ImportDTO;
import pl.com.softproject.currency.model.Rate;
import pl.com.softproject.currency.repository.RateRepository;
import pl.com.softproject.currency.service.exception.IncorrectCurrencyCodeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Main Service API for currency rates history
 *
 * @author Leszek Bednorz
 */
@Service
public class RateHistoryServiceImpl  implements RateHistoryService{

    Logger logger = Logger.getLogger(RateHistoryServiceImpl.class);

    @Autowired
    RateRepository rateRepository;

    @Autowired
    HistoricalRatesService historicalRatesService;

    @Autowired
    RateAssembler rateAssembler;

    @Autowired
    RateToDtoAssembler rateToDtoAssembler;

    /**
     * Load last month of currency changes based on currencyCode
     * @return
     */
    public List<RateDto> getCurrencyLastMonthHistoryRates(String currencyCode) throws IncorrectCurrencyCodeException{

        LocalDate startData = LocalDate.now().minusDays(31);
        LocalDate endData = LocalDate.now().minusDays(1);
        return rateToDtoAssembler.assemblyToDto(rateRepository.getRatesByCodeAndBetweenDates(startData.toDate(), endData.toDate(),currencyCode ));
    }


    /**
     * Load and store initial history data.
     * Only works, when database is empty
     */
    @Transactional(readOnly = false)
    public void updateHistory() throws LoadRatesException {
        if(!isRatesDbEmpty()) return;

        List<Date> last30Days =  generateLast30DaysDates();
        for (Date date : last30Days) {
            logger.debug("Loadign data for day "+date );
            ImportDTO importData = historicalRatesService.getHistoryRates(date);
            storeImportData(importData);
        }


    }

    @Transactional(readOnly = false)
    private void storeImportData(ImportDTO importDTO){
        List<Rate> rates = rateAssembler.assemby(importDTO);
        for (Rate rate:rates) {
            logger.debug("Savind rate : "+rate.getCurrency() + " : "+rate.getValue()+":"+ rate.getImportDate() );
            rateRepository.save(rate);
        }
    }




    public RateRepository getRateRepository() {
        return rateRepository;
    }

    public void setRateRepository(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public HistoricalRatesService getHistoricalRatesService() {
        return historicalRatesService;
    }

    public void setHistoricalRatesService(HistoricalRatesService historicalRatesService) {
        this.historicalRatesService = historicalRatesService;
    }

    private List<Date> generateLast30DaysDates() {
        logger.debug("Start generateLast30DaysDates");
        List<Date> last30DaysDates = new ArrayList<>();
        DateTime  now = DateTime.now();
        for(int i = 30; i >=1; i--){
            DateTime minusDate = now.minusDays(i);
            last30DaysDates.add(minusDate.toDate());
        }
        logger.debug(" generateLast30DaysDates generated");

        return last30DaysDates;
    }

    private boolean isRatesDbEmpty(){
        List<Rate> rates = rateRepository.getAllInversed();
        if(rates != null && !rates.isEmpty()) return false;
        return true;
    }
}

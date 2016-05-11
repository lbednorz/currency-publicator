package pl.com.softproject.currency.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.com.softproject.currency.model.Rate;

/**
 * Spring Data interface for Rate manipulation
 * @author Leszek Bedorz
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT a FROM Rate a  order by a.id desc ")
    public List<Rate> getAllInversed();

    @Query("select r from Rate r " +
            "where r.importDate between ?1 and ?2 and r.currency = ?3 ")
    public List<Rate> getRatesByCodeAndBetweenDates(Date beginDate, Date endDate, String coutryCode);

}

package pl.com.softproject.currency.assembler;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.com.softproject.currency.dto.RateDto;
import pl.com.softproject.currency.model.Rate;

import java.util.ArrayList;
import java.util.List;

/**
 * Converting standatd DTO to entity -  both ways
 * @author Leszek Bednorz
 */
@Component
public class RateToDtoAssembler implements Assembler<Rate, RateDto> {

    private static final String[] EXCLUDED_FIELDS = new String[]{};

    @Override
    public Rate assemblyToDbo(final RateDto dto) {
        Preconditions.checkArgument(dto != null, "DTO must not be null");
        Rate dbo = new Rate();
        dbo.setCurrency(dto.getCountryCode());
        dbo.setImportDate(dto.getImportDate());
        dbo.setValue(dto.getValue());
        dbo.setId(dto.getId());
        return dbo;
    }

    @Override
    public RateDto assemblyToDto(final Rate dbo) {
        Preconditions.checkArgument(dbo != null, "DBO must not be null");
        RateDto dto = new RateDto();
        dto.setCountryCode(dbo.getCurrency());
        dto.setImportDate(dbo.getImportDate());
        dto.setValue(dbo.getValue());
        dto.setId(dbo.getId());
        return dto;
    }

    @Override
    public List<RateDto> assemblyToDto(final List<Rate> dbo) {
        List<RateDto> dtos = new ArrayList<>();
        for (Rate rateDbo : dbo) {
            dtos.add(assemblyToDto(rateDbo));
        }
        return dtos;
    }

    @Override
    public List<Rate> assemblyToDbo(final List<RateDto> dto) {
        List<Rate> dbos = new ArrayList<>();
        for (RateDto rateDto : dto) {
            dbos.add(assemblyToDbo(rateDto));
        }
        return dbos;
    }



}

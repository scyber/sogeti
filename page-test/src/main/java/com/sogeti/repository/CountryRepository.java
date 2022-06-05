package com.sogeti.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sogeti.domain.CountryDto;
import com.sogeti.resources.CountryResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements TestRepository<CountryDto>{
    private static final List<CountryDto> countriesList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepository.class);

    @Override
    public List<CountryDto> getList() {
        try {
            processCountryDto();
        } catch (Exception e) {
            LOGGER.error("Getting error process repository ", e);
        }
        return countriesList;
    }
    private static void processCountryDto() throws IOException {
        Resource resource = new CountryResourceImpl().getResource();
        Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()));
        final ColumnPositionMappingStrategy<CountryDto> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(CountryDto.class);
        strategy.setColumnMapping("countryShort", "postalCode", "cityName");
        CsvToBean<CountryDto> countries = new CsvToBeanBuilder<CountryDto>(reader)
                .withSeparator(',')
                .withMappingStrategy(strategy)
                .build();
        countries.forEach(c -> countriesList.add(c));
    }
}

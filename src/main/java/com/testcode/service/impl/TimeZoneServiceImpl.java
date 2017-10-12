package com.testcode.service.impl;

import com.testcode.dao.TimeZonesRepository;
import com.testcode.domain.TimeZone;
import com.testcode.service.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.List;

@Service
public class TimeZoneServiceImpl implements TimeZoneService {
    @Autowired
    private TimeZonesRepository timeZonesRepository;

    @Override
    public List<TimeZone> loadAll() {
        return timeZonesRepository.findAll();
    }

    @Override
    public void createCSVFile() throws Exception {
        //position of the file could be a better one!
        ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter("src/main/resources/file.csv"),
                CsvPreference.STANDARD_PREFERENCE);
        CellProcessor[] processors = new CellProcessor[]{
                new NotNull(), // abbreviation
                new NotNull(), // country
                new NotNull()  // UTCOffset
        };
        //Headers have to match the sam as in TimeZone object
        String[] header = {"countryAbbreviation", "countryName", "UTCOffset"};
        beanWriter.writeHeader(header);

        for (TimeZone aBook : timeZonesRepository.findAll()) {
            beanWriter.write(aBook, header, processors);
        }
        beanWriter.flush();
        beanWriter.close();
    }
}


package com.testcode.service.impl;

import com.testcode.dao.TimeZonesRepository;
import com.testcode.domain.TimeZone;
import com.testcode.service.WebFetchService;
import com.testcode.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class WebFetchServiceImpl implements WebFetchService {

    @Autowired
    private TimeZonesRepository timeZonesRepository;

    @Override
    public List<TimeZone> getTimeZonesFromWeb() throws IOException {
        WebUtils.getDocument();
        try (BufferedReader br = new BufferedReader(new FileReader(WebUtils.DOWNLOAD_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            //Skip three lines since the input is not clean
            line = br.readLine();
            while (line != null) {
                String[] newElement = line.split("\\t");
                if (newElement.length < 3) {
                    line = br.readLine();
                    continue;
                }
                TimeZone timeZone = new TimeZone();
                timeZone.setCountryAbbreviation(newElement[0]);
                timeZone.setCountryName(newElement[1]);
                timeZone.setUTCOffset(newElement[2]);
                timeZonesRepository.save(timeZone);
                line = br.readLine();
            }
        }
        return timeZonesRepository.findAll();
    }
}

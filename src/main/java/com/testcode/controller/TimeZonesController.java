package com.testcode.controller;

import com.testcode.domain.TimeZone;
import com.testcode.service.TimeZoneService;
import com.testcode.service.WebFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class TimeZonesController {
    private static final Logger logger = LoggerFactory.getLogger(TimeZonesController.class);
    private static final String filePath = "src/main/resources/file.csv";

    @Autowired
    private TimeZoneService timeZoneService;

    @Autowired
    private WebFetchService webFetchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage() {
        return "index";
    }

    /**
     * This methods serves the frontend with a list of TimeZones.
     * @return List of TimeZone
     * @throws IOException
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody List<TimeZone> loadAll() throws IOException {
        List<TimeZone> result = timeZoneService.loadAll();
        if (result.isEmpty()) {
            result = webFetchService.getTimeZonesFromWeb();
        }
        return result;
    }

    /**
     * Endpoint for downloading the csv file with time zones.
     * @return csv file into the stream
     * @throws Exception
     */
    @RequestMapping(value = "/downloadCsv", method = RequestMethod.GET)
    public ResponseEntity<Resource> download() throws Exception {
        List<TimeZone> result = timeZoneService.loadAll();
        if (result.isEmpty()) {
            webFetchService.getTimeZonesFromWeb();
        }
        timeZoneService.createCSVFile();

        File file = new File(filePath);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(filePath)));
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }
}
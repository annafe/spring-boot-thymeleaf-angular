package com.testcode.service;

import com.testcode.domain.TimeZone;

import java.util.List;

public interface TimeZoneService {
    /**
     * Simple method to get all the entries into the database
     * @return
     */
    List<TimeZone> loadAll();

    /**
     * This method create a CSV file with the entries from the database.
     * @throws Exception
     */
    void createCSVFile() throws Exception;
}

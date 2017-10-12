package com.testcode.service;

import com.testcode.domain.TimeZone;

import java.io.IOException;
import java.util.List;

public interface WebFetchService {
    /**
     * With this method we get the time zones from the web address and
     * we put all the results into the Database. In the end we return the
     * database content
     *
     * @return List of Time Zones
     * @throws IOException
     */
    List<TimeZone> getTimeZonesFromWeb() throws IOException;
}

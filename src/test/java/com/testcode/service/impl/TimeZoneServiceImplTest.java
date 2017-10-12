package com.testcode.service.impl;

import com.testcode.dao.TimeZonesRepository;
import com.testcode.domain.TimeZone;
import com.testcode.service.TimeZoneService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class TimeZoneServiceImplTest {

    @TestConfiguration
    static class TimeZoneServiceImplTestContextConfiguration {

        @Bean
        public TimeZoneService timeZoneService() {
            return new TimeZoneServiceImpl();
        }
    }

    @Autowired
    private TimeZoneService timeZoneService;

    @MockBean
    private TimeZonesRepository timeZonesRepository;

    @Before
    public void setUp() {
        TimeZone codeExample = new TimeZone("Anna", "AN", "UTC +1");
        Mockito.when(timeZonesRepository.findAll())
                .thenReturn(Arrays.asList(codeExample));
    }

    @Test
    public void shouldReturnListOfTimeZones() {
        List<TimeZone> found = timeZoneService.loadAll();
        Assert.assertEquals("Anna", found.get(0).getCountryName());
    }
}

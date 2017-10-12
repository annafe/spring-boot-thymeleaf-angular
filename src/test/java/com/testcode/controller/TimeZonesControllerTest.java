package com.testcode.controller;

import com.testcode.domain.TimeZone;
import com.testcode.service.TimeZoneService;
import com.testcode.service.WebFetchService;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(TimeZonesController.class)
public class TimeZonesControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TimeZoneService timeZoneService;

    @MockBean
    private WebFetchService webFetchService;

    @Test
    public void shouldReturnJsonArray() throws Exception {

        TimeZone codeExample = new TimeZone("Anna", "AN", "UTC +1");
        List<TimeZone> allTimeZones = Arrays.asList(codeExample);
        BDDMockito.given(timeZoneService.loadAll()).willReturn(allTimeZones);

        mvc.perform(get("/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("$[0].countryName", Matchers.is(codeExample.getCountryName())));
                //.andExpect(jsonPath("$[2].UTCOffset", Matchers.is(codeExample.getUTCOffset())));
        }
}

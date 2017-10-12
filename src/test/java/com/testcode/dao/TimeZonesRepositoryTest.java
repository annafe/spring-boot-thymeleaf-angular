package com.testcode.dao;

import com.testcode.domain.TimeZone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TimeZonesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TimeZonesRepository timeZonesRepository;

    @Test
    public void shouldInsertAndRetrieveAnObjectCorrectly() {
        // setup
        TimeZone codeExample = new TimeZone();
        codeExample.setCountryAbbreviation("AN");
        codeExample.setCountryName("Anna");
        TimeZone codeExample2 = new TimeZone();
        codeExample2.setCountryAbbreviation("FE");
        codeExample2.setCountryName("Feola");
        entityManager.persist(codeExample);
        entityManager.persist(codeExample2);
        entityManager.flush();

        List<TimeZone> found = timeZonesRepository.findAll();
        Assert.assertEquals(2, found.size());
        Assert.assertEquals(codeExample.getCountryAbbreviation(), found.get(0).getCountryAbbreviation());
    }
}

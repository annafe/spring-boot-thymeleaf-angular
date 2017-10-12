package com.testcode.domain;

import javax.persistence.*;

@Entity
@Table(name = "time_zones")
public class TimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_abbreviation")
    private String countryAbbreviation;
    @Column(name = "UTC_offset")
    private String UTCOffset;

    public TimeZone() {
    }

    public TimeZone(String countryName, String countryAbbreviation, String UTCOffset) {
        this.countryName = countryName;
        this.countryAbbreviation = countryAbbreviation;
        this.UTCOffset = UTCOffset;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryAbbreviation() {
        return countryAbbreviation;
    }

    public void setCountryAbbreviation(String coutryAbbreviation) {
        this.countryAbbreviation = coutryAbbreviation;
    }

    public String getUTCOffset() {
        return UTCOffset;
    }

    public void setUTCOffset(String UTCOffset) {
        this.UTCOffset = UTCOffset;
    }
}
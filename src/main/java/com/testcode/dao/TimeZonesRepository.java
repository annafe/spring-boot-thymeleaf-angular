package com.testcode.dao;

import com.testcode.domain.TimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeZonesRepository extends JpaRepository<TimeZone, Long> {
}

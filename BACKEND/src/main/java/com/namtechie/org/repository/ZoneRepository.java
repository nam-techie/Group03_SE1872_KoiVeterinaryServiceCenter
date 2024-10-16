package com.namtechie.org.repository;

import com.namtechie.org.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByName(String name);

    boolean existsByName(String name);

    List<Zone> findAll();
}

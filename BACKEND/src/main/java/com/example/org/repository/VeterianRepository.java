package com.example.org.repository;

import com.example.org.entity.Veterian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeterianRepository extends JpaRepository<Veterian, Long> {

    // Chưa hoàn thiện
    public List<Veterian> findAll();
    Veterian findById(long id);
}

package com.upc.Hato.repository;

import com.upc.Hato.model.Serving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServingRepository extends JpaRepository<Serving, Long> {
    List<Serving> findByServingType(String servingType);
}

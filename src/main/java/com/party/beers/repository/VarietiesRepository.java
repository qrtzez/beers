package com.party.beers.repository;

import com.party.beers.model.VarietiesBeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietiesRepository extends JpaRepository<VarietiesBeer, Integer> {
    VarietiesBeer findByName(String name);
}

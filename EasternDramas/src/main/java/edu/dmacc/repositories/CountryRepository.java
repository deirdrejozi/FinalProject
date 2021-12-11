package edu.dmacc.repositories;

import edu.dmacc.entities.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Integer> {
    List<Country> findAll();
}

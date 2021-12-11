package edu.dmacc.repositories;

import edu.dmacc.entities.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, Integer> {
    List<Language> findAll();
}

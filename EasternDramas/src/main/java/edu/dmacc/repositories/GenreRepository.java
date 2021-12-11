package edu.dmacc.repositories;

import edu.dmacc.entities.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    List<Genre> findAll();
}

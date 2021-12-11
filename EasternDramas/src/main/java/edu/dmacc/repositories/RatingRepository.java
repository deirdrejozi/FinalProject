package edu.dmacc.repositories;

import edu.dmacc.entities.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Integer> {
    List<Rating> findAll();
}

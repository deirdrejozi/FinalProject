package edu.dmacc.repositories;

import edu.dmacc.entities.Drama;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DramaRepository extends PagingAndSortingRepository<Drama, Integer> {
    List<Drama> findAll( Sort sort);
}

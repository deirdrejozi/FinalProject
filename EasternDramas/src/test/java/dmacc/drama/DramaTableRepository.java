package edu.dmacc.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DramaTableRepository extends PagingAndSortingRepository<DramaTable, Integer> {

    @Query(value =
        "SELECT d.dramaId, d.dramaTitle, d.numOfReviews, d.revStars, d.releaseYear, d.primaryLang, d.subtitlesLang, " +
            "d.country, d.genre, d.rating, l.languageName as primaryLangName, s.languageName as subtitlesLangName, " +
            "c.countryName, g.genreName, r.rate as ratingName " +
            "FROM Drama d " +
            "LEFT JOIN Language l ON d.primaryLang " +
            "LEFT JOIN Language s ON d.subtitlesLang " +
            "LEFT JOIN Country c  ON d.country " +
            "LEFT JOIN Genre g    ON d.genre " +
            "LEFT JOIN Rating r   ON d.rating"
        )
    List<DramaTable> findAll( Sort sort );
}

package edu.dmacc.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Pamela D. Jozi
 * Date: 12/10/2021
 * Time: 11:59
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="Drama")
public class Drama {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dramaId;
    private String dramaTitle;
    private int numOfReviews;
    private BigDecimal revStars;
    private int releaseYear;
    private boolean subtitled;

/*
    private Integer primaryLang;
    private Integer subtitlesLang;

    private Integer country;
    private Integer genre;
    private Integer rating;
*/

    private String primaryLanguageName;
    private String countryName;
    private String genreName;
    private String ratingRate;

}

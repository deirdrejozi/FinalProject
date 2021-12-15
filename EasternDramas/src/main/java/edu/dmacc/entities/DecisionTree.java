package dmacc.drama.entities;

import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/***************************************************************
*Name:				Eastern Dramas : Final project
*Author:			Pamela Jozi
*Created:			12/10/2021
*Course:			CIS 152 - Data Structure
*version:			1.0
*OS: 				Windows 10
*Copyright:			This is my own original work based on specifications issued by our instructor
* Description:		This program overall description here 
* Input:			list and describe
* Output:			list and describe
* Academic Honesty:	I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified. I have not given other fellow student(s) access
* to my program.        
***************************************************************/

@Data
public class DecisionTree {
    private Root root;

    public DecisionTree(String[] countries, String[] languages, String[] subtitles, String[] genres, String[][] ratings) {
        root = new Root();
        for (String country : countries) {
            add(new Country(country));
        }
        for (String language : languages) {
            add(new Language(language));
        }
        for (String subtitle : subtitles) {
            add(new Subtitles(subtitle));
        }
        for (String genre : genres) {
            add(new Genre(genre));
        }
        for (String[] rating : ratings) {
            add(new Rating(rating[0], rating[1]));
        }
        try (Scanner dramaFile = new Scanner(new File("Dramalist.txt"))) {
            dramaFile.nextLine();   // skip headings
            while (dramaFile.hasNextLine()) {
// -----------+--------------+---------+------------+---------+----------------+------------+----------+-----------
//      0     |      1       |    2    |     3      |   4     |      5         |      6     |    7     |     8
// drama_title|num_of_reviews|rvw_stars|release_year|subtitled|primary_language|country_name|genre_name|rating_rate
// -----------+--------------+---------+------------+---------+----------------+------------+----------+-----------
                String[] line = dramaFile.nextLine().split("\\|");
                addDrama(new Drama(line[0], Integer.parseInt(line[1]), Double.parseDouble(line[2]),
                    Integer.parseInt(line[3]), line[4], line[5], line[6], line[7], line[8]));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public Root getRoot() {
        return root;
    }

    public Drama[] search( int type, String key) {
        List<Drama> dramas = new ArrayList<>();
        List<List<Node>> rootChildren = root.getChildren();
        for ( Node sibling : rootChildren.get(type)) {
            switch (sibling.getType()) {
                case COUNTRY: {
                    Drama[] dramaArray = (( Country ) sibling).getDramas().toArray(new Drama[0]);
                    for ( Drama drama : dramaArray) {
                        if (drama.getCountry().equalsIgnoreCase(key)) dramas.add(drama);
                    }
                    break;
                }
                case LANGUAGE: {
                    Drama[] dramaArray = ((Language) sibling).getDramas().toArray(new Drama[0]);
                    for ( Drama drama : dramaArray) {
                        if (drama.getLanguage().equalsIgnoreCase(key)) dramas.add(drama);
                    }
                    break;
                }
                case SUBTITLES: {
                    Drama[] dramaArray = (( Subtitles ) sibling).getDramas().toArray(new Drama[0]);
                    for ( Drama drama : dramaArray) {
                        if (drama.getSubtitles().equalsIgnoreCase(key)) dramas.add(drama);
                    }
                    break;
                }
                case GENRE: {
                    Drama[] dramaArray = (( Genre ) sibling).getDramas().toArray(new Drama[0]);
                    for ( Drama drama : dramaArray) {
                        if (drama.getGenre().equalsIgnoreCase(key)) dramas.add(drama);
                    }
                    break;
                }
                case RATING: {
                    Drama[] dramaArray = (( Rating ) sibling).getDramas().toArray(new Drama[0]);
                    for ( Drama drama : dramaArray) {
                        if (drama.getRating().equalsIgnoreCase(key)) dramas.add(drama);
                    }
                    break;
                }
			case DRAMA:
				break;
			case ROOT:
				break;
			default:
				break;
            }
        }
        Drama[] dramaArray = dramas.toArray(new Drama[0]);
        Drama.sort(dramaArray);
        return dramaArray;
    }

    public void add( Node child) {
        AttrType type = child.getType();
        List<Node> siblings = root.getChildren().get(type.ordinal());
        siblings.add(child);
    }

    public void print() {
        List<List<Node>> rootChildren = root.getChildren();
        for (List<Node> siblings : rootChildren) {
            System.out.println(siblings);
        }
    }

    public void addDrama( Drama drama) {
        List<List<Node>> siblings = root.getChildren();
        addDramaToCountry(drama, siblings.get(AttrType.COUNTRY.ordinal()));
        addDramaToLanguage(drama, siblings.get(AttrType.LANGUAGE.ordinal()));
        addDramaToSubtitles(drama, siblings.get(AttrType.SUBTITLES.ordinal()));
        addDramaToGenre(drama, siblings.get(AttrType.GENRE.ordinal()));
        addDramaToRating(drama, siblings.get(AttrType.RATING.ordinal()));
    }

    private void addDramaToCountry( Drama drama, List<Node> nodes) {
        for ( Node node : nodes) {
            Country country = ( Country ) node;
            if (country.getCountryName().equalsIgnoreCase(drama.getCountry())) {
                country.addDrama(drama);
                return;
            }
        }
    }

    private void addDramaToLanguage( Drama drama, List<Node> nodes) {
        for ( Node node : nodes) {
            Language language = (Language) node;
            if (language.getLanguageName().equalsIgnoreCase(drama.getLanguage())) {
                language.addDrama(drama);
                return;
            }
        }
    }

    private void addDramaToSubtitles( Drama drama, List<Node> nodes) {
        for ( Node node : nodes) {
            Subtitles subtitles = ( Subtitles ) node;
            if (subtitles.getLanguageName().equalsIgnoreCase(drama.getSubtitles())) {
                subtitles.addDrama(drama);
                return;
            }
        }
    }

    private void addDramaToGenre( Drama drama, List<Node> nodes) {
        for ( Node node : nodes) {
            Genre genre = ( Genre ) node;
            if (genre.getGenreName().equalsIgnoreCase(drama.getGenre())) {
                genre.addDrama(drama);
                return;
            }
        }
    }

    private void addDramaToRating( Drama drama, List<Node> nodes) {
        for ( Node node : nodes) {
            Rating rating = ( Rating ) node;
            if (rating.getRate().equalsIgnoreCase(drama.getRating())) {
                rating.addDrama(drama);
                return;
            }
        }
    }

}
// last edit weds Dec 15 4:10 pm

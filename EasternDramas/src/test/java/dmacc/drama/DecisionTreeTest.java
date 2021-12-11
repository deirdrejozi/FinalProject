package dmacc.drama;

import dmacc.drama.entities.AttrType;
import dmacc.drama.entities.DecisionTree;
import dmacc.drama.entities.Drama;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

class DecisionTreeTest {
    static DecisionTree tree;
    private static final String[] countries = {"South Korea", "North Korea", "Japan", "China", "Taiwan"};
    private static final String[] languages = {"Korean", "Japanese", "Chinese"};
    private static final String[] subtitles = {"none", "English", "Korean", "Japanese", "Chinese"};
    private static final String[] genres = {"Action", "Based on True Story", "Comedy", "Crime", "Educational", "Food",
        "Friendship", "Historical", "Horror", "Legend", "Melodrama", "Myth", "Mystery", "Political",
        "Romance", "Thriller", "Tragedy", "Tragic Romance"};
    private static final String[][] ratings = {{"G","General Audience"}, {"PG", "Parental Guidance Advised"},
        {"PG-13", "Parental Strongly Cautioned"}, {"NC-17", "No One Under 17"}, {"R", "Restricted"}};

    @BeforeAll
    static void setUp() {
        tree = new DecisionTree(countries, languages, subtitles, genres, ratings);
    }

    @Test
    void treeBuildTest() {
        assertEquals(5,  tree.getRoot().getChildren().size());
        assertEquals(5,  tree.getRoot().getChildren().get(AttrType.COUNTRY.ordinal()).size());
        assertEquals(3,  tree.getRoot().getChildren().get(AttrType.LANGUAGE.ordinal()).size());
        assertEquals(5,  tree.getRoot().getChildren().get(AttrType.SUBTITLES.ordinal()).size());
        assertEquals(18, tree.getRoot().getChildren().get(AttrType.GENRE.ordinal()).size());
        assertEquals(5,  tree.getRoot().getChildren().get(AttrType.RATING.ordinal()).size());
    }

    @Test
    void countrySearchTest() {
        Drama[] dramas = tree.search(AttrType.COUNTRY.ordinal(), "Japan");
        assertEquals(18, dramas.length);
    }

    @Test
    void languageSearchTest() {
        Drama[] dramas = tree.search(AttrType.LANGUAGE.ordinal(), "Chinese");
        assertEquals(46, dramas.length);
    }

    @Test
    void subtitlesSearchTest() {
        Drama[] dramas = tree.search(AttrType.SUBTITLES.ordinal(), "none");
        assertEquals(14, dramas.length);
    }

    @Test
    void actionSearchTest() {
        Drama[] dramas = tree.search(AttrType.GENRE.ordinal(), "Action");
        assertEquals(6, dramas.length);
    }

    @Test
    void ratingSearchTest() {
        Drama[] dramas = tree.search(AttrType.RATING.ordinal(), "R");
        assertEquals(8, dramas.length);
    }
}
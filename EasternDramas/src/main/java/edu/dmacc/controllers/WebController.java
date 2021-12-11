
package dmacc.drama.controllers;

import dmacc.drama.entities.DecisionTree;
import dmacc.drama.entities.Drama;
import dmacc.drama.entities.Option;
import dmacc.drama.entities.Selection2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

/**
 * @author Pamela D. Jozi
 * Date: 12/10/2021
 * Time: 11:59
 */
@Controller
public class WebController {
    private final String[] countries = {"South Korea", "North Korea", "Japan", "China", "Taiwan"};
    private final String[] languages = {"Korean", "Japanese", "Chinese"};
    private final String[] subtitles = {"none", "English", "Korean", "Japanese", "Chinese"};
    private final String[] genres = {"Action", "Based on True Story", "Comedy", "Crime", "Educational", "Food",
        "Friendship", "Historical", "Horror", "Legend", "Melodrama", "Myth", "Mystery", "Political",
        "Romance", "Thriller", "Tragedy", "Tragic Romance"};
    private final String[][] ratings = {{"G","General Audience"}, {"PG", "Parental Guidance Advised"},
        {"PG-13", "Parental Strongly Cautioned"}, {"NC-17", "No One Under 17"}, {"R", "Restricted"}};
    private final String[] values = {"Countries", "Languages", "Subtitles", "Genres", "Ratings"};

    @GetMapping("/")
    String viewOptions( Model model) {
        Option sel = new Option();
        model.addAttribute("sel",sel);
        return "menu1";
    }

    @PostMapping("/menu2")
    String viewSelections( Option sel, Model model) {
        model.addAttribute("sel", sel);
        int opt;
        try { opt = Integer.parseInt(sel.getOpt1()); }
        catch (NumberFormatException e) { return viewOptions(model); }
        Selection2[] selections = initSelections(opt);
        model.addAttribute("value", values[opt - 1]);
        model.addAttribute("selections", selections);
        return "menu2";
    }

    @PostMapping("/process")
    String processSearch( Option sel, Model model) {
        int opt;
        try { opt = Integer.parseInt(sel.getOpt1()); }
        catch (NumberFormatException e) { return viewOptions(model); }

        int key;
        try { key = Integer.parseInt(sel.getOpt2()); }
        catch (NumberFormatException e) { return viewSelections(sel, model); }
        DecisionTree tree = new DecisionTree(countries, languages, subtitles, genres, ratings);
        List<Drama> dramas = Arrays.asList(tree.search(opt - 1, getKey(opt, key)));
        model.addAttribute("dramas", dramas);
        return "results";
    }

    String getKey(int opt, int key) {
        switch (opt) {
            case 1: if (key > 0 && key <= countries.length) return countries[key - 1];
            case 2: if (key > 0 && key <= languages.length) return languages[key - 1];
            case 3: if (key > 0 && key <= subtitles.length) return subtitles[key - 1];
            case 4: if (key > 0 && key <= genres.length)    return genres[key - 1];
        }
        return ratings[key - 1][0];
    }

    Selection2[] initSelections( int opt1) {
        Selection2[] selections = new Selection2[0];
        switch (opt1) {
            case 1: {
                selections = new Selection2[countries.length];
                for ( int i = 0; i < selections.length; i++ ) selections[i] = new Selection2(i + 1, countries[i]);
                break;
            }
            case 2: {
                selections = new Selection2[languages.length];
                for ( int i = 0; i < selections.length; i++ ) selections[i] = new Selection2(i + 1, languages[i]);
                break;
            }
            case 3: {
                selections = new Selection2[subtitles.length];
                for ( int i = 0; i < selections.length; i++ ) selections[i] = new Selection2(i + 1, subtitles[i]);
                break;
            }
            case 4: {
                selections = new Selection2[genres.length];
                for ( int i = 0; i < selections.length; i++ ) selections[i] = new Selection2(i + 1, genres[i]);
                break;
            }
            case 5: {
                selections = new Selection2[ratings.length];
                for ( int i = 0; i < selections.length; i++ ) selections[i] = new Selection2(i + 1, ratings[i][0]);
                break;
            }
        }
        return selections;
    }
}
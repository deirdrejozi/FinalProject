package edu.dmacc.controllers;

import edu.dmacc.entities.*;
import edu.dmacc.repositories.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Pamela D. Jozi
 * Date: 12/10/2021
 * Time: 11:59
 */
@Controller
public class DramaController {
    final
    DramaRepository repo;

    final
    LanguageRepository langRepo;

    final
    CountryRepository countryRepo;

    final
    GenreRepository genreRepo;

    final
    RatingRepository ratingRepo;

    public DramaController( DramaRepository repo, LanguageRepository langRepo, CountryRepository countryRepo, GenreRepository genreRepo, RatingRepository ratingRepo ) {
        this.repo = repo;
        this.langRepo = langRepo;
        this.countryRepo = countryRepo;
        this.genreRepo = genreRepo;
        this.ratingRepo = ratingRepo;
    }

    @GetMapping("/")
    String start(Model model) {
        return viewDramas("dramaTitle", model);
    }

    @GetMapping("/viewDramas/{order}")
    String viewDramas( @PathVariable("order") String order, Model model) {
        List<Drama> dramas = repo.findAll(Sort.by(Sort.Order.by(order)));
        if (dramas.isEmpty()) {
            return addNewDrama(model);
        }
        System.out.println("There are " + dramas.size() + " dramas.\n" + dramas);
        model.addAttribute("dramas", dramas);
        return "index";
    }

    @GetMapping("/inputDrama")
    String addNewDrama(Model model) {
        Drama drama = new Drama();
        model.addAttribute("drama", drama);
        loadTables(model);
        return "newDrama";
    }

    @GetMapping("/editDrama/{id}")
    String showUpdateDrama( @PathVariable("id") Integer dramaId, Model model) {
        Drama drama = repo.findById(dramaId).get();
        model.addAttribute("drama", drama);
        return "editDrama";
    }

    @PostMapping("/saveDrama/{id}")
    String saveDrama(Drama drama, Model model) {
        repo.save(drama);
        return viewDramas("dramaTitle", model);
    }


    @PostMapping("/deleteDrama/{id}")
    String deleteDrama( @PathVariable("id") Integer dramaId, Model model) {
        repo.deleteById(dramaId);
        return viewDramas("dramaTitle", model);
    }

    void loadTables( Model model) {
        List<Country> countries = countryRepo.findAll();
        model.addAttribute("countries", countries);
        List<Genre> genres = genreRepo.findAll();
        model.addAttribute("genres", genres);
        List<Language> languages = langRepo.findAll();
        model.addAttribute("languages", languages);
        List<Rating> ratings = ratingRepo.findAll();
        model.addAttribute("ratings", ratings);
        System.out.printf("******** %d countries, %d genres, %d languages, %d ratings.\n",
            countries.size(), genres.size(), languages.size(), ratings.size());
    }
}

package edu.dmacc.controllers;

import edu.dmacc.entities.Genre;
import edu.dmacc.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GenreController {
    @Autowired
    GenreRepository repo;

    @GetMapping("/viewGenres")
    String viewGenres( Model model) {
        List<Genre> genres = repo.findAll();
        if (genres.isEmpty()) {
            return addNewGenre((model));
        }
        model.addAttribute("genres", genres);
        return "viewGenres";
    }

    @GetMapping("/inputGenre")
    String addNewGenre(Model model) {
        Genre newGenre = new Genre();
        model.addAttribute("newGenre", newGenre);
        return "newGenre";
    }

    @GetMapping("/editGenre/{id}")
    String showUpdateGenre( @PathVariable("id") int genreId, Model model) {
        Genre genre = repo.findById(genreId).orElse(null);
        model.addAttribute("newGenre", genre);
        return "newGenre";
    }

    @PostMapping("/updateGenre/{id}")
    String saveGenre(Genre newGenre, Model model) {
        repo.save(newGenre);
        return viewGenres(model);
    }

    @PostMapping("/deleteGenre/{id}")
    String deleteGenre( @PathVariable("id") int genreId, Model model) {
        repo.deleteById(genreId);
        return viewGenres(model);
    }
}

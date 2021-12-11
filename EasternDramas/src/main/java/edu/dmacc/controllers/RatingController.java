package edu.dmacc.controllers;

import edu.dmacc.entities.Rating;
import edu.dmacc.repositories.RatingRepository;
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
public class RatingController {
    @Autowired
    RatingRepository repo;

    @GetMapping("/viewRatings")
    String viewRatings( Model model) {
        List<Rating> ratings = repo.findAll();
        if (ratings.isEmpty()) {
            return addNewRating((model));
        }
        model.addAttribute("ratings", ratings);
        return "viewRatings";
    }

    @GetMapping("/inputRating")
    String addNewRating(Model model) {
        Rating newRating = new Rating();
        model.addAttribute("newRating", newRating);
        return "newRating";
    }

    @GetMapping("/editRating/{id}")
    String showUpdateRating( @PathVariable("id") int ratingId, Model model) {
        Rating rating = repo.findById(ratingId).orElse(null);
        model.addAttribute("newRating", rating);
        return "newRating";
    }

    @PostMapping("/updateRating/{id}")
    String saveRating(Rating newRating, Model model) {
        repo.save(newRating);
        return viewRatings(model);
    }

    @PostMapping("/deleteRating/{id}")
    String deleteRating( @PathVariable("id") int RatingId, Model model) {
        repo.deleteById(RatingId);
        return viewRatings(model);
    }
}

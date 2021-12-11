package edu.dmacc.controllers;

import edu.dmacc.entities.Country;
import edu.dmacc.repositories.CountryRepository;
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
public class CountryController {
    @Autowired
    CountryRepository repo;

    @GetMapping("/viewCountries")
    String viewAllCountries( Model model) {
        List<Country> countries = repo.findAll();
        if (countries.isEmpty()) {
            return addNewCountry((model));
        }
        model.addAttribute("countries", countries);
        return "viewCountries";
    }

    @GetMapping("/inputCountry")
    String addNewCountry(Model model) {
        Country newCountry = new Country();
        model.addAttribute("newCountry", newCountry);
        return "newCountry";
    }

    @GetMapping("/editCountry/{id}")
    String showUpdateCountry( @PathVariable("id") int countryId, Model model) {
        Country country = repo.findById(countryId).orElse(null);
        model.addAttribute("newCountry", country);
        return "newCountry";
    }

    @PostMapping("/updateCountry/{id}")
    String saveCust(Country newCountry, Model model) {
        repo.save(newCountry);
        return viewAllCountries(model);
    }

    @PostMapping("/deleteCountry/{id}")
    String deleteCust( @PathVariable("id") int countryId, Model model) {
        repo.deleteById(countryId);
        return viewAllCountries(model);
    }
}

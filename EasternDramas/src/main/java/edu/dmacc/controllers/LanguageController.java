package edu.dmacc.controllers;

import edu.dmacc.entities.Language;
import edu.dmacc.repositories.LanguageRepository;
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
public class LanguageController {
    @Autowired
    LanguageRepository repo;

    @GetMapping("/viewLanguages")
    String viewLanguages( Model model) {
        List<Language> languages = repo.findAll();
        if (languages.isEmpty()) {
            return addNewLanguage((model));
        }
        model.addAttribute("languages", languages);
        return "viewLanguages";
    }

    @GetMapping("/inputLanguage")
    String addNewLanguage(Model model) {
        Language newLanguage = new Language();
        model.addAttribute("newLanguage", newLanguage);
        return "newLanguage";
    }

    @GetMapping("/editLanguage/{id}")
    String showUpdateLanguage( @PathVariable("id") int languageId, Model model) {
        Language language = repo.findById(languageId).orElse(null);
        model.addAttribute("newLanguage", language);
        return "newLanguage";
    }

    @PostMapping("/updateLanguage/{id}")
    String saveLanguage(Language newLanguage, Model model) {
        repo.save(newLanguage);
        return viewLanguages(model);
    }

    @PostMapping("/deleteLanguage/{id}")
    String deleteLanguage( @PathVariable("id") int languageId, Model model) {
        repo.deleteById(languageId);
        return viewLanguages(model);
    }
}

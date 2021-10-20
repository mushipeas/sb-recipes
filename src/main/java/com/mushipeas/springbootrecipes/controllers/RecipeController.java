package com.mushipeas.springbootrecipes.controllers;

import com.mushipeas.springbootrecipes.models.Recipe;
import com.mushipeas.springbootrecipes.services.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/search")
    public String Search(Model model, @RequestParam String terms, @RequestParam(defaultValue = "1") Integer p) {
        Integer pageNo = p-1;
        Integer pageSize = 21;
        String sortBy = "title";
        Page<Recipe> resultsPage = recipeService.getAllRecipesWhichContainKeywords(terms, pageNo, pageSize, sortBy);
        model.addAttribute("results", resultsPage.getContent());
        
        if ( p != 1 ) {
            model.addAttribute("previousPage", p - 1);
        }
        if ( p < resultsPage.getTotalPages() ) {
            model.addAttribute("nextPage", p + 1);
        }
        
        model.addAttribute("results_count", resultsPage.getTotalElements());
        model.addAttribute("terms", terms);
        model.addAttribute("p", p);

        return "search";
    }
    
}

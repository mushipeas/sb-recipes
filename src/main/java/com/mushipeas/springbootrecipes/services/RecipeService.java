package com.mushipeas.springbootrecipes.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.mushipeas.springbootrecipes.dao.RecipeDao;
import com.mushipeas.springbootrecipes.models.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    // @Autowired 
    // private RecipeDao recipeDao;

    private final RecipeDao recipeDao;

    @Autowired
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }
    
    public Page<Recipe> getAllRecipesWhichContainKeywords(String keywords, Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywords.split(" ")));
 
        Page<Recipe> pagedResult = recipeDao.fetchRecipesWhichContainInTitle(keywordSet, paging);
        
        return pagedResult;
    }
    
}

package com.mushipeas.springbootrecipes.dao;

import java.util.Set;

import com.mushipeas.springbootrecipes.models.Recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecipeDaoCustom {

    Page<Recipe> fetchRecipesWhichContainInTitle(Set<String> keywords, Pageable pageable);
    
}

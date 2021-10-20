package com.mushipeas.springbootrecipes.dao;

import org.springframework.stereotype.Repository;

import com.mushipeas.springbootrecipes.models.Recipe;

import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface RecipeDao extends PagingAndSortingRepository<Recipe, Integer>, RecipeDaoCustom {
    
}

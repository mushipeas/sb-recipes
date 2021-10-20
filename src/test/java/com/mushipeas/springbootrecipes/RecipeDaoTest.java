package com.mushipeas.springbootrecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mushipeas.springbootrecipes.dao.RecipeDao;
import com.mushipeas.springbootrecipes.models.Recipe;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RecipeDaoTest {

    @Autowired private RecipeDao recipeDao;

    private Recipe recipe1 = new Recipe(1, "test", "beef and broccoli stew", 1, "", "", "", "", "");
    private Recipe recipe2 = new Recipe(2, "test", "beef steak with broccoli cheese", 1, "", "", "", "", "");
    private Recipe recipe3 = new Recipe(3, "test", "chicken steak", 1, "", "", "", "", "");
    private List<Recipe> allRecipes = List.of(recipe1, recipe2, recipe3);

    @BeforeEach
    void setup(){
        recipeDao.saveAll(allRecipes);
    }
    
    @Test
    void injectedComponentsAreNotNull(){
        Assertions.assertThat(recipeDao).isNotNull();
    }

    @Test
    public void givenKeywordsReturnRecipesWithTitlesContainingKeywords() {
        List<Recipe> expectedRecipes = List.of(recipe1, recipe2);
        
        Pageable paging = PageRequest.of(0, 20, Sort.by("title"));
        // when
        Set<String> recipes1and2ButNot3 = Set.of("beef", "broccoli");
        Page<Recipe> returnedRecipes = recipeDao.fetchRecipesWhichContainInTitle(recipes1and2ButNot3, paging);
        // then
        Assertions.assertThat(returnedRecipes.getContent())
            .hasSize(expectedRecipes.size())
            .extracting(Recipe::getTitle)
            .hasSameElementsAs(expectedRecipes.stream()
                                                    .map(recipe -> recipe.getTitle())
                                                    .collect(Collectors.toList()));

            // .containsExactlyInAnyOrder(recipe1.getTitle(), recipe2.getTitle());
    }

    @Test
    public void givenNoKeywordsReturnAllRecipes() {
        List<Recipe> expectedRecipes = new ArrayList<>(allRecipes);;
        
        Pageable paging = PageRequest.of(0, 20, Sort.by("title"));
        // when
        Set<String> recipes1and2ButNot3 = Set.of("");
        Page<Recipe> returnedRecipes = recipeDao.fetchRecipesWhichContainInTitle(recipes1and2ButNot3, paging);
        // then
        Assertions.assertThat(returnedRecipes.getContent())
            .hasSize(expectedRecipes.size())
            .extracting(Recipe::getTitle)
            .hasSameElementsAs(expectedRecipes.stream()
                                                    .map(recipe -> recipe.getTitle())
                                                    .collect(Collectors.toList()));
    }
}
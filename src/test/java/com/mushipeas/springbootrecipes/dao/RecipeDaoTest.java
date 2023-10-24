package com.mushipeas.springbootrecipes.dao;

import com.mushipeas.springbootrecipes.models.Recipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
class RecipeDaoTest {

    private final Recipe recipe1 = new Recipe(1, "test", "beef and broccoli stew", 1, "", "", "", "", "");
    private final Recipe recipe2 = new Recipe(2, "test", "beef steak with broccoli cheese", 1, "", "", "", "", "");
    private final Recipe recipe3 = new Recipe(3, "test", "chicken steak", 1, "", "", "", "", "");
    private final List<Recipe> allRecipes = List.of(recipe1, recipe2, recipe3);

    @Autowired private RecipeDao recipeDao;

    @BeforeEach
    void setup(){
        recipeDao.saveAll(allRecipes);
    }

    @Test
    void injectedComponentsAreNotNull(){
        Assertions.assertThat(recipeDao).isNotNull();
    }

    @Test
    void fetchRecipesWhichContainInTitle_givenKeywords_returnsCorrectRecipes() {
        List<Recipe> expectedRecipes = List.of(recipe1, recipe2);
        Pageable paging = PageRequest.of(0, 20, Sort.by("title"));

        Set<String> recipes1and2ButNot3 = Set.of("beef", "broccoli");
        Page<Recipe> returnedRecipes = recipeDao.fetchRecipesWhichContainInTitle(recipes1and2ButNot3, paging);

        Assertions.assertThat(returnedRecipes.getContent())
            .hasSize(expectedRecipes.size())
            .extracting(Recipe::getTitle)
            .hasSameElementsAs(expectedRecipes.stream()
                .map(Recipe::getTitle)
                .collect(Collectors.toList()));
    }

    @Test
    void fetchRecipesWhichContainInTitle_givenNoKeywords_returnAllRecipes() {
        List<Recipe> expectedRecipes = new ArrayList<>(allRecipes);;
        Pageable paging = PageRequest.of(0, 20, Sort.by("title"));

        Set<String> recipes1and2ButNot3 = Set.of("");
        Page<Recipe> returnedRecipes = recipeDao.fetchRecipesWhichContainInTitle(recipes1and2ButNot3, paging);

        Assertions.assertThat(returnedRecipes.getContent())
            .hasSize(expectedRecipes.size())
            .extracting(Recipe::getTitle)
            .hasSameElementsAs(expectedRecipes.stream()
                .map(Recipe::getTitle)
                .collect(Collectors.toList()));
    }
}
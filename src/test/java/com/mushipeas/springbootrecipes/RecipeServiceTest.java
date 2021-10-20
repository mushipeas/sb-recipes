package com.mushipeas.springbootrecipes;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.mushipeas.springbootrecipes.dao.RecipeDao;
import com.mushipeas.springbootrecipes.services.RecipeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
    
    @Mock private RecipeDao recipeDao;
    private RecipeService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RecipeService(recipeDao);
    }

    @Test
    public void itShouldModifyKeywordsIntoASetAndPassPaginationRequestAsPageable() {
        String keywords = "test words";
        Integer pageNo = 1;
        Integer pageSize = 21;
        String sortBy = "title";
        // when
        underTest.getAllRecipesWhichContainKeywords(keywords, pageNo, pageSize, sortBy);
        
        Pageable expectedPageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Set<String> expectedKeywordSet = new HashSet<String>(Arrays.asList("test words".split(" ")));
        // then
        verify(recipeDao).fetchRecipesWhichContainInTitle(expectedKeywordSet, expectedPageable);
        verifyNoMoreInteractions(recipeDao);
    }
    
}

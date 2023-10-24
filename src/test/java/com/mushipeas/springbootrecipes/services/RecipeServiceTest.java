package com.mushipeas.springbootrecipes.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.mushipeas.springbootrecipes.dao.RecipeDao;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    
    @Mock
    private RecipeDao recipeDao;

    @InjectMocks
    private RecipeService underTest;

    @Test
    void getAllRecipesWhichContainKeywords_shouldFetchFromRecipeDaoWithCorrectPagination() {
        String keywords = "test words";
        int pageNo = 1;
        int pageSize = 21;
        String sortBy = "title";

        underTest.getAllRecipesWhichContainKeywords(keywords, pageNo, pageSize, sortBy);
        
        Pageable expectedPageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Set<String> expectedKeywordSet = Set.of("test words".split(" "));

        verify(recipeDao).fetchRecipesWhichContainInTitle(expectedKeywordSet, expectedPageable);
        verifyNoMoreInteractions(recipeDao);
    }
    
}

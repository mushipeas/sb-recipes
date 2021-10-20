package com.mushipeas.springbootrecipes.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mushipeas.springbootrecipes.models.Recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class RecipeDaoCustomImpl implements RecipeDaoCustom {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Recipe> fetchRecipesWhichContainInTitle(Set<String> keywords, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
        Root<Recipe> root = cq.from(Recipe.class);
        Path<String> titlePath = root.get("title");

        List<Predicate> predicates = new ArrayList<>();
        for (String keyword : keywords) {
            predicates.add(cb.like(titlePath, "%"+keyword+"%"));
        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<Recipe> query = em.createQuery(cq);

        int totalRows = query.getResultList().size();
        List<Recipe> result = query.setFirstResult((int) pageable.getOffset())
                        .setMaxResults(pageable.getPageSize()).getResultList();

        Page<Recipe> resultPage = new PageImpl<>(result, pageable, totalRows);
        return resultPage;
    }
    
}
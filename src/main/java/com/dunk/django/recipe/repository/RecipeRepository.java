package com.dunk.django.recipe.repository;

import com.dunk.django.domain.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @EntityGraph(attributePaths = {"foodNation", "foodType"})
    Page<Recipe> findAll(Pageable pageable);
}
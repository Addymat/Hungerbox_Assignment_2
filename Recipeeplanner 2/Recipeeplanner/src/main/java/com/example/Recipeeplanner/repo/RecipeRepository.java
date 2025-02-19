package com.example.Recipeeplanner.repo;

import com.example.Recipeeplanner.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategory(String category);
    List<Recipe> findByDietaryTagsContaining(String tag);
    List<Recipe> findByNameContaining(String name);
}

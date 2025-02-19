package com.example.Recipeeplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Recipeeplanner.repo.*;
import com.example.Recipeeplanner.models.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe) {
        // Ensure unique recipe name
        if (recipeRepository.findByNameContaining(recipe.getName()).size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Recipe name already exists!");
        }

        // Validate dietary tags
        List<String> validTags = List.of("Vegetarian","Nonvegetarian", "Vegan", "Gluten-Free", "Keto", "Paleo");
        for (String tag : recipe.getDietaryTags().split(",")) {
            if (!validTags.contains(tag.trim())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid dietary tag: " + tag);
            }
        }

        List<Recipe> existingRecipes = recipeRepository.findByNameContaining(recipe.getName());
        for (Recipe existing : existingRecipes) {
            if (existing.getIngredients().equals(recipe.getIngredients())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A recipe with the same name and ingredients already exists!");
            }
        }


        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe newRecipeData) {
        return recipeRepository.findById(id).map(recipe -> {
            recipe.setName(newRecipeData.getName());
            recipe.setCategory(newRecipeData.getCategory());
            recipe.setSteps(newRecipeData.getSteps());
            recipe.setDietaryTags(newRecipeData.getDietaryTags());
            recipe.setIngredients(newRecipeData.getIngredients());
            return recipeRepository.save(recipe);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));
    }



    public Recipe getRecipeById(Long id) {return recipeRepository.findById(id).get();}

    public List<Recipe> getRecipesByCategory(String category) {
        return recipeRepository.findByCategory(category);
    }

    public List<Recipe> getRecipesByDietaryTag(String tag) {
        return recipeRepository.findByDietaryTagsContaining(tag);
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContaining(name);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }



    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}


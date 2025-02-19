package com.example.Recipeeplanner.controller;

import com.example.Recipeeplanner.models.Recipe;
import com.example.Recipeeplanner.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.Recipeeplanner.utils.JsonCsvUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private JsonCsvUtil jsonCsvUtil;

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/category/{category}")
    public List<Recipe> getRecipesByCategory(@PathVariable String category) {
        return recipeService.getRecipesByCategory(category);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String name) {
        return recipeService.searchRecipesByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }


    @GetMapping("/export/json")
    public ResponseEntity<String> exportRecipesToJson() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            String json = jsonCsvUtil.convertToJson(recipes);
            return ResponseEntity.ok(json);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting recipes to JSON.");
        }
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportRecipesToCsv() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            String csv = jsonCsvUtil.convertToCsv(recipes);
            return ResponseEntity.ok(csv);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error exporting recipes to CSV.");
        }
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return recipeService.updateRecipe(id, updatedRecipe);
    }


    @PostMapping("/import/json")
    public ResponseEntity<String> importRecipesFromJson(@RequestBody String json) {
        try {
            List<Recipe> recipes = jsonCsvUtil.convertFromJson(json);
            for (Recipe recipe : recipes) {
                recipeService.addRecipe(recipe);
            }
            return ResponseEntity.ok("Recipes imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error importing recipes from JSON.");
        }
    }
}

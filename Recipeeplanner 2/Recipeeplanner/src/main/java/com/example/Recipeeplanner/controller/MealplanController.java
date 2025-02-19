package com.example.Recipeeplanner.controller;

import com.example.Recipeeplanner.models.Ingredient;
import com.example.Recipeeplanner.models.Mealplan;
import com.example.Recipeeplanner.repo.MealplanRepository;
import com.example.Recipeeplanner.service.MealplanService;
import com.example.Recipeeplanner.utils.JsonCsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mealplan")
public class MealplanController {
    @Autowired
    private MealplanService mealPlanService;

    @Autowired
    private MealplanRepository mealPlanRepository;

    @Autowired
    private JsonCsvUtil jsonCsvUtil;

    @PostMapping
    public Mealplan assignRecipeToDay(@RequestParam String day, @RequestParam Long recipeId) {
        return mealPlanService.assignRecipeToDay(day, recipeId);
    }

    @PostMapping("/import/json")
    public ResponseEntity<String> importMealPlansFromJson(@RequestBody String json) {
        try {
            List<Mealplan> mealPlans = jsonCsvUtil.convertMealPlansFromJson(json);
            mealPlans.forEach(mealPlanRepository::save);
            return ResponseEntity.ok("Meal plans imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.");
        }
    }


    @GetMapping("/shopping-list")
    public List<Ingredient> generateShoppingList() {
        return mealPlanService.generateShoppingList();
    }
}
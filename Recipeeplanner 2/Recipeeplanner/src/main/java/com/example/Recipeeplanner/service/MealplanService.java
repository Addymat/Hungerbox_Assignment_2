package com.example.Recipeeplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.Recipeeplanner.repo.*;
import com.example.Recipeeplanner.models.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class MealplanService {
    @Autowired
    private MealplanRepository mealPlanRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeService recipeService;

    public Mealplan assignRecipeToDay(String day, Long recipeId) {
        // Validate day
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found"));

        List<String> validDays = List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        if (!validDays.contains(day)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid day: " + day);
        }



        Mealplan mealPlan = new Mealplan();
        mealPlan.setDayOfWeek(day);

        mealPlan.setRecipes(List.of(recipe));

        return mealPlanRepository.save(mealPlan);
    }


    public List<Mealplan> getMealplanByDay(String day) {
        return mealPlanRepository.findByDayOfWeek(day);
    }

    public List<Ingredient> generateShoppingList() {
        List<Mealplan> mealPlans = mealPlanRepository.findAll();
        List<Ingredient> shoppingList = new ArrayList<>();

        for (Mealplan mealPlan : mealPlans) {
            for (Recipe recipe : mealPlan.getRecipes()) {
                shoppingList.addAll(recipe.getIngredients());
            }
        }
        return shoppingList;

    }
}

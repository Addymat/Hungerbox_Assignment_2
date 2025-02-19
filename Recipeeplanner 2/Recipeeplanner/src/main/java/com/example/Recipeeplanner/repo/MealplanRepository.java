package com.example.Recipeeplanner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Recipeeplanner.models.*;

import java.util.List;

@Repository
public interface MealplanRepository extends JpaRepository<Mealplan, Long> {
    List<Mealplan> findByDayOfWeek(String day);
}
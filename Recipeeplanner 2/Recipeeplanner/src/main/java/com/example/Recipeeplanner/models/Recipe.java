package com.example.Recipeeplanner.models;

import jakarta.persistence.*;
import com.example.Recipeeplanner.models.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String steps;
    private String dietaryTags;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;


}

package com.example.Recipeeplanner.models;

import jakarta.persistence.*;
import lombok.Data;
import com.example.Recipeeplanner.models.*;
@Entity
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String quantity;

    @ManyToOne
    @Transient
    @JoinColumn(name = "recipe_id",referencedColumnName = "ID")
    private Recipe recipe;

}
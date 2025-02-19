package com.example.Recipeeplanner.utils;

import com.example.Recipeeplanner.models.Mealplan;
import com.example.Recipeeplanner.models.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class JsonCsvUtil {

    private final ObjectMapper objectMapper;

    public JsonCsvUtil() {
        this.objectMapper = new ObjectMapper();
    }

    // Convert List<Recipe> to JSON string
    public String convertToJson(List<Recipe> recipes) throws IOException {
        return objectMapper.writeValueAsString(recipes);
    }

    // Convert List<Recipe> to CSV format
    public String convertToCsv(List<Recipe> recipes) {
        StringWriter writer = new StringWriter();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Category", "Ingredients", "Instructions"))) {
            for (Recipe recipe : recipes) {
                csvPrinter.printRecord(recipe.getId(), recipe.getName(), recipe.getCategory(), recipe.getIngredients(),recipe.getSteps());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    // Convert JSON string to List<Recipe>
    public List<Recipe> convertFromJson(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<List<Recipe>>() {});
    }

    public List<Mealplan> convertMealPlansFromJson(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<List<Mealplan>>() {});
    }


    // Export Recipes to JSON
    public static void exportRecipesToJson(List<Recipe> recipes, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(filePath), recipes);
    }

    // Import Recipes from JSON
    public static List<Recipe> importRecipesFromJson(String filePath, ObjectMapper objectMapper) throws IOException {
        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
    }

    // Export Meal Plans to JSON
    public static void exportMealplansToJson(List<Mealplan> mealPlans, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(filePath), mealPlans);
    }

    // Export Recipes to CSV
    public static void exportRecipesToCsv(List<Recipe> recipes, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(new String[]{"ID", "Name", "Category", "Steps", "Dietary Tags"});
            for (Recipe recipe : recipes) {
                writer.writeNext(new String[]{
                        recipe.getId().toString(),
                        recipe.getName(),
                        recipe.getCategory(),
                        recipe.getSteps(),
                        recipe.getDietaryTags()
                });
            }
        }
    }
}





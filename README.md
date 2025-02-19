# RecipeePlanner
A Spring Boot application for meal planning, recipe management, and grocery list generation.

## Features
1. Add, update, delete, and retrieve recipes
2. Filter recipes by category and dietary tags
3. Import/export recipes in JSON and CSV format
4. Assign recipes to a meal plan by day
5. Generate a shopping list based on the meal plan

## Installation
## Prerequisites
1. Java 17+
2. IntelliJ IDEA (or any IDE of your choice)
3. Postman (for API testing)
4. Git

## Setup
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Wait for Maven dependencies to load
4. Run the application
5. The server will start at http://localhost:8080

## API Endpoints

1. Recipe Management
2. POST /recipes → Add a recipe
3. PUT /recipes/{id} → Update a recipe
4. DELETE /recipes/{id} → Delete a recipe
5. GET /recipes/{id} → Get recipe by ID
6. GET /recipes/category/{category} → Get recipes by category
7. GET /recipes/search?name={name} → Search recipes by name

## Meal Planning
1. POST /mealplan/{day}/{recipeId} → Assign recipe to a day
2. GET /mealplan/{day} → Get meal plan for a day
3. GET /mealplan/shopping-list → Generate shopping list
4. Import/Export Recipes
5. GET /recipes/export/json → Export recipes as JSON
6. GET /recipes/export/csv → Export recipes as CSV
7. POST /recipes/import/json → Import recipes from JSON

## Testing with Postman
1. Open Postman
2. Use http://localhost:8080 as the base URL
3. Send GET, POST, PUT, and DELETE requests to test the APIs
4. H2 Database Access
5. Go to http://localhost:8080/h2-console
6. Use the following credentials:
7. JDBC URL: jdbc:h2:mem:testdb
8. Username: sa
9. Password: password
10. Click Connect

## License
This project is licensed under the MIT License.




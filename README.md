#RecipeePlanner
A Spring Boot application for meal planning, recipe management, and grocery list generation.

Features
Add, update, delete, and retrieve recipes
Filter recipes by category and dietary tags
Import/export recipes in JSON and CSV format
Assign recipes to a meal plan by day
Generate a shopping list based on the meal plan
Installation
Prerequisites
Java 17+
IntelliJ IDEA (or any IDE of your choice)
Postman (for API testing)
Git

Setup
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Wait for Maven dependencies to load
4. Run the application
5. The server will start at http://localhost:8080

API Endpoints

Recipe Management
POST /recipes → Add a recipe
PUT /recipes/{id} → Update a recipe
DELETE /recipes/{id} → Delete a recipe
GET /recipes/{id} → Get recipe by ID
GET /recipes/category/{category} → Get recipes by category
GET /recipes/search?name={name} → Search recipes by name

Meal Planning
POST /mealplan/{day}/{recipeId} → Assign recipe to a day
GET /mealplan/{day} → Get meal plan for a day
GET /mealplan/shopping-list → Generate shopping list
Import/Export Recipes
GET /recipes/export/json → Export recipes as JSON
GET /recipes/export/csv → Export recipes as CSV
POST /recipes/import/json → Import recipes from JSON

Testing with Postman
Open Postman
Use http://localhost:8080 as the base URL
Send GET, POST, PUT, and DELETE requests to test the APIs
H2 Database Access
Go to http://localhost:8080/h2-console
Use the following credentials:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password
Click Connect

License
This project is licensed under the MIT License.




package ru.gb;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RecipeTests {
//    String id;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void getRecipePositiveTest() {
        given()
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information?" +
                        "includeNutrition=false&apiKey=7f5fb9a7a9934c788b1bfc1bb437d108")
                                .then()
                                .statusCode(200);
    }

    @Test
    void getRecipeWithQueryParametersPositiveTest() {
        given()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then()
                .statusCode(200);
    }

//    @Test
//    void addMealTest() {
//        id = given()
//                .queryParam("hash", "7f5fb9a7a9934c788b1bfc1bb437d108")
//                .queryParam("apiKey", "3e6cc9c807c84d0ba3518045b86e6687")
//                .body("{\n"
//                        + " \"date\": 1644881179,\n"
//                        + " \"slot\": 1,\n"
//                        + " \"position\": 0,\n"
//                        + " \"type\": \"INGREDIENTS\",\n"
//                        + " \"value\": {\n"
//                        + " \"ingredients\": [\n"
//                        + " {\n"
//                        + " \"name\": \"1 banana\"\n"
//                        + " }\n"
//                        + " ]\n"
//                        + " }\n"
//                        + "}")
//                .when()
//                .post("https://api.spoonacular.com/mealplanner/geekbrains/items")
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .get("id")
//                .toString();
//    }
//
//    @AfterEach
//    void tearDown() {
//        given()
//                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
//                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
//                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
//                .then()
//                .statusCode(200);
//    }

    @Test
    void getRecipeWithLoggingPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}

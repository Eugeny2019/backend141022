package ru.gb;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RecipeTests {
//    String id;

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
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

    @Test
    void getRecipeByIngredientsWithQueryParametersPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .queryParam("ingredients", "apples")
                .when()
                .get("https://api.spoonacular.com/recipes/findByIngredients")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getRecipeByIngredientsAndWithNumberOfRecipeWithQueryParametersPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .queryParam("ingredients", "apples")
                .queryParam("number", "2")
                .when()
                .get("https://api.spoonacular.com/recipes/findByIngredients")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getRecipeByIngredientsAndWithNumberOfRecipeAndWithLimitLicenseWithQueryParametersPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .queryParam("ingredients", "apples")
                .queryParam("number", "1")
                .queryParam("limitLicense", "true")
                .when()
                .get("https://api.spoonacular.com/recipes/findByIngredients")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
    @Test
    void getRecipeByIngredientsAndWithNumberOfRecipeAndWithLimitLicenseWithRankingWithQueryParametersPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .queryParam("ingredients", "apples")
                .queryParam("number", "2")
                .queryParam("limitLicense", "true")
                .queryParam("ranking", "1")
                .when()
                .get("https://api.spoonacular.com/recipes/findByIngredients")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getRecipeByIngredientsAndWithNumberOfRecipeAndWithLimitLicenseWithRankingWithIgnorePantryWithQueryParametersPositiveTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .queryParam("includeNutrition", "false")
                .queryParam("ingredients", "apples")
                .queryParam("number", "3")
                .queryParam("limitLicense", "true")
                .queryParam("ranking", "2")
                .queryParam("ignorePantry", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/findByIngredients")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void addCuisineTest() {
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .body("{\n"
                        + " \"cuisine\": \"Mediterranean\",\n"
                        + " \"cuisines\": [\n"
                        + " \"Mediterranean\",\n"
                        + " \"European\",\n"
                        + " \"Italian\"\n"
                        + " ],\n"
                        + " \"confidence\": 0.0\n"
                        + " }\n")
                .when()
                .post("https://api.spoonacular.com/recipes/cuisine")
                .prettyPeek()
                .then()
                .headers("Content-Type", "application/json")
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("Italian");
    }

    @Test
    @Disabled
    void addParseIngredientsTest() { //fails because absent parameter ingredientList in documentation request sample
        given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .body("{\n" +
                        "        \"id\": 10014356,\n" +
                        "        \"original\": \"1 big cup green tea\",\n" +
                        "        \"originalName\": \"green tea\",\n" +
                        "        \"name\": \"tea\",\n" +
                        "        \"nameClean\": \"green tea\",\n" +
                        "        \"amount\": 1.0,\n" +
                        "        \"unit\": \"big cup\",\n" +
                        "        \"unitShort\": \"big cup\",\n" +
                        "        \"unitLong\": \"big cup\",\n" +
                        "        \"possibleUnits\": [\n" +
                        "            \"g\",\n" +
                        "            \"oz\",\n" +
                        "            \"fluid ounce\",\n" +
                        "            \"cup\"\n" +
                        "        ],\n" +
                        "        \"estimatedCost\": {\n" +
                        "            \"value\": 2247.78,\n" +
                        "            \"unit\": \"US Cents\"\n" +
                        "        },\n" +
                        "        \"consistency\": \"solid\",\n" +
                        "        \"aisle\": \"Tea and Coffee\",\n" +
                        "        \"image\": \"green-tea-leaves.jpg\",\n" +
                        "        \"meta\": [\n" +
                        "            \"green\"\n" +
                        "        ],\n" +
                        "        \"nutrition\": {\n" +
                        "            \"nutrients\": [\n" +
                        "                {\n" +
                        "                    \"name\": \"Calories\",\n" +
                        "                    \"amount\": 4.70,\n" +
                        "                    \"unit\": \"kcal\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.24\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Fat\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Saturated Fat\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.03\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Carbohydrates\",\n" +
                        "                    \"amount\": 1.42,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.48\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Net Carbohydrates\",\n" +
                        "                    \"amount\": 1.42,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.52\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Sugar\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Cholesterol\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Sodium\",\n" +
                        "                    \"amount\": 14.16,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.62\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Caffeine\",\n" +
                        "                    \"amount\": 92,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 31.52\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Protein\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"g\",\n" +
                        "                    \"percentOfDailyNeeds\": 0.0\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Manganese\",\n" +
                        "                    \"amount\": 1.04,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 51.68\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Folate\",\n" +
                        "                    \"amount\": 23.6,\n" +
                        "                    \"unit\": \"Âµg\",\n" +
                        "                    \"percentOfDailyNeeds\": 5.9\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Potassium\",\n" +
                        "                    \"amount\": 174.64,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 4.99\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Vitamin B2\",\n" +
                        "                    \"amount\": 0.06,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 3.88\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Magnesium\",\n" +
                        "                    \"amount\": 14.16,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 3.54\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Copper\",\n" +
                        "                    \"amount\": 0.04,\n" +
                        "                    \"unit\": \"mg\",\n" +
                        "                    \"percentOfDailyNeeds\": 2.36\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"properties\": [\n" +
                        "                {\n" +
                        "                    \"name\": \"Glycemic Index\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Glycemic Load\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"flavonoids\": [\n" +
                        "                {\n" +
                        "                    \"name\": \"Cyanidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Petunidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Delphinidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Malvidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Pelargonidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Peonidin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Catechin\",\n" +
                        "                    \"amount\": 7.01,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Epigallocatechin\",\n" +
                        "                    \"amount\": 38.0,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Epicatechin\",\n" +
                        "                    \"amount\": 10.0,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Epicatechin 3-gallate\",\n" +
                        "                    \"amount\": 31.1,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Epigallocatechin 3-gallate\",\n" +
                        "                    \"amount\": 44.18,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Theaflavin\",\n" +
                        "                    \"amount\": 7.70,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Thearubigins\",\n" +
                        "                    \"amount\": 382.18,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Eriodictyol\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Hesperetin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Naringenin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Apigenin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Luteolin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Isorhamnetin\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Kaempferol\",\n" +
                        "                    \"amount\": 5.55,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Myricetin\",\n" +
                        "                    \"amount\": 2.12,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Quercetin\",\n" +
                        "                    \"amount\": 10.34,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Theaflavin-3,3'-digallate\",\n" +
                        "                    \"amount\": 8.26,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Theaflavin-3'-gallate\",\n" +
                        "                    \"amount\": 7.12,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Theaflavin-3-gallate\",\n" +
                        "                    \"amount\": 0.0,\n" +
                        "                    \"unit\": \"\"\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"name\": \"Gallocatechin\",\n" +
                        "                    \"amount\": 5.9,\n" +
                        "                    \"unit\": \"mg\"\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"caloricBreakdown\": {\n" +
                        "                \"percentProtein\": 0.0,\n" +
                        "                \"percentFat\": 0.0,\n" +
                        "                \"percentCarbs\": 100.0\n" +
                        "            },\n" +
                        "            \"weightPerServing\": {\n" +
                        "                \"amount\": 470,\n" +
                        "                \"unit\": \"g\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }")
                .when()
                .post("https://api.spoonacular.com/recipes/parseIngredients")
                .prettyPeek()
                .then()
                .headers("Content-Type", "application/json")
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("Italian");
    }




    //    @AfterEach
//    void tearDown() {
//        given()
//                .queryParam("hash", "a3da66460bfb7e62ea1c96cfa0b7a634a346ccbf")
//                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
//                .delete("https://api.spoonacular.com/mealplanner/geekbrains/items/" + id)
//                .then()
//                .statusCode(200);
//    }
}

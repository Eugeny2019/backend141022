package ru.gb;

import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FromPostmanTest extends AbstractTest {
    private static String id = "";
    private static String userName = "";
    private static String hash = "";
    private static String ShoppingListId = "";

    @Order(1)
    @Test
    void searchRecipesTest() {
        HashMap map = (HashMap) given()
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .prettyPeek()
                .then()
                .extract()
                .jsonPath()
                .getList("results")
                .get(0);
        id = map.get("id").toString();
    }

    @Order(2)
//    @ParameterizedTest
//    @MethodSource("getId")
    @Test()
    void GetRecipeInformationTest() {
        String url = getBaseUrl() + "/recipes/" + id + "/information";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertEquals(true, map.get("vegan"));
        ArrayList ingredients = (ArrayList) map.get("extendedIngredients");
        LinkedHashMap extendedIngredients = (LinkedHashMap) ingredients.get(0);
        Assertions.assertEquals("broccoli", extendedIngredients.get("name"));
    }

    @Order(3)
    @Test
    void TasteByID() {
        String url = getBaseUrl() + "/recipes/" + id + "/tasteWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertTrue((float) map.get("sweetness") < 50.0f);
    }

    @Order(4)
    @Test
    void EquipmentByID() {
        String url = getBaseUrl() + "/recipes/" + id + "/equipmentWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertTrue(((ArrayList) map.get("equipment")).size() > 0);
    }

    @Order(5)
    @Test
    void PriceBreakdownByID() {
        String url = getBaseUrl() + "/recipes/" + id + "/priceBreakdownWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        ArrayList ingredients = (ArrayList) map.get("ingredients");
        LinkedHashMap ingredient = (LinkedHashMap) ingredients.get(0);
        Assertions.assertTrue(((String) ingredient.get("name")).length() > 0);
        Assertions.assertTrue(((String) ingredient.get("image")).length() > 0);
        Assertions.assertTrue(((float) ingredient.get("price")) > 0);
        LinkedHashMap amount = (LinkedHashMap) ingredient.get("amount");
        LinkedHashMap metric = (LinkedHashMap) amount.get("metric");
        Assertions.assertTrue((float) metric.get("value") > 0.0f);
        Assertions.assertTrue(Objects.nonNull(metric.get("unit")));
    }

    @Order(6)
    @Test
    void registerUserTest() {
        HashMap map = (HashMap)
                given()
                        .body("{\n" +
                                "\"username\": " + properties.getProperty("username") + ",\n" +
                                "\"firstName\": " + properties.getProperty("firstName") + ",\n" +
                                "\"lastName\": " + properties.getProperty("lastName") + ",\n" +
                                "\"email\": " + properties.getProperty("email") +
                                "}\n")
                        .when()
                        .post(getBaseUrl() + "/users/connect")
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        userName = map.get("username").toString();
        hash = map.get("hash").toString();
    }

    @Order(7)
    @Test
    void AddToShoppingListTest() {
        HashMap map = (HashMap)
                given()
                        .body("""
                                {
                                "item": "1 package baking powder",
                                "aisle": "Baking",
                                "parse": true
                                }
                                """)
                        .queryParam("hash", hash)
                        .when()
                        .post(getBaseUrl() + "/mealplanner/" + userName + "/shopping-list/items")
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        ShoppingListId = map.get("id").toString();
        Assertions.assertEquals("Baking", map.get("aisle"));
    }

    @Order(8)
    @Test
    void GetToShoppingListTest() {
        HashMap map = (HashMap)
                given()
                        .queryParam("hash", hash)
                        .when()
                        .get(getBaseUrl() + "/mealplanner/" + userName + "/shopping-list")
                        .prettyPeek()
                        .then()
                        .extract()
                        .jsonPath()
                        .getMap("");
        ArrayList aisles = (ArrayList) map.get("aisles");
        LinkedHashMap aisle = (LinkedHashMap) aisles.get(0);
        Assertions.assertEquals("Baking", aisle.get("aisle"));
    }

    @Order(9)
    @Test
    void DeleteFromShoppingListTest() {
        given()
                .queryParam("hash", hash)
                .when()
                .delete(getBaseUrl() + "/mealplanner/" + userName + "/shopping-list/items/" + ShoppingListId)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}

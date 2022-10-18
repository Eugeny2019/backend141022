package ru.gb;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import java.util.*;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FromPostmanTest {
    private static String id = "hhyjj";

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Order(1)
    @Test
    void searchRecipesTest() {
        HashMap map = (HashMap) given()
                .log()
                .all()
                .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                .when()
                .get("https://api.spoonacular.com/recipes/complexSearch")
                .prettyPeek()
                .then()
                .statusCode(200)
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
        String url = "https://api.spoonacular.com/recipes/" + id + "/information";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .log()
                        .all()
                        .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertTrue(map.get("vegan").equals(true));
        ArrayList ingredients = (ArrayList) map.get("extendedIngredients");
        LinkedHashMap extendedIngredients = (LinkedHashMap) ingredients.get(0);
        Assertions.assertTrue(extendedIngredients.get("name").equals("broccoli"));
    }

    @Order(3)
    @Test
    void TasteByID() {
        String url = "https://api.spoonacular.com/recipes/" + id + "/tasteWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .log()
                        .all()
                        .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertTrue((float)map.get("sweetness") < 50.0f);
    }

    @Order(4)
    @Test
    void EquipmentByID() {
        String url = "https://api.spoonacular.com/recipes/" + id + "/equipmentWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .log()
                        .all()
                        .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getMap("");
        Assertions.assertTrue(((ArrayList)map.get("equipment")).size() > 0);
    }

    @Order(5)
    @Test
    void PriceBreakdownByID() {
        String url = "https://api.spoonacular.com/recipes/" + id + "/priceBreakdownWidget.json";
        System.out.println(id);
        System.out.println("url = " + url);
        HashMap map = (HashMap)
                given()
                        .log()
                        .all()
                        .queryParam("apiKey", "7f5fb9a7a9934c788b1bfc1bb437d108")
                        .when()
                        .get(url)
                        .prettyPeek()
                        .then()
                        .statusCode(200)
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
}

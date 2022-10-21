package ru.gb;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {
    static Properties properties = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String apiKey;
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;

    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/properties");
        properties.load(configFile);

        baseUrl = properties.getProperty("baseUrl");
        apiKey = properties.getProperty("apiKey");

        System.out.println(baseUrl +"\n" + apiKey);

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getApiKey() {
        return apiKey;
    }
}

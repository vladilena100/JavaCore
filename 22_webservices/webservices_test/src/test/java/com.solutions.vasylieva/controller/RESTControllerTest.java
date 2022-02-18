package com.solutions.vasylieva.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RESTControllerTest {

    private static String createUserJson = "{\n" +
            "    \"login\": \"test\",\n" +
            "    \"password\": \"test1\",\n" +
            "    \"passwordAgain\": \"test1\",\n" +
            "    \"email\": \"test@test\",\n" +
            "    \"firstName\": \"test\",\n" +
            "    \"lastName\": \"test\",\n" +
            "    \"birthday\": \"1990-11-11\",\n" +
            "    \"role\": {" +
            "\"name\":\"ADMIN\"\n" +
            "}" +
            "}";

    private static String updateUserJson = "{\n" +
            "    \"login\": \"test\",\n" +
            "    \"password\": \"NEWtest1\",\n" +
            "    \"passwordAgain\": \"NEWtest1\",\n" +
            "    \"email\": \"NEWtest@test\",\n" +
            "    \"firstName\": \"NEWtest\",\n" +
            "    \"lastName\": \"NEWtest\",\n" +
            "    \"birthday\": \"1990-11-11\",\n" +
            "    \"role\": {" +
            "\"name\":\"ADMIN\"\n" +
            "}" +
            "}";

    @BeforeClass
    public static void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://vasylieva-app";
        RestAssured.basePath = "/api";
    }

    @Test
    public void test1AddUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(createUserJson)
                .when()
                .post("/users")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("test", response.jsonPath().getString("login"));
        assertEquals("test@test", response.jsonPath().getString("email"));
        assertEquals("test", response.jsonPath().getString("firstName"));
        assertEquals("test", response.jsonPath().getString("lastName"));
        assertEquals("ADMIN", response.jsonPath().getString("role.name"));
    }

    @Test
    public void test2GetUserById() {
        when().get("/users/3")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("login", equalTo("test"));
    }

    @Test
    public void test3GetUserByLogin() {
        when().get("/users/userByLogin/test")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("login", equalTo("test"));
    }

    @Test
    public void test4GetUserByEmail() {
        when().get("/users/userByEmail/test@test")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("email", equalTo("test@test"));
    }

    @Test
    public void test5GetAllUsers() {
        when().get("/users")
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void test6UpdateUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(updateUserJson)
                .when()
                .put("/users")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("test", response.jsonPath().getString("login"));
        assertEquals("NEWtest@test", response.jsonPath().getString("email"));
        assertEquals("NEWtest", response.jsonPath().getString("firstName"));
        assertEquals("NEWtest", response.jsonPath().getString("lastName"));
        assertEquals("ADMIN", response.jsonPath().getString("role.name"));
    }

    @Test
    public void test7DeleteUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/users/3")
                .then()
                .extract().response();

        assertEquals(200, response.statusCode());
    }
}

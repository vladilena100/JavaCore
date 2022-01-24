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
            "    \"password\": \"test\",\n" +
            "    \"passwordRepeat\": \"test\",\n" +
            "    \"email\": \"test@test\",\n" +
            "    \"firstName\": \"test\",\n" +
            "    \"lastName\": \"test\",\n" +
            "    \"birthday\": \"1111-11-11\",\n" +
            "    \"role\": 2\n" +
            "}";

    private static String updateUserJson = "{\n" +
            "    \"login\": \"test\",\n" +
            "    \"password\": \"NEWtest\",\n" +
            "    \"passwordRepeat\": \"NEWtest\",\n" +
            "    \"email\": \"NEWtest@test\",\n" +
            "    \"firstName\": \"NEWtest\",\n" +
            "    \"lastName\": \"NEWtest\",\n" +
            "    \"birthday\": \"1111-11-11\",\n" +
            "    \"role\": 2\n" +
            "}";

    @BeforeClass
    public static void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost/api";
        RestAssured.basePath = "/users";
    }

    @Test
    public void test1AddUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(createUserJson)
                .when()
                .post("")
                .then()
                .extract().response();

        assertEquals(201, response.statusCode());
        assertEquals("test", response.jsonPath().getString("login"));
        assertEquals("test@test", response.jsonPath().getString("email"));
        assertEquals("test", response.jsonPath().getString("firstName"));
        assertEquals("test", response.jsonPath().getString("lastName"));
        assertEquals("ROLE_USER", response.jsonPath().getString("role.name"));
    }

    @Test
    public void test2GetUserById() {
        when().get("/3")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("login", equalTo("test"));
    }

    @Test
    public void test3GetUserByLogin() {
        when().get("by-login/test")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("id", equalTo(3));
    }

    @Test
    public void test4GetUserByEmail() {
        when().get("by-email/test@test")
                .then().statusCode(200)
                .assertThat()
                .contentType(ContentType.JSON)
                .body("id", equalTo(3));
    }

    @Test
    public void test5GetAllUsers() {
        when().get("/all")
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
                .put("/test")
                .then()
                .extract().response();

        assertEquals(202, response.statusCode());
        assertEquals("test", response.jsonPath().getString("login"));
        assertEquals("NEWtest@test", response.jsonPath().getString("email"));
        assertEquals("NEWtest", response.jsonPath().getString("firstName"));
        assertEquals("NEWtest", response.jsonPath().getString("lastName"));
        assertEquals("ROLE_USER", response.jsonPath().getString("role.name"));
    }

    @Test
    public void test7DeleteUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/3")
                .then()
                .extract().response();

        assertEquals(204, response.statusCode());
    }
}

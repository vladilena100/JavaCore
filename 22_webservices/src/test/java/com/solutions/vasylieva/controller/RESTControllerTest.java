package com.solutions.vasylieva.controller;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

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

//    @BeforeClass
//    public static void setUp() {
//        RestAssured.port = 8080;
//        RestAssured.baseURI = "http://localhost/api";
//        RestAssured.basePath = "/users";
//    }

    @Test
    public void findAllUsers() {
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void getUserBuEmail() {
    }

    @Test
    public void getUserBuLogin() {
    }

    @Test
    public void addUser() {
    }

    @Test
    public void editUser() {
    }

    @Test
    public void removeUser() {
    }
}
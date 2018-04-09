package com.thoughtworks.felix.training.picture.interfaces.rest;

import com.thoughtworks.felix.training.picture.support.ApiTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.thoughtworks.felix.training.picture.support.TestHelper.readFileFrom;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class ImagesApiTest extends ApiTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesApiTest.class);
    private static final String CREATE_IMAGE_PATH = "/images";

    @Test
    public void should_201_when_create_image_success() {
        given()
                .multiPart(readFileFrom("rails.png"))
                .when()
                .post(CREATE_IMAGE_PATH)
                .then()
                .statusCode(201)
                .header("Location", containsString("images/"));
    }
}
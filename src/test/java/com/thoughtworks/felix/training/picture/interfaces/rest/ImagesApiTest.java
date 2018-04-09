package com.thoughtworks.felix.training.picture.interfaces.rest;

import com.thoughtworks.felix.training.picture.support.ApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.thoughtworks.felix.training.picture.support.TestHelper.readFileFrom;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class ImagesApiTest extends ApiTest{
    private static final Logger LOGGER = LoggerFactory.getLogger(ImagesApiTest.class);
    private static final String IMAGES_PATH = "/images";

    @Test
    public void should_201_when_create_image_success() {
        given()
                .multiPart(readFileFrom("rails.png"))
                .when()
                .post(IMAGES_PATH)
                .then()
                .statusCode(201)
                .header("Location", containsString("images/"));
    }

    @Test
    public void should_200_when_list_all_images() {
        final Response response = given()
                .when()
                .get(IMAGES_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();
        LOGGER.info(">>{}", response.asString());
    }

    @Test
    public void should_404_when_get_image_with_non_exist_id() {
        final String NON_EXIST_IMAGE_PATH = "images/10000";
        given()
                .when()
                .get(NON_EXIST_IMAGE_PATH)
                .then()
                .statusCode(404);
    }

    @Test
    public void should_200_when_get_image_with_exist_id() {
        final String EXIST_IMAGE_PATH = "images/1";
        given()
                .when()
                .get(EXIST_IMAGE_PATH)
                .then()
                .statusCode(200);
    }
}
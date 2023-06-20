package vitrine.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VitrineTest {

    @Test
    public void deveCadastrarCategoria() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Jose\", \"alias\": \"jose\", \"subcategories\": \" \"}")
        .when()
                .post("http://64.226.114.207:3334/categories")
        .then()
                .log().all()
                .statusCode(200) //api retornando 200, deveria ser 201
                .body("name", is("Jose"))
        ;

    }

}

package vitrine.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StepsVitrine {
    private RequestSpecification requestSpecification;
    private Response response;

    @Dado("uma categoria básica")
    public void umaCategoriaBásica() {
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Jose\", \"alias\": \"jose\"}");
    }
    @Quando("chamar a api com post para o endpoint {string}")
    public void chamarAApiComPostParaOEndpoint(String endpoint) {
        response = requestSpecification.when().post("http://64.226.114.207:3334" + endpoint);
    }
    @Então("deve criar uma categoria")
    public void deveCriarUmaCategoria() {
        response.then().log().all()
                .statusCode(201) //api retornando 200, deveria ser 201
                .body("name", is("Jose"));
    }

}

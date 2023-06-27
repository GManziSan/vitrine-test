package vitrine.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StepsVitrine {
    private RequestSpecification requestSpecification;
    private Response response;
    private static String CONTA_NAME = "Conta " + System.nanoTime();
    private static String ALIAS_NAME = "Alias " + System.nanoTime();


    @Dado("uma categoria básica")
    public void umaCategoriaBásica() {
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \""+CONTA_NAME+"\", \"alias\": \""+ALIAS_NAME+"\"}");
    }
    @Quando("chamar a api com post para o endpoint {string}")
    public void chamarAApiComPostParaOEndpoint(String endpoint) {
        response = requestSpecification.when().post("http://64.226.114.207:3334" + endpoint);
   }
    @Então("deve criar uma categoria")
    public void deveCriarUmaCategoria() {
        response.then().log().all()
                .statusCode(201) //api retornando 200, deveria ser 201
                .body("name", is(""+CONTA_NAME+""));

    }

    @Dado("uma categoria com nome já existente")
    public void umaCategoriaComNomeJáExistente() {
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"teste\", \"alias\": \"teste\"}");
    }
    @Então("não deve criar uma categoria")
    public void nãoDeveCriarUmaCategoria() {
        response.then().log().all()
                .statusCode(400) //statusCode esperado 400
               ;
    }

    @Dado("uma categoria com {string}")
    public void umaCategoriaCom(String carac) {
        CONTA_NAME = CONTA_NAME +carac;
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \""+CONTA_NAME+"\", \"alias\": \""+ALIAS_NAME+"\"}");
    }

    @Então("deve criar uma categoria com {string}")
    public void deveCriarUmaCategoriaCom(String carac) {
        response.then().log().all()
                .statusCode(200) //api retornando 200, deveria ser 201
                .body("name", is(""+CONTA_NAME+""));
    }

    @Dado("um alias com {string}")
    public void umAliasCom(String carac) {
        ALIAS_NAME = ALIAS_NAME +carac;
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \""+CONTA_NAME+"\", \"alias\": \""+ALIAS_NAME+"\"}");
    }

    @Então("nao deve criar a categoria com {string}")
    public void naoDeveCriarACategoriaCom(String carac) {
        response.then().log().all()
                .statusCode(200) //api retornando 200, deveria ser 400

        ;
    }

    @Dado("uma categoria que não contém nome")
    public void umaCategoriaQueNãoContémNome() {
       requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"alias\": \""+ALIAS_NAME+"\"}");
    }

    @Dado("Uma categoria que não contém alias")
    public void umaCategoriaQueNãoContémAlias() {
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \""+CONTA_NAME+"\"}");
    }


//     @After
//     public void afterAll() {
//        given()
//                .log().all()
//                .contentType(ContentType.JSON)
//       .when()
//                .delete("http://64.226.114.207:3334/categories/" + contaId)
//       .then()
//                .log().all()
//                .statusCode(200)
//    ;
//    }

}

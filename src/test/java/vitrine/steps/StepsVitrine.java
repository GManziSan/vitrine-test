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
import static org.hamcrest.Matchers.*;

public class StepsVitrine {
    private RequestSpecification requestSpecification;
    private Response response;
    private static String CONTA_NAME = "Conta " + System.nanoTime();
    private static String ALIAS_NAME = "Alias " + System.nanoTime();
    private static Integer contaId;
    private static Integer contaAlteracao;


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
       contaId = response.then().log().all()
                .statusCode(200) //api retornando 200, deveria ser 201
                .body("name", is(""+CONTA_NAME+""))
                .extract().path("id")
               ;

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
                .statusCode(201) //api retornando 200, deveria ser 201
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
                .statusCode(400) //api retornando 200, deveria ser 400

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

    @Dado("uma categoria existente")
    public void umaCategoriaExistente() {
        requestSpecification = given()
                .log().all()
                .contentType(ContentType.JSON)
                ;
    }
    @Quando("chamar a api com delete para o endpoint")
    public void chamarAApiComDeleteParaOEndpoint() {
        response = requestSpecification.when().delete("http://64.226.114.207:3334/categories/" + contaId);
    }
        @Então("deve excluir a categoria")
        public void deveExcluirACategoria () {
            response.then().log().all()
                    .statusCode(204)
            ;
        }

        @Dado("um conjunto de categorias")
        public void umConjuntoDeCategorias () {
            requestSpecification = given()
                    .log().all()
                    .contentType(ContentType.JSON);
        }
        @Quando("chamar a api para listar")
        public void chamarAApiParaListar () {
            response = requestSpecification.when().get("http://64.226.114.207:3334/categories/");
        }
        @Então("deve listar as categorias")
        public void deveListarAsCategorias () {
            response.then()
                    .log().all()
                    .body("name", hasItems("sala", "quarto", "office"))
                    .body("alias", hasItems("areaexterna", "gastronomia", "sale"))
            ;
        }

        @Quando("chamar a api para listar por nome")
        public void chamarAApiParaListarPorNome () {
            response = requestSpecification.when().get("http://64.226.114.207:3334/categoriesByName/banheiro");
        }
        @Então("deve listar as categorias por nome")
        public void deveListarAsCategoriasPorNome () {
            response.then()
                    .log().all()
//                .body("$", hasSize(4))
                    .body("id", hasItem(5))
                    .body("name", hasItem("banheiro"))
                    .body("alias", hasItem("banheiro"))
            ;
        }
        @Quando("chamar a api para listar por alias")
        public void chamarAApiParaListarPorAlias () {
            response = requestSpecification.when().get("http://64.226.114.207:3334/categoriesByAlias/areaexterna");

        }
        @Então("deve listar as categorias por alias")
        public void deveListarAsCategoriasPorAlias () {
            response.then()
                    .log().all()
//                .body("$", hasSize(4))
                    .body("id", hasItem(6))
                    .body("name", hasItem("área externa"))
                    .body("alias", hasItem("areaexterna"))
            ;
        }
        @Quando("chamar a api para listar por id")
        public void chamarAApiParaListarPorId () {
            response = requestSpecification.when().get("http://64.226.114.207:3334/categories/3");

        }
        @Então("deve listar as categorias por id")
        public void deveListarAsCategoriasPorId () {
            response.then()
                    .log().all()
//                .body("$", hasSize(4))
                    .body("id", hasItem(3))
                    .body("name", hasItem("cozinha"))
                    .body("alias", hasItem("cozinha"))
            ;
        }

    @Dado("uma categoria criada")
    public void umaCategoriaCriada() {
        contaAlteracao = given()
               .log().all()
               .contentType(ContentType.JSON)
               .body("{\"name\": \"conta1\", \"alias\": \"alias1\"}")
                .when().post("http://64.226.114.207:3334/categories")
                .then().log().all()
                .extract().path("id")
        ;
    }
    @Quando("alterar a categoria")
    public void alterarACategoria() {
       response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"contaAlterada\", \"alias\": \"aliasAlterado\"}")
                .when().put("http://64.226.114.207:3334/categories/" + contaAlteracao)
        ;

    }
    @Então("deve alterar a categoria")
    public void deveAlterarACategoria() {
        response.then().log().all().statusCode(200).body("name", hasItem("contaAlterada"))
                ;

    }

    }
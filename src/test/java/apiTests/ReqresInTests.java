package apiTests;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresInTests {

    UsersCreat user = new UsersCreat("morpheus", "leader");
    RegSuc email = new RegSuc("eve.holt@reqres.in", "pistol");


    @Test
    void createTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body(
                        "name", is(user.getName()),
                        "job", is(user.getJob()),
                        "id", notNullValue(),
                        "createdAt", notNullValue()
                );
    }

    @Test
    void createEmptyBodyTest() {
        given()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(415);
    }

    @Test
    void deleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    void registerSuccessfulTest() {

        given()
                .contentType(ContentType.JSON)
                .body(email)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "token", notNullValue()
                );
    }

    @Test
    void registerEmptyBodylTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body(
                        "error", is("Missing email or username")
                );
    }
}


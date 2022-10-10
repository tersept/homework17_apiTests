package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ReqresInTests {
    UserNameJobData user = new UserNameJobData("morpheus", "leader");
    UserEmailData email = new UserEmailData("eve.holt@reqres.in", "pistol");

    final String baseURI = "https://reqres.in/api";

    @BeforeAll
    void setUp() {
        RestAssured.baseURI = baseURI;
    }

    @DisplayName("Тест на успешное создание пользователя")
    @Test
    void userCreationSuccessTest() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body(
                        "name", is(user.getName()),
                        "job", is(user.getJob()),
                        "id", notNullValue(),
                        "createdAt", notNullValue()
                );
    }

    @DisplayName("Тест на 415 ошибку при пустом body")
    @Test
    void createEmptyBodyNegativeTest() {
        given()
                .when()
                .post("/users")
                .then()
                .statusCode(415);
    }

    @DisplayName("Тест на успешное удаление пользователя")
    @Test
    void userDeletionSuccessTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @DisplayName("Тест на успешную регистрацию")
    @Test
    void userRegistrationSuccessTest() {

        given()
                .contentType(ContentType.JSON)
                .body(email)
                .when()
                .post("/register")
                .then()
                .statusCode(200)
                .body(
                        "id", notNullValue(),
                        "token", notNullValue()
                );
    }

    @DisplayName("Тест на 400 ошибку с пустым body при регистрации")
    @Test
    void registerEmptyBodylTest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/register")
                .then()
                .statusCode(400)
                .body(
                        "error", is("Missing email or username")
                );
    }
}


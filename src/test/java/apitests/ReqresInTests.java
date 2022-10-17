package apitests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.CreatUserResponseLombokModel;
import models.lombok.CreateUserBodyLombokModel;
import models.pojo.CreatUserResponsePojoModel;
import models.pojo.CreateUserBodyPojoModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.UserCreatSpecs.*;
import static specs.UserDeletetSpecs.deleteUserResponseSpec;
import static specs.UserRegistrationSpecs.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ReqresInTests {
    UserEmailData email = new UserEmailData("eve.holt@reqres.in", "pistol");

//    @DisplayName("Тест на успешное создание пользователя lombok модель")
//    @Test
//    void userCreationSuccessTest() {
//
//        CreateUserBodyLombokModel body = new CreateUserBodyLombokModel();
//        body.setName("morpheus");
//        body.setJob("leader");
//
//        CreateUserBodyLombokModel response = given()
//                .spec(creatUserRequestSpec)
//                .body(body)
//                .when()
//                .post()
//                .then()
//                .spec(creatUserResponseSpec)
//                .body(
//                        "id", is(not(emptyOrNullString())),
//                        "createdAt", notNullValue()
//                )
//                .extract()
//                .as(CreateUserBodyLombokModel.class);
//        assertThat(response.getName()).isEqualTo("morpheus");
//        assertThat(response.getJob()).isEqualTo("leader");
//    }

    @DisplayName("Тест на успешное создание пользователя pojo модель")
    @Test
    void userCreationSuccessPojoTest() {

        CreateUserBodyPojoModel body = new CreateUserBodyPojoModel();
        body.setName("morpheus");
        body.setJob("leader");

        CreatUserResponsePojoModel response = given()
                .filter(new AllureRestAssured())
                .spec(creatUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(creatUserResponseSpec)
                .body(
                        "id", is(not(emptyOrNullString())),
                        "createdAt", notNullValue()
                )
                .extract()
                .as(CreatUserResponsePojoModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @DisplayName("Тест на 415 ошибку при пустом body")
    @Disabled
    @Test
    void createEmptyBodyNegativeTest() {
        given()
                .filter(new AllureRestAssured())
                .spec(creatUserRequestSpec)
                .body(nullValue())
                .post()
                .then()
                .spec(creatUser415ResponseSpec);
    }

    @DisplayName("Тест на успешное удаление пользователя")
    @Test
    void userDeletionSuccessTest() {
        given()
                .filter(new AllureRestAssured())
                .when()
                .delete("/users/2")
                .then()
                .spec(deleteUserResponseSpec);
    }

    @DisplayName("Тест на успешную регистрацию pojo модель")
    @Test
    void userRegistrationPojoSuccessTest() {
        CreateUserBodyPojoModel body = new CreateUserBodyPojoModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        CreatUserResponsePojoModel response = given()
                .spec(registrationUserRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(registrationUserResponseSpec)
                .extract()
                .as(CreatUserResponsePojoModel.class);
        assertThat(response.getId()).isEqualTo("4");
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @DisplayName("Тест на 400 ошибку с пустым body при регистрации")
    @Test
    void registerEmptyBodylTest() {
        given()
                .spec(registrationUserRequestSpec)
                .when()
                .post()
                .then()
                .spec(registrationUser400ResponseSpec);
    }
}


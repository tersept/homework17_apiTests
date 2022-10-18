package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserListSpecs {
    public static RequestSpecification userListRequestSpec = with()
            .baseUri("https://reqres.in/api")
            .basePath("/users?page=2")
            .log().all()
            .contentType(ContentType.JSON);
    public static ResponseSpecification userListResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}

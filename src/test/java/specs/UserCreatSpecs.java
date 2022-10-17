package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserCreatSpecs {
    public static RequestSpecification creatUserRequestSpec = with()
            .baseUri("https://reqres.in/api")
            .basePath("/users")
            .log().all()
            .contentType(ContentType.JSON);
    public static ResponseSpecification creatUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
    public static ResponseSpecification creatUser415ResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(415)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}

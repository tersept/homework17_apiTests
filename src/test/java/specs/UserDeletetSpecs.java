package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class UserDeletetSpecs {
    public static ResponseSpecification deleteUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}

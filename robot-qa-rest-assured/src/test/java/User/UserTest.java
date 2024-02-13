package User;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.example.User.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;

public class UserTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @BeforeEach
    public void setRequest() {
        request = RestAssured.given().contentType("application/json");
    }

    @Test
    public void testGetToken_withValidUser(){
        User user = new User("victor", "password");
        request
                .body(user)
                .when().post("/robots/token")
                .then().assertThat()
                .statusCode(200)
                .contentType(containsString("text/plain"));
    }

    @Test
    public void testGetToken_withInvalidUser(){
        User user = new User("invalid", "password");
        request
                .body(user)
                .when().post("/robots/token")
                .then().assertThat()
                .statusCode(403);
    }

    @Test
    public void testGetToken_withInvalidPassword(){
        User user = new User("victor", "invalid");
        request
                .body(user)
                .when().post("/robots/token")
                .then().assertThat()
                .statusCode(403);
    }

}

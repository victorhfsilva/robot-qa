package Robot;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.User.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class RobotTest {

    public static RequestSpecification request;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @BeforeEach
    public void setRequest() {
        request = RestAssured.given()
                .contentType("application/json");
    }

    @Test
    public void getRobots_withValidUserVictor() {
        User user = new User("victor", "password");
        Response response = request
                        .body(user)
                        .when().post("/robots/token");

        String token = response.getBody().asString();

        request
                .header("Authorization", "Bearer " + token)
                .when().get("/robots")
                .then().assertThat().statusCode(200)
                .and().body("[0].id", equalTo(1))
                .and().body("[0].name", equalTo("13DC-2"))
                .and().body("[0].description", equalTo("Created to fulfill all your desires."))
                .and().body("[1].id", equalTo(2))
                .and().body("[1].name", equalTo("2345-1"))
                .and().body("[1].description", equalTo("Created to make you feel human."));

    }

    @Test
    public void getRobots_withValidUserHugo() {
        User user = new User("hugo", "password");
        Response response = request
                .body(user)
                .when().post("/robots/token");

        String token = response.getBody().asString();

        request
                .header("Authorization", "Bearer " + token)
                .when().get("/robots")
                .then().assertThat().statusCode(200)
                .and().body("[0].id", equalTo(2))
                .body("[0].name", equalTo("2345-1"))
                .body("[0].description", equalTo("Created to make you feel human."))
                .body("[1].id", equalTo(3))
                .body("[1].name", equalTo("123D-3"))
                .body("[1].description", equalTo("Created to teach you all the secrets of the universe."));

    }

    @Test
    public void getRobots_withValidUserAdmin() {
        User user = new User("admin", "password123");
        Response response = request
                .body(user)
                .when().post("/robots/token");

        String token = response.getBody().asString();

        request
                .header("Authorization", "Bearer " + token)
                .when().get("/robots")
                .then().assertThat().statusCode(200)
                .and()
                .body("[0].id", equalTo(3))
                .body("[0].name", equalTo("123D-3"))
                .body("[0].description", equalTo("Created to teach you all the secrets of the universe."))
                .body("[1].id", equalTo(1))
                .body("[1].name", equalTo("13DC-2"))
                .body("[1].description", equalTo("Created to fulfill all your desires."))
                .body("[2].id", equalTo(2))
                .body("[2].name", equalTo("2345-1"))
                .body("[2].description", equalTo("Created to make you feel human."));
    }

    @Test
    public void getRobots_withInvalidUser() {
        User user = new User("invalid", "password123");
        Response response = request
                .body(user)
                .when().post("/robots/token");

        String token = response.getBody().asString();

        request
                .header("Authorization", "Bearer " + token)
                .when().get("/robots")
                .then().assertThat().statusCode(403);
    }

    @Test
    public void getRobots_withInvalidPassword() {
        User user = new User("admin", "invalid");
        Response response = request
                .body(user)
                .when().post("/robots/token");

        String token = response.getBody().asString();

        request
                .header("Authorization", "Bearer " + token)
                .when().get("/robots")
                .then().assertThat().statusCode(403);
    }

}

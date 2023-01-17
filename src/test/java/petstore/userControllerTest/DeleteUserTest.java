package petstore.userControllerTest;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.petstore.controllers.users.entity.User;
import utils.TestBase;

import static services.petstore.controllers.users.endpoint.Endpoints.USERS_DELETE_ENDPOINT;

public class DeleteUserTest extends TestBase {

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    @DisplayName("Delete non-existent. User not found. Status code 404")
    public void testDeleteUser404() {
        User nonExistentUser = User.builder()
                .username("nonExistentUserName")
                .build();
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .and()
                .log()
                .all()
                .delete(USERS_DELETE_ENDPOINT + nonExistentUser.getUsername())
                .then()
                .log()
                .ifError()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}

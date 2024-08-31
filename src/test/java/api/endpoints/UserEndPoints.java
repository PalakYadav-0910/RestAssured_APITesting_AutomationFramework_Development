package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User user) {

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user)

                .when()
                .post(post_url);

        return response;

    }

    public static Response getUser(String username) {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParams("username", username)

                .when()
                .get(get_url);

        return response;
    }

    public static Response updateUser(User user, String username) {

        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParams("username", username)
                .body(user)

                .when()
                .put(put_url);

        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParams("username", username)
                
                .when()
                .delete(delete_url);

        return response;
    }

}

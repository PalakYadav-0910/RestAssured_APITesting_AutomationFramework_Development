package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker;
    User userPayload;

    @BeforeClass
    public void generateTestData() {

        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void testCreateUser() {

        Response response = UserEndPoints.createUser(userPayload);

        System.out.println("Create User");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 2)
    public void testGetUserData() {

        Response response = UserEndPoints.getUser(this.userPayload.getUsername());

        System.out.println("Get User Data");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void testUpdateUser() {

        userPayload.setFirstName(faker.name().firstName());
        Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

        //Read User Data to check if first name is updated
        Response responsePostUpdate = UserEndPoints.getUser(this.userPayload.getUsername());

        System.out.println("After Update User Data.");

        responsePostUpdate.then().log().all();

    }

    @Test(priority = 4)
    public void testDeleteUser() {

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());

        System.out.println("Delete User Data.");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}

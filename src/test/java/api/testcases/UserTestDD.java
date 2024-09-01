package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestDD {

    @Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
    public void testCreateUserDD(String userId, String userName, String fname, String lname, String email, String password, String phone) {

        User user = new User();
        user.setId(Integer.parseInt(userId));
        user.setUsername(userName);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);

        Response response = UserEndPoints.createUser(user);

        System.out.println("Create User");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Test(priority = 2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testGetUserDD(String username) {

        Response response = UserEndPoints.getUser(username);

        System.out.println("Read User Data");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }


    @Test(priority = 3, dataProvider = "AllData", dataProviderClass = DataProviders.class)
    public void testUpdateUserDD(String fname) {

        User user = new User();
        user.setFirstName(fname);

        Response response = UserEndPoints.updateUser(user, user.getUsername());

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

        Response responsePostUpdate = UserEndPoints.getUser(user.getUsername());

        System.out.println("After Update User Data");

        responsePostUpdate.then().log().all();

    }


    @Test(priority = 4, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
    public void testDeleteUserDD(String username) {

        Response response = UserEndPoints.deleteUser(username);

        System.out.println("Delete User Data");

        //Log Response
        response.then().log().all();

        //Validation
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}

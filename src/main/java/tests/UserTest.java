package tests;

import contracts.AccountController;
import contracts.Configuration;
import io.restassured.response.Response;
import objects.user.*;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserTest {

    User user = new User("Damian16", "Qwerty123!");
    static String userID;
    static String token;
    private void setUser() {
        Response setUserResponse = AccountController.setUser(user);
        UserResponse userBody = setUserResponse.then().extract().body().as(UserResponse.class);
//        Response setUserResponse = AccountController.setUser(user);
//        int statusCode = setUserResponse.statusCode();
//        System.out.println(statusCode);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userBody.getUserID()).isNotEqualTo("");
        userID = userBody.getUserID();
        softAssertions.assertThat(userBody.getUsername()).isEqualTo(user.getUserName());
        for (UserBooks item : userBody.getBooks()) {
            softAssertions.assertThat(item.getIsbn()).isEmpty();
            softAssertions.assertThat(item.getTitle()).isEmpty();
            softAssertions.assertThat(item.getSubTitle()).isEmpty();
            softAssertions.assertThat(item.getAuthor()).isEmpty();
            softAssertions.assertThat(item.getPublish_date()).isEmpty();
            softAssertions.assertThat(item.getPublisher()).isEmpty();
            softAssertions.assertThat(item.getPages()).isEqualTo(0);
            softAssertions.assertThat(item.getDescription()).isEmpty();
            softAssertions.assertThat(item.getWebsite()).contains("http");
        }
        softAssertions.assertAll();
    }

    private void generateToken() {
        Response generateTokenUserResponse = AccountController.generateToken(user);
        UserGenerateToken generateTokenInfo = generateTokenUserResponse.then().extract().body().as(UserGenerateToken.class);
        token = generateTokenInfo.getToken();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(generateTokenInfo.getStatus()).isEqualTo("Success");
        softAssertions.assertAll();
    }

    @Test
    public void authorizedUser()
    {
        setUser();
        generateToken();
        Response authorizedUserResponse = AccountController.authorized(user);
        String authorization = authorizedUserResponse.then().extract().body().htmlPath().get().children().get(0).value();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(authorization).isEqualTo("true");
        softAssertions.assertAll();
    }

    @AfterClass
    public static void deleteUser() {
        Response deleteUserResponse = AccountController.deleteUser(userID,token);
//        UserDelete deleteUserBody = deleteUserResponse.then().extract().body().as(UserDelete.class);
        System.out.println(deleteUserResponse.getStatusCode());
        System.out.println(deleteUserResponse.getBody());
    }
}

package tests;

import contracts.AccountController;
import io.restassured.response.Response;
import objects.user.User;
import objects.user.UserGenerateToken;
import objects.user.UserResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.Test;

public class UserTest {

    static User user = new User("Damian17", "Qwerty123!");
    private static String userID;
    private static String token;

    private static UserResponse setUser() {
        Response response = AccountController.setUser(user);
        UserResponse userBody = response.then().extract().body().as(UserResponse.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userBody.getUserID()).isNotEqualTo("");
        softAssertions.assertThat(userBody.getUsername()).isEqualTo(user.getUserName());
        softAssertions.assertThat(userBody.getBooks().size()).isEqualTo(0);
        softAssertions.assertAll();
        return userBody;
    }

    private static UserGenerateToken generateToken() {
        Response response = AccountController.generateToken(user);
        UserGenerateToken generateTokenInfo = response.then().extract().body().as(UserGenerateToken.class);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(generateTokenInfo.getStatus()).isEqualTo("Success");
        softAssertions.assertThat(generateTokenInfo.getResult()).isEqualTo("User authorized successfully.");
        softAssertions.assertAll();

        return generateTokenInfo;
    }

    @Test
    public void authorizedUser() {
        userID = setUser().getUserID();
        token = generateToken().getToken();
        Response response = AccountController.authorized(user, token);
        String authorization = response.then().extract().body().as(String.class);

        Assertions.assertThat(authorization).isEqualTo("true");
    }

    @AfterClass
    public static void deleteUser() {
        AccountController.deleteUser(userID, token).then().statusCode(204);
    }
}

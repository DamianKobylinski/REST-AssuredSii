package contracts;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import objects.user.User;

import static io.restassured.RestAssured.given;

public class AccountController {
    public static Response setUser(User user) {
        Configuration configuration = new Configuration();
        return given().spec(configuration.getRequestSpecification()).contentType(ContentType.JSON).body(user).post("/Account/v1/User");
    }
    public static Response generateToken(User user)
    {
        Configuration configuration = new Configuration();
        return given().spec(configuration.getRequestSpecification()).contentType(ContentType.JSON).body(user).post("/Account/v1/GenerateToken");
    }

    public static Response authorized(User user) {
        Configuration configuration = new Configuration();
        return given().spec(configuration.getRequestSpecification()).contentType(ContentType.JSON).body(user).post("/Account/v1/Authorized");
    }

    public static Response deleteUser(String userID,String bearerToken) {
        Configuration configuration = new Configuration();
        return given().spec(configuration.getRequestSpecification()).header("Authorization","Bearer " + bearerToken).contentType(ContentType.JSON).post("/Account/v1/User/"+userID);
    }
}

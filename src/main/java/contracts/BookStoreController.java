package contracts;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookStoreController {

    static Configuration configuration = new Configuration();

    public static Response getBooks() {
        return given().spec(configuration.getRequestSpecification()).when().get("/tests.BookStoreTest/v1/objects.book.Books");
    }
}

package tests;

import contracts.BookStoreController;
import objects.book.Book;
import objects.book.Books;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BookStoreTest {
    
    @Test
    public void getBooks() {
        Books getBooksResponse = BookStoreController.getBooks().then().extract().body().as(Books.class);
        SoftAssertions softAssert = new SoftAssertions();
        for (Book item : getBooksResponse.getBooks()) {
            softAssert.assertThat(item.getIsbn()).isNotEmpty();
            softAssert.assertThat(item.getTitle()).isNotEmpty();
            softAssert.assertThat(item.getSubTitle()).isNotEmpty();
            softAssert.assertThat(item.getAuthor()).isNotEmpty();
            softAssert.assertThat(item.getPublish_date()).isNotEmpty();
            softAssert.assertThat(item.getPublisher()).isNotEmpty();
            softAssert.assertThat(item.getPages()).isGreaterThan(0);
            softAssert.assertThat(item.getDescription()).isNotEmpty();
            softAssert.assertThat(item.getWebsite()).contains("http");
        }
        softAssert.assertAll();
    }
}

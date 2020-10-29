package objects.user;

import objects.book.Books;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {

    private String userID;
    private String username;
    private List<UserBooks> books = new ArrayList<>();

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserBooks> getBooks() {
        return books;
    }

    public void setBooks(List<UserBooks> books) {
        this.books = books;
    }
}

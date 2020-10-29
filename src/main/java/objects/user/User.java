package objects.user;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;

    List<UserBooks> books = new ArrayList<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserBooks> getBooks() {
        return books;
    }

    public void setBooks(List<UserBooks> books) {
        this.books = books;
    }
}

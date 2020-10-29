package objects.book;

import objects.book.Book;

import java.util.ArrayList;
import java.util.List;

public class Books {
    List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

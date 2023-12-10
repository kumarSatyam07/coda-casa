import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String username;
    private List<Book> borrowedBooks;

    public User(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
}

public class LibraryManagementSystem {
    private Map<String, Book> books;
    private Map<String, User> users;

    public LibraryManagementSystem() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.put(title, book);
    }

    public void addUser(String username) {
        User user = new User(username);
        users.put(username, user);
    }

    public void borrowBook(String username, String title) {
        if (users.containsKey(username) && books.containsKey(title)) {
            User user = users.get(username);
            Book book = books.get(title);

            if (book.isAvailable()) {
                user.borrowBook(book);
                book.setAvailable(false);
                System.out.println(username + " borrowed " + title + ".");
            } else {
                System.out.println("Sorry, " + title + " is not available.");
            }
        } else {
            System.out.println("Invalid username or book title.");
        }
    }

    public void returnBook(String username, String title) {
        if (users.containsKey(username) && books.containsKey(title)) {
            User user = users.get(username);
            Book book = books.get(title);

            if (user.getBorrowedBooks().contains(book)) {
                user.returnBook(book);
                book.setAvailable(true);
                System.out.println(username + " returned " + title + ".");
            } else {
                System.out.println(username + " did not borrow " + title + ".");
            }
        } else {
            System.out.println("Invalid username or book title.");
        }
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books.values()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() +
                    (book.isAvailable() ? " (Available)" : " (Not Available)"));
        }
    }

    public void displayUsers() {
        System.out.println("Library users:");
        for (User user : users.values()) {
            System.out.println("User: " + user.getUsername());
            System.out.println("Borrowed Books:");
            for (Book book : user.getBorrowedBooks()) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        library.addBook("The Catcher in the Rye", "J.D. Salinger");
        library.addBook("To Kill a Mockingbird", "Harper Lee");
        library.addBook("1984", "George Orwell");

        library.addUser("Alice");
        library.addUser("Bob");

        library.borrowBook("Alice", "The Catcher in the Rye");
        library.borrowBook("Bob", "To Kill a Mockingbird");

        library.returnBook("Alice", "The Catcher in the Rye");

        library.displayBooks();
        System.out.println();
        library.displayUsers();
    }
}

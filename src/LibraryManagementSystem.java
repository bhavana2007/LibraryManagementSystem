import java.util.*;
class Book {
    int id;
    String title;
    String author;
    int quantity;
    int issued;

    Book(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.issued = 0;
    }

    public String toString() {
        return "Book ID: " + id + " | Title: " + title + " | Author: " + author +
               " | Quantity: " + quantity + " | Issued: " + issued;
    }
}

class Library {
    HashMap<Integer, Book> books = new HashMap<>();

    void addBook(int id, String title, String author, int quantity) {
        if (books.containsKey(id)) {
            System.out.println("Book ID already exists!");
        } else {
            books.put(id, new Book(id, title, author, quantity));
            System.out.println("✅ Book added successfully!");
        }
    }

    void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n------ BOOK LIST ------");
        for (Book b : books.values()) {
            System.out.println(b);
        }
    }

    void searchBook(String keyword) {
        boolean found = false;
        for (Book b : books.values()) {
            if (b.title.toLowerCase().contains(keyword.toLowerCase()) ||
                b.author.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    void issueBook(int id) {
        if (books.containsKey(id)) {
            Book b = books.get(id);
            if (b.quantity > b.issued) {
                b.issued++;
                System.out.println("📚 Book issued successfully!");
            } else {
                System.out.println("❌ No copies available to issue.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    void returnBook(int id) {
        if (books.containsKey(id)) {
            Book b = books.get(id);
            if (b.issued > 0) {
                b.issued--;
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("This book was not issued.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    lib.addBook(id, title, author, qty);
                    break;

                case 2:
                    lib.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    lib.searchBook(keyword);
                    break;

                case 4:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    lib.issueBook(issueId);
                    break;

                case 5:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

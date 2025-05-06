package main.controller;

import main.io.BookEditor;
import main.io.MenuPrinter;
import main.io.View;
import main.model.Book;
import main.repository.BookRepository;
import main.repository.BookRepositoryBinaryImpl;
import main.repository.BookRepositoryJSONImpl;
import main.repository.BookRepositoryTextImpl;
import main.service.BookService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookController {
    private final BookService service = new BookService();
    private final View view = new View();
    private final Scanner scanner = new Scanner(System.in);
    private final MenuPrinter menuOptions = new MenuPrinter();
    private final BookRepository textRepo = new BookRepositoryTextImpl();
    private final BookRepository binaryRepo = new BookRepositoryBinaryImpl();
    private final BookRepository jsonRepo = new BookRepositoryJSONImpl();
    private final BookEditor bookEditor = new BookEditor();


    public void processBooks(List<Book> initialBooks) {
        label:
        while (true) {
            menuOptions.menuOptions();
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> {
                    view.prompt("Enter author name: ");
                    assert initialBooks != null;
                    view.showBooks(service.findBooksByAuthor(initialBooks, scanner.nextLine()));
                }
                case 2 -> {
                    view.prompt("Enter publisher: ");
                    assert initialBooks != null;
                    view.showBooks(service.findBooksByPublisher(initialBooks, scanner.nextLine()));
                }
                case 3 -> {
                    view.prompt("Enter min pages: ");
                    assert initialBooks != null;
                    view.showBooks(service.findBooksByMinPages(initialBooks, scanner.nextInt()));
                    scanner.nextLine();
                }
                case 4 -> {
                    view.prompt("Enter genre: ");
                    assert initialBooks != null;
                    view.showBooks(service.findBooksByGenreSorted(initialBooks, scanner.nextLine()));
                }
                case 5 -> {
                    view.prompt("Enter book title: ");
                    assert initialBooks != null;
                    Book foundBook = service.findBookByTitle(initialBooks, scanner.nextLine());
                    if (foundBook != null) {
                        view.showBookDetails(foundBook);
                    } else {
                        view.showBookNotFound();
                    }
                }
                case 6 -> {
                    Book newBook = bookEditor.createBookFromInput(view);
                    service.addBook(initialBooks, newBook);
                    view.prompt("Book added successfully!\n");
                }

                case 7 -> {
                    int idToRemove = bookEditor.getBookIdToRemove(view);
                    boolean removed = service.removeBookById(initialBooks, idToRemove);
                    if (removed) {
                        view.prompt("Book removed successfully.\n");
                    } else {
                        view.prompt("Book not found.\n");
                    }
                }

                case 8 -> {
                    assert initialBooks != null;
                    view.showAllBooks(initialBooks);
                }


                case 9 -> {
                    view.prompt("Enter book ID: ");
                    assert initialBooks != null;
                    Book foundBook = service.findBookById(initialBooks, scanner.nextInt());
                    scanner.nextLine();
                    if (foundBook != null) {
                        view.showBookDetails(foundBook);
                    } else {
                        view.showBookNotFound();
                    }
                }
                case 10 -> {
                    view.prompt("Enter genre: ");
                    String genreInput = scanner.nextLine();
                    assert initialBooks != null;
                    Map<String, List<Book>> genreMap = service.mapGenreToBooksSortedByAuthor(initialBooks);
                    List<Book> booksInGenre = genreMap.get(genreInput);
                    if (booksInGenre != null && !booksInGenre.isEmpty()) {
                        view.prompt("Books in genre: " + genreInput + "\n");
                        view.showBooks(booksInGenre);
                    } else {
                        view.prompt("No books found for genre: " + genreInput + "\n");
                    }
                }
                case 11 -> {
                    view.prompt("Enter publisher: ");
                    String publisherInput = scanner.nextLine();
                    assert initialBooks != null;
                    Map<String, Integer> publisherMap = service.countBooksPerPublisher(initialBooks);
                    Integer count = publisherMap.get(publisherInput);
                    if (count != null) {
                        String bookLabel = (count == 1) ? "book" : "books";
                        view.prompt(publisherInput + " -> " + count + " " + bookLabel + "\n");
                    } else {
                        view.prompt("No books found for publisher: " + publisherInput + "\n");
                    }
                }
                case 12 -> {
                    view.prompt("Enter text filename: ");
                    textRepo.saveToFile(initialBooks, scanner.nextLine());
                    view.prompt("Books saved successfully!\n");
                }
                case 13 -> {
                    view.prompt("Enter binary filename: ");
                    binaryRepo.saveToFile(initialBooks, scanner.nextLine());
                    view.prompt("Books saved successfully!\n");
                }
                case 14 -> {
                    view.prompt("Enter JSON filename: ");
                    jsonRepo.saveToFile(initialBooks, scanner.nextLine());
                    view.prompt("Books saved successfully!\n");
                }
                case 15 -> {
                    view.prompt("Load from text file: ");
                    initialBooks = textRepo.loadFromFile(scanner.nextLine());
                    if (initialBooks != null ) {
                        view.prompt("Books loaded successfully!\n");
                        view.showBooks(initialBooks);
                    } else {
                        view.prompt("Books loaded failed!\n");
                    }
                }
                case 16 -> {
                    view.prompt("Load from binary file: ");
                    initialBooks = binaryRepo.loadFromFile(scanner.nextLine());
                    if (initialBooks != null) {
                        view.prompt("Books loaded successfully!\n");
                        view.showBooks(initialBooks);
                    } else {
                        view.prompt("Books loaded failed!\n");
                    }
                }
                case 17 -> {
                    view.prompt("Load from JSON file: ");
                    initialBooks = jsonRepo.loadFromFile(scanner.nextLine());
                    if (initialBooks != null) {
                        view.prompt("Books loaded successfully!\n");
                        view.showBooks(initialBooks);
                    } else {
                        view.prompt("Books loaded failed!\n");
                    }
                }
                case 0 -> {
                    break label;
                }
                default -> view.prompt("Invalid option, try again.\n");
            }
        }
    }
}
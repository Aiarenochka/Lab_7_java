package main.main;

import main.controller.BookController;
import main.factory.BookFactory;

public class Main {
    private final BookController controller = new BookController();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        controller.processBooks(BookFactory.createBooks());
    }
}
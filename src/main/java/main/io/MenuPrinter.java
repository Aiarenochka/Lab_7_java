package main.io;

public class MenuPrinter {
    public void menuOptions() {
        System.out.print("""
        --- Book Menu ---
        1. List books by author
        2. List books by publisher
        3. List books with min pages
        4. List books by genre (sorted by year/title)
        5. Find book by title
        6. Add a new book
        7. Remove a book by ID
        8. Find book by ID
        9. Show books by genre sorted by author
        10. Show number of books per publisher
        11. Save to text file
        12. Save to binary file
        13. Save to JSON file
        14. Load from text file
        15. Load from binary file
        16. Load from JSON file
        0. Exit
        Choose an option: >>\s""");
    }
}
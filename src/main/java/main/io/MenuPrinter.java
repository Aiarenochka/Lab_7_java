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
        6. Find book by ID
        7. Show books by genre sorted by author
        8. Show number of books per publisher
        9. Save to text file
        10. Save to binary file
        11. Save to JSON file
        12. Load from text file
        13. Load from binary file
        14. Load from JSON file
        0. Exit
        Choose an option: >>\s""");
    }
}
package main.service;

import main.model.Book;

import java.util.*;

public class BookService {

    public List<Book> findBooksByAuthor(List<Book> books, String author) {
        ArrayList<Book> resultList = new ArrayList<>();

        for (Book book : books) {
            if (book != null && book.getAuthor().equalsIgnoreCase(author)) {
                resultList.add(book);
            }
        }

        return resultList;
    }

    public List<Book> findBooksByPublisher(List<Book> books, String publisher) {
        ArrayList<Book> resultList = new ArrayList<>();

        for (Book book : books) {
            if (book != null && book.getPublisher().equalsIgnoreCase(publisher)) {
                resultList.add(book);
            }
        }

        return resultList;
    }

    public List<Book> findBooksByMinPages(List<Book> books, int minPages) {
        ArrayList<Book> resultList = new ArrayList<>();

        for (Book book : books) {
            if (book != null && book.getPages() >= minPages) {
                resultList.add(book);
            }
        }

        return resultList;
    }

    public List<Book> findBooksByGenreSorted(List<Book> books, String genre) {
        ArrayList<Book> resultList = new ArrayList<>();

        for (Book book : books) {
            if (book != null && book.getGenre().equalsIgnoreCase(genre)) {
                resultList.add(book);
            }
        }

        ArrayList<Book> filteredBooks = new ArrayList<>(resultList);

        filteredBooks.sort(new Comparator<>() {
            @Override
            public int compare(Book b1, Book b2) {
                int yearCompare = Integer.compare(b1.getYear(), b2.getYear());
                if (yearCompare != 0) {
                    return yearCompare;
                }
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });

        return filteredBooks;
    }

    public Book findBookByTitle(List<Book> books, String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookById(List<Book> books, int id) {
        for (Book book : books) {
            if (book != null && book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public void addBook(List<Book> books, Book newBook) {
        if (newBook != null) {
            books.add(newBook);
        }
    }

    public boolean removeBookById(List<Book> books, int id) {
        Book bookToRemove = findBookById(books, id);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            return true;
        }
        return false;
    }

    public Map<String, List<Book>> mapGenreToBooksSortedByAuthor(List<Book> books) {
        Map<String, List<Book>> genreMap = new HashMap<>();

        for (Book book : books) {
            if (book != null) {
                String genre = book.getGenre();

                if (!genreMap.containsKey(genre)) {
                    genreMap.put(genre, new ArrayList<>());
                }

                genreMap.get(genre).add(book);
            }
        }

        for (String genre : genreMap.keySet()) {
            List<Book> bookList = genreMap.get(genre);
            bookList.sort(Comparator.comparing(Book::getAuthor));
        }

        return genreMap;
    }

    public Map<String, Integer> countBooksPerPublisher(List<Book> books) {
        Map<String, Integer> publisherCountMap = new HashMap<>();

        for (Book book : books) {
            if (book != null) {
                String publisher = book.getPublisher();

                if (publisherCountMap.containsKey(publisher)) {
                    int count = publisherCountMap.get(publisher);
                    publisherCountMap.put(publisher, count + 1);
                } else {
                    publisherCountMap.put(publisher, 1);
                }
            }
        }

        return publisherCountMap;
    }

}
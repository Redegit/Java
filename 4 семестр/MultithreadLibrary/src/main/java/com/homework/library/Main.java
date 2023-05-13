package com.homework.library;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = createBooks();
        Library library = new Library();
        ReadingRoom readingRoom = new ReadingRoom();
        library.setBooks(books);

        Thread reader1 = new Thread(new Reader(library, readingRoom, "1"), "reader1");
        Thread reader2 = new Thread(new Reader(library, readingRoom, "2"), "reader2");
        Thread reader3 = new Thread(new Reader(library, readingRoom, "3"), "reader3");
        Thread reader4 = new Thread(new Reader(library, readingRoom, "4"), "reader4");

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();
    }

    private static ArrayList<Book> createBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            Book book = new Book(i, new Random().nextBoolean());
            books.add(book);
        }
        return books;
    }
}

package com.homework.library;

import java.util.ArrayList;

public class Library implements Room {
    private int readersInRoom = 0;

    private ArrayList<Book> books;

    public synchronized void enter(Reader reader) {
        int maxReadersInRoom = 2;
        while (readersInRoom >= maxReadersInRoom) {
            try {
                wait();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        readersInRoom++;
        reader.setLocation("library");
        System.out.println("Читатель " + reader.getName() + " вошел в библиотеку");
        System.out.println("Всего человек в читальном зале: " + readersInRoom);
        notify();
    }

    public synchronized void leave(Reader reader) {
        int minReadersInRoom = 0;
        while (readersInRoom <= minReadersInRoom) {
            try {
                wait();
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        readersInRoom--;
        reader.setLocation("building");
        System.out.println("Читатель " + reader.getName() + " покинул библиотеку");
        System.out.println("Всего человек в читальном зале: " + readersInRoom);
        notify();
    }


    synchronized void giveBook(Reader reader, Book book) {
        ArrayList<Book> readerBooks = reader.getBooks();
        readerBooks.add(book);
        reader.setBooks(readerBooks);
        books.remove(book);
        String end = book.isReaderRoomOnly()?" (только для читального зала)":"";
        System.out.println("Читатель " + reader.getName() + " взял книгу " + book.getId() + end);
    }

    synchronized void returnBook(Reader reader, Book book) {
        ArrayList<Book> readerBooks = reader.getBooks();
        readerBooks.remove(book);
        reader.setBooks(readerBooks);
        books.add(book);
        System.out.println("Читатель " + reader.getName() + " вернул книгу " + book.getId());
    }

    ArrayList<Book> getBooks() {
        return books;
    }

    void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}

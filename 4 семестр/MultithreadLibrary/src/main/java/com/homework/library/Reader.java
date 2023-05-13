package com.homework.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Reader implements Runnable {

    private String name;
    private String location;
    private Library library;
    private Room readingRoom;
    private ArrayList<Book> books;

    private HashMap<String, int[]> movementMap = new HashMap<>();

    Reader(Library library, Room readingRoom, String name) {
        this.library = library;
        this.readingRoom = readingRoom;
        this.name = name;
        location = "home";
        books = new ArrayList<>();

        movementMap.put("home", new int[]{6, 8});
        movementMap.put("library", new int[]{1, 5, 5, 5, 7, 7});
        movementMap.put("building", new int[]{0, 0, 2, 2, 2, 2, 2});
        movementMap.put("reading room", new int[]{3, 6, 6, 6, 6, 6});
    }

    private int chooseAction() {
        for (String loc : movementMap.keySet()) {
            if (loc.equals(location)) {
                int[] moves = movementMap.get(loc);
                return moves[new Random().nextInt(moves.length)];
            }
        }
        return -1;
    }

    public void run() {
        try {
            while (true) {
                int action = chooseAction();
                switch (action) {
                    case 0:
                        library.enter(Reader.this);
                        Thread.sleep(2000);
                        break;
                    case 1:
                        library.leave(Reader.this);
                        Thread.sleep(2000);
                        break;
                    case 2:
                        if (Reader.this.books.size() > 0) {
                            readingRoom.enter(Reader.this);
                            Thread.sleep(3000);
                        }
                        break;
                    case 3:
                        readingRoom.leave(Reader.this);
                        Thread.sleep(3000);
                        break;
                    case 4:
                        setLocation("Home");
                        System.out.println("Читатель " + this.name + " дома");
                        Thread.sleep(5000);
                        break;
                    case 5:
                        if (library.getBooks().size() > 0) {
                            library.giveBook(Reader.this, library.getBooks().
                                    get(new Random().nextInt(library.
                                            getBooks().size())));
                            Thread.sleep(2000);
                            if(Reader.this.books.get(books.size() - 1).isReaderRoomOnly()) {
                                readAtReadingRoom(Reader.this.books.get(books.size() - 1));
                            }
                        }
                        break;
                    case 6:
                        if (Reader.this.books.size() > 0) {
                            System.out.println("Читаель " + this.name + " читает");
                            Thread.sleep(10000);
                        } else break;
                        break;
                    case 7:
                        if (Reader.this.books.size() > 0) {
                            library.returnBook(Reader.this, Reader.this.getBooks().
                                    get(new Random().nextInt(Reader.this.
                                            getBooks().size())));
                        } else break;
                        break;
                    case 8:
                        Reader.this.setLocation("building");
                        System.out.println("Читатель " + Reader.this.name + " вошел в библиотеку");
                        break;
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void readAtReadingRoom(Book book) {
        try {
            setLocation("reading room");
            System.out.println("Читатель " + this.name + " читает книгу " + book.getId() + " " +
                    "(только для читального зала)");
            Thread.sleep(10000);
            setLocation("library");
            library.returnBook(Reader.this, book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    String getName() {
        return name;
    }


    ArrayList<Book> getBooks() {
        return books;
    }

    void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    void setLocation(String location) {
        this.location = location;
    }
}
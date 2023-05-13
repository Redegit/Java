package com.homework.library;

class Book {
    private long id;
    private boolean readerRoomOnly;

    Book(long id, boolean readerRoomOnly) {
        this.id = id;
        this.readerRoomOnly = readerRoomOnly;
    }

    long getId() {
        return id;
    }

    boolean isReaderRoomOnly() {
        return readerRoomOnly;
    }
}

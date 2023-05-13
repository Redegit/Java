package com.homework.library;

public class ReadingRoom implements Room {
    private int readersInRoom = 0;

    public synchronized void enter(Reader reader) {
        int maxReadersInRoom = 4;
        while (readersInRoom >= maxReadersInRoom) {
            try {
                wait();
            }
            catch (InterruptedException ignored){}
        }
        readersInRoom++;
        reader.setLocation("reading room");
        System.out.println("Читетель " + reader.getName() + " зашел в читальный зал");
        System.out.println("Всего человек в читальном зале: " + readersInRoom);
        notify();
    }

    public synchronized void leave(Reader reader) {
        int minReadersInRoom = 0;
        while (readersInRoom <= minReadersInRoom) {
            try {
                wait();
            }
            catch (InterruptedException ignored){}
        }
        readersInRoom--;
        reader.setLocation("building");
        System.out.println("Читатель " + reader.getName() + " покинул читальный зал");
        System.out.println("Всего человек в читальном зале: " + readersInRoom);
        notify();
    }
}

package model;

import java.util.concurrent.atomic.AtomicBoolean;

public class Bank implements Runnable {

    private final String name;

    private int money;

    private final AtomicBoolean running = new AtomicBoolean(true);

    private boolean busy = false;

    private final long lunch;

    public Bank(String name, int money, long lunch) {
        this.name = name;
        this.money = money;
        this.lunch = lunch;
    }

    public String getName() {
        return name;
    }

    public synchronized int getMoney() {
        return money;
    }

    public synchronized void setMoney(int money) {
        this.money = money;
    }

    public synchronized boolean isBusy() {
        return busy;
    }

    public synchronized void setBusy(boolean busy) {
        this.busy = busy;
    }

    public void stopBank() {
        running.set(false);
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Thread " + name + " has been stopped.");
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Thread.sleep(lunch);
            } catch (InterruptedException ignored) {}
        }
        System.out.println("Thread " + name + " has been stopped.");
    }
} 
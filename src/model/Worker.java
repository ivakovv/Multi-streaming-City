package model;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker extends Client implements Runnable {
    private final int salary;
    private final int limit;
    private final List<Bank> banks;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final long workerJob;

    public Worker(String name, int money, int salary, int limit, List<Bank> banks, long workerJob) {
        super(name, money);
        this.salary = salary;
        this.limit = limit;
        this.banks = banks;
        this.workerJob = workerJob;
    }

    public void stopWorker() {
        running.set(false);
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Thread " + name + " has been stopped.");
    }

    public synchronized void receiveSalary(int amount) {
        this.money += amount;
        notify();
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public void run() {
        while (running.get()) {
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException ignored) {}
            }
            if (!running.get()) break;
            try {
                Thread.sleep(workerJob);
            } catch (InterruptedException ignored) {}
            if (money >= limit) {
                Bank bank = util.RandomUtil.pickRandom(banks);
                synchronized (bank) {
                    while (bank.isBusy()) {
                        try { bank.wait(); } catch (InterruptedException ignored) {}
                    }
                    bank.setBusy(true);
                    bank.setMoney(bank.getMoney() + money);
                    money = 0;
                    bank.setBusy(false);
                    bank.notifyAll();
                }
            }
        }
    }
} 
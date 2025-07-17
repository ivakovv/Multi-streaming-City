package model;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Spender extends Client implements Runnable {
    private final List<Bank> banks;
    private final List<Worker> workers;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final long lunch;

    public Spender(String name, int money, List<Bank> banks, List<Worker> workers, long lunch) {
        super(name, money);
        this.banks = banks;
        this.workers = workers;
        this.lunch = lunch;
    }

    public void stopSpender() {
        running.set(false);
        synchronized (this) {
            notifyAll();
        }
        System.out.println("Thread " + name + " has been stopped.");
    }

    @Override
    public void run() {
        while (running.get()) {
            Worker worker = util.RandomUtil.pickRandom(workers);
            synchronized (worker) {
                while (!running.get()) break;
                try {
                    worker.wait(10);
                } catch (InterruptedException ignored) {}
                int salary = worker.getSalary();
                if (money >= salary) {
                    worker.receiveSalary(salary);
                    money -= salary;
                } else {
                    Bank bank = util.RandomUtil.pickRandom(banks);
                    synchronized (bank) {
                        while (bank.isBusy()) {
                            try { bank.wait(); } catch (InterruptedException ignored) {}
                        }
                        bank.setBusy(true);
                        money++;
                        bank.setMoney(bank.getMoney() - 1);
                        bank.setBusy(false);
                        bank.notifyAll();
                    }
                }
            }
            try {
                Thread.sleep(lunch);
            } catch (InterruptedException ignored) {}
        }
    }
} 
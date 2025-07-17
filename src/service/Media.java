package service;

import model.Bank;
import model.Worker;
import model.Spender;
import java.util.List;

public class Media implements Runnable {
    private final List<Bank> banks;
    private final List<Worker> workers;
    private final List<Spender> spenders;
    private volatile boolean running = true;
    private final long lunch;

    public Media(List<Bank> banks, List<Worker> workers, List<Spender> spenders, long lunch) {
        this.banks = banks;
        this.workers = workers;
        this.spenders = spenders;
        this.lunch = lunch;
    }

    public void stopMedia() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Good news for everyone! Total amount money in city is: " + Wiretapping.totalMoney(banks, workers, spenders) + "$\n");
            for (Bank bank : banks) {
                System.out.println("This " + bank.getName() + " has money: " + bank.getMoney() + "$\n");
            }
            for (Worker worker : workers) {
                System.out.println("This " + worker.getName() + " has money: " + worker.getMoney() + "$\n");
            }
            for (Spender spender : spenders) {
                System.out.println("This " + spender.getName() + " has money: " + spender.getMoney() + "$\n");
            }
            try {
                Thread.sleep(lunch);
            } catch (InterruptedException ignored) {}
        }
        System.out.println("Thread Media has been stopped.");
    }
} 
package service;

import model.Bank;
import model.Worker;
import model.Spender;
import java.util.List;

public class Wiretapping {
    public static int totalMoney(List<Bank> banks, List<Worker> workers, List<Spender> spenders) {
        int sum = 0;
        for (Bank bank : banks) sum += bank.getMoney();
        for (Worker worker : workers) sum += worker.getMoney();
        for (Spender spender : spenders) sum += spender.getMoney();
        return sum;
    }
} 
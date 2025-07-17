package factory;

import model.Bank;
import model.Worker;
import model.Spender;
import java.util.List;

public class CityFactory implements AbstractFactory {
    @Override
    public Bank createBank(String name, int initialMoney, long lunch) {
        return new Bank(name, initialMoney, lunch);
    }

    @Override
    public Worker createWorker(String name, int initialMoney, int salary, int limit, List<Bank> banks, long workerJob) {
        return new Worker(name, initialMoney, salary, limit, banks, workerJob);
    }

    @Override
    public Spender createSpender(String name, int initialMoney, List<Bank> banks, List<Worker> workers, long lunch) {
        return new Spender(name, initialMoney, banks, workers, lunch);
    }
} 
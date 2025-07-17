
import factory.CityFactory;
import factory.AbstractFactory;
import model.Bank;
import model.Worker;
import model.Spender;
import service.Media;
import service.Wiretapping;
import util.ConfigLoader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigLoader config = new ConfigLoader("src/config.properties");
        AbstractFactory factory = new CityFactory();

        int bankCount = Integer.parseInt(config.getProperty("bank.count"));
        int workerCount = Integer.parseInt(config.getProperty("worker.count"));
        int spenderCount = Integer.parseInt(config.getProperty("spender.count"));
        int bankInitMoney = Integer.parseInt(config.getProperty("bank.initMoney"));
        int clientInitMoney = Integer.parseInt(config.getProperty("client.initMoney"));
        int salary = Integer.parseInt(config.getProperty("worker.salary"));
        int workerLimit = Integer.parseInt(config.getProperty("worker.limit"));
        long workDay = Long.parseLong(config.getProperty("work.day"));
        long workerJob = Long.parseLong(config.getProperty("worker.job"));
        long lunch = Long.parseLong(config.getProperty("lunch"));

        List<Bank> banks = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();
        List<Spender> spenders = new ArrayList<>();

        for (int i = 1; i <= bankCount; i++) {
            banks.add(factory.createBank("Bank-" + i, bankInitMoney, lunch));
        }
        for (int i = 1; i <= workerCount; i++) {
            workers.add(factory.createWorker("Worker-" + i, clientInitMoney, salary, workerLimit, banks, workerJob));
        }
        for (int i = 1; i <= spenderCount; i++) {
            spenders.add(factory.createSpender("Spender-" + i, clientInitMoney, banks, workers, lunch));
        }

        System.out.println("Total money amount in city on day start: " + Wiretapping.totalMoney(banks, workers, spenders) + "$\n");
        for (Bank bank : banks) {
            System.out.println("This " + bank.getName() + " has money: " + bank.getMoney() + "$\n");
        }
        for (Worker worker : workers) {
            System.out.println("This " + worker.getName() + " has money: " + worker.getMoney() + "$\n");
        }
        for (Spender spender : spenders) {
            System.out.println("This " + spender.getName() + " has money: " + spender.getMoney() + "$\n");
        }
        System.out.println();

        List<Thread> threads = new ArrayList<>();
        for (Bank bank : banks) {
            Thread t = new Thread(bank);
            t.setName(bank.getName());
            threads.add(t);
            t.start();
        }
        for (Worker worker : workers) {
            Thread t = new Thread(worker);
            t.setName(worker.getName());
            threads.add(t);
            t.start();
        }
        for (Spender spender : spenders) {
            Thread t = new Thread(spender);
            t.setName(spender.getName());
            threads.add(t);
            t.start();
        }

        Media media = new Media(banks, workers, spenders, lunch);
        Thread mediaThread = new Thread(media);
        mediaThread.setDaemon(true);
        mediaThread.setName("Media");
        mediaThread.start();

        Thread.sleep(workDay);

        for (Worker worker : workers) worker.stopWorker();
        for (Spender spender : spenders) spender.stopSpender();
        for (Bank bank : banks) bank.stopBank();
        media.stopMedia();

        for (Thread t : threads) t.join();

        System.out.println("Total money amount in city on day end: " + Wiretapping.totalMoney(banks, workers, spenders) + "$\n");

        service.HelpDesk.getInstance().printCitySummary(banks, workers, spenders);
    }
}
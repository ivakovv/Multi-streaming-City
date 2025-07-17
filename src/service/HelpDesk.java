package service;

import model.Bank;
import model.Worker;
import model.Spender;
import java.util.List;

/**
 * @author Ivakov Andrey
 * Справочная доска города
 * <p>
 * Реализует паттерн Singleton — существует только один экземпляр этого класса на всё приложение.
 * Используется для предоставления справочной информации о городе и его участниках
 * @see <a href="https://habr.com/ru/companies/otus/articles/779914/">Паттерн Singleton</a>
 */
public class HelpDesk {
    private static volatile HelpDesk instance;

    private HelpDesk() {}

    /**
     * Получить единственный экземпляр HelpDesk
     * @return экземпляр HelpDesk
     */
    public static HelpDesk getInstance() {
        if (instance == null) {
            synchronized (HelpDesk.class) {
                if (instance == null) {
                    instance = new HelpDesk();
                }
            }
        }
        return instance;
    }

    /**
     * Вывести информацию о банках
     */
    public void printBanksInfo(List<Bank> banks) {
        System.out.println("=== Банки ===");
        for (Bank bank : banks) {
            System.out.println(bank.getName() + " - " + bank.getMoney() + "$ ");
        }
    }

    /**
     * Вывести информацию о трудягах
     */
    public void printWorkersInfo(List<Worker> workers) {
        System.out.println("=== Трудяги ===");
        for (Worker worker : workers) {
            System.out.println(worker.getName() + " - " + worker.getMoney() + "$ ");
        }
    }

    /**
     * Вывести информацию о транжирах
     */
    public void printSpendersInfo(List<Spender> spenders) {
        System.out.println("=== Транжиры ===");
        for (Spender spender : spenders) {
            System.out.println(spender.getName() + " - " + spender.getMoney() + "$ ");
        }
    }

    /**
     * Общая сводка по городу
     */
    public void printCitySummary(List<Bank> banks, List<Worker> workers, List<Spender> spenders) {
        System.out.println("=== Справка по городу ===");
        printBanksInfo(banks);
        printWorkersInfo(workers);
        printSpendersInfo(spenders);
        System.out.println("=========================");
    }
} 
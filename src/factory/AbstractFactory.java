package factory;

import model.Bank;
import model.Worker;
import model.Spender;
import java.util.List;

/**
 * @author Ivakov Andrey
 * @see <a href="https://habr.com/ru/articles/465835/">Паттерн AbstractFactory</a>
 * Абстрактная фабрика для создания сущностей города: банков, трудяг и транжир
 * <p>
 */
public interface AbstractFactory {
    /**
     * <b> Создаёт новый банк с заданным именем и начальными деньгами </b>
     *
     * @param name имя банка
     * @param initialMoney начальное количество денег в банке
     * @param lunch время обеда
     * @return созданный банк
     * @see Bank
     */
    Bank createBank(String name, int initialMoney, long lunch);

    /**
     * <b> Создаёт нового трудягу с заданными параметрами </b>
     *
     * @param name         имя трудяги
     * @param initialMoney начальное количество денег у трудяги
     * @param salary       размер заработной платы
     * @param limit        лимит денег, после которого трудяга несёт деньги в банк
     * @param banks        список доступных банков
     * @param workerJob    время работы сотрудника
     * @return созданный трудяга
     * @see Worker
     */
    Worker createWorker(String name, int initialMoney, int salary, int limit, List<Bank> banks, long workerJob);

    /**
     * <b> Создаёт нового транжиру с заданными параметрами </b>
     *
     * @param name         имя транжиры
     * @param initialMoney начальное количество денег у транжиры
     * @param banks        список доступных банков
     * @param workers      список доступных трудяг
     * @param lunch        время паузы между наймами
     * @return созданный транжира
     * @see Spender
     */
    Spender createSpender(String name, int initialMoney, List<Bank> banks, List<Worker> workers, long lunch);
} 
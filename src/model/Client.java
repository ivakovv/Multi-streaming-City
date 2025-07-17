package model;

/**
 * Абстрактный клиент города
 * <p>
 * Базовый класс для всех участников города (трудяги, транжиры)
 * Содержит имя и баланс, а также методы для работы с ними
 */
public abstract class Client {
    /** Имя клиента */
    protected final String name;
    /** Баланс клиента */
    protected int money;

    /**
     * Конструктор клиента
     * @param name имя клиента
     * @param money начальное количество денег
     */
    public Client(String name, int money) {
        this.name = name;
        this.money = money;
    }

    /**
     * Получить имя клиента
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получить баланс клиента
     * @return количество денег
     */
    public synchronized int getMoney() {
        return money;
    }

    /**
     * Установить баланс клиента
     * @param money новое количество денег
     */
    public synchronized void setMoney(int money) {
        this.money = money;
    }
} 
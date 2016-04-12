package ua.yandex.bank;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Maksym Yatsura
 */
public class Account {

    private int balance;
    private Lock lock = new ReentrantLock();
    private Condition lowBalance = lock.newCondition();

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) throws InterruptedException {

        if (amount < 0) {
            return;
        }

        lock.lock();

        try {
            while (amount > balance) {
                lowBalance.await();
            }

            balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        
        if (amount < 0) {
            return;
        }
        
        lock.lock();
        try {
            balance += amount;
            lowBalance.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public int getBalance() {
        return balance;
    }

}

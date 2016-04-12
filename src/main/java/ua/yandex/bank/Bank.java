package ua.yandex.bank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maksym Yatsura
 */
public class Bank {

    private final List<Account> accountList = new ArrayList();
    
    public void transfer(Account from, Account to, int amount) 
            throws InterruptedException {
        from.withdraw(amount);
        to.deposit(amount);
    }
    
    public void addAccount(int balance) {
        Account account = new Account(balance);
        accountList.add(account);
    }
            
    public Account getAccountByIndex(int index) {
        return accountList.get(index);
    }

    public int getNumAccounts() {
        return accountList.size();
    }
              
    public long getTotalBalance() {
        long totalBalance = 0;
        for(int i = 0; i < accountList.size(); i++) {
            totalBalance += accountList.get(i).getBalance();
        }
        return totalBalance;
    }
    
}

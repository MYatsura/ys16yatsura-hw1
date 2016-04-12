package ua.yandex.bank;

import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Maksym Yatsura
*/
public class BankTest {

    private static final int MAX_SUM = 100000000;

    private class TransactionCommiter implements Runnable {

        private Bank bank; 
       
        public TransactionCommiter(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            Random random = new Random();
            int numAccounts = bank.getNumAccounts();
            Account source = bank
                    .getAccountByIndex(random.nextInt(numAccounts));
            Account target = bank
                    .getAccountByIndex(random.nextInt(numAccounts));
            int amount = random.nextInt(MAX_SUM);
            try {
                bank.transfer(source, target, amount);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted");
            }
        }
        
    }
    
    @Test
    public void testTransfer1() throws Exception {
        
        Bank bank = new Bank();
        int numAccounts = 50;
        int numTransactions = 100;
        Random random = new Random();
        
        for(int i = 0; i < numAccounts; i++) {
            bank.addAccount(random.nextInt(MAX_SUM));
        }
        
        long expResult = bank.getTotalBalance();
        
        Thread[] threads = new Thread[numTransactions]; 
        for(int i = 0; i < numTransactions; i++) {
            threads[i] = new Thread(new TransactionCommiter(bank));
        }
        
        for(int i = 0; i < numTransactions; i++) {
            threads[i].start();
        }          

        Thread.sleep(1000);

        for(int i = 0; i < numTransactions; i++) {
            threads[i].interrupt();
        }            
        
        long actResult = bank.getTotalBalance();
        assertEquals(expResult, actResult);
    }
    
    @Test
    public void testTransfer2() throws Exception {
        
        Bank bank = new Bank();
        int numAccounts = 200;
        int numTransactions = 500;
        Random random = new Random();
        
        for(int i = 0; i < numAccounts; i++) {
            bank.addAccount(random.nextInt(MAX_SUM));
        }
        
        long expResult = bank.getTotalBalance();
        
        Thread[] threads = new Thread[numTransactions]; 
        for(int i = 0; i < numTransactions; i++) {
            threads[i] = new Thread(new TransactionCommiter(bank));
        }
        
        for(int i = 0; i < numTransactions; i++) {
            threads[i].start();
        }          

        Thread.sleep(5000);

        for(int i = 0; i < numTransactions; i++) {
            threads[i].interrupt();
        }            
        
        long actResult = bank.getTotalBalance();
        assertEquals(expResult, actResult);
    }

}

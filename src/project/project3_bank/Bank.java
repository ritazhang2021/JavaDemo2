package project.project3_bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Rita
 */
public class Bank {
    private List<Customer> customers;
    private Bank() {
        customers = new ArrayList<Customer>();
    }
    private static volatile Bank instanceBank;

    public static synchronized Bank getBank(){
        if(instanceBank == null){
            synchronized (Bank.class){
                if(instanceBank == null){
                    instanceBank = new Bank();
                }
            }
        }
        return instanceBank;
    }

    public void addCustomer(String firstName, String lastName){
        Customer cust = new Customer(firstName, lastName);
        customers.add(cust);
    }
    public int getNumOfCustomers(){
        return customers.size();
    }
    public Customer getCustomer(int index){
        return customers.get(index);
    }
    public Iterator<Customer> getCustomers(){
        return customers.iterator();
    }

}

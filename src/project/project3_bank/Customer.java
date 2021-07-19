package project.project3_bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Rita
 */
public class Customer {
	
	private String firstName;
	private String lastName;
	private List<Account_Synchronized> accounts;
	
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		accounts = new ArrayList<Account_Synchronized>();
	}

	public Account_Synchronized getAccount(int index) {
		return accounts.get(index);
	}

	public void addAccount(Account_Synchronized account) {
		accounts.add(account);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public int getNumOfAccounts(){
		return accounts.size();
	}
	
	public Iterator<Account_Synchronized> getAccounts(){
		return accounts.iterator();
	}
}

package project.project3_bank;

/**
 * @Author: Rita
 */
public class SavingAccount extends Account_Synchronized {
	private double interestRate;
	
	public SavingAccount(double init_balance, double interestRate) {
		super(init_balance);
		this.interestRate = interestRate;
	}
	
}

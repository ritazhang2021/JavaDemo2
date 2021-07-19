package project.project3_bank;

/**
 * @Author: Rita
 * @Date:4/16/2021 8:35 PM
 */
public class CheckingAccount extends Account_Synchronized {

	private Double overdraftProtection;
	
	public CheckingAccount(double init_balance) {
		super(init_balance);
	}
	
	public CheckingAccount(double init_balance, double overdraftProtection){
		super(init_balance);
		this.overdraftProtection = overdraftProtection;
	}




	@Override
	public void withdraw(double amt) {
		if(balance >= amt){
			balance -= amt;
		}else{
			if(overdraftProtection == null){
				throw new OverdraftException("no overdraft protection", (amt - balance));
			}
			if(overdraftProtection >= (amt - balance)){
				overdraftProtection -= (amt - balance);
				super.balance = 0;
			}else{
				throw new OverdraftException("Insufficient funds for overdraft protection", (amt - balance));
			}
		}
		System.out.println(Thread.currentThread().getName()+" withdraw："+amt);
	}
	
}

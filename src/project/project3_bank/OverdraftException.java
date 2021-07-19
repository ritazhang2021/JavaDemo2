package project.project3_bank;
/**
 * @Author: Rita
 */
public class OverdraftException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private double deficit;
	public double getDeficit() {
		return deficit;
	}
	public OverdraftException(String message, double deficit) {
		super(message);
		this.deficit = deficit;
	}
}

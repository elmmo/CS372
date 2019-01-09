/** 
 * Interface that enforces the declaration of functions for handling employees 
 * @author Elizabeth Min
 */
public interface Employee {
	
	/** 
	 * Pays someone as long as the recipient is a person
	 * Validation is done in the classes that implement Employee. It isn't done here to ensure that the person can easily use the deposityMoney function in Person
	 * @param p	the employee to pay 
	 * @param amount	the amount to pay the employee 
	 */
	public void payEmployee(Person p, double amount); 
	
	/** 
	 * Gets the id of the employee 
	 * @return employee id
	 */
	public int getId();
}

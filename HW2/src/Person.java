import java.math.BigDecimal; 

/** 
 * Base class for all people, stores basic information 
 * @author Elizabeth Min
 */
public class Person {
	String name; 
	int age; 
	String phoneNum;
	BigDecimal moneyInBank; 
	
	/** 
	 * Default constructor that creates a pre-established person 
	 */
	Person() {
		name = "John Doe"; 
		age = 40; 
		phoneNum = "123-456-7890"; 
		moneyInBank = new BigDecimal(0.00); 
	}
	
	/** 
	 * arg constructor that allows assignment of all properties 
	 * @param name	name of the person 
	 * @param age	age of the person 
	 * @param phoneNum	person's phone number 
	 * @param moneyInBank	the amount of money the person currently has in the bank
	 */
	Person(String name, int age, String phoneNum, double moneyInBank) {
		this.name = name; 
		//PT -- make sure to validate age and phone number. -1
		this.age = age; 
		this.phoneNum = phoneNum; 
		this.moneyInBank = new BigDecimal(moneyInBank); 
	}
	
	/** 
	 * gets the person's name 
	 * @return person's name 
	 */
	String getName() { return name; }
	
	/** 
	 * gets the person's age 
	 * @return age
	 */
	int getAge() { return age; }
	
	/** 
	 * gets the user's phone number 
	 * @return String with the user's phone number, sections separated by dashes
	 */
	String getPhoneNum() { return phoneNum; }
	
	/** 
	 * get the amount of money the user has in the bank 
	 * @return BigDecimal value with the amount of money the user has 
	 */
	BigDecimal getMoneyInBank() { return moneyInBank; }
	
	/** 
	 * takes money from the user's account so long as there is enough in there 
	 * @param amount	the amount to withdraw 
	 * @return	the amount in the user's account after withdrawal 
	 * @throws IllegalArgumentException if the amount is a negative sum 
	 */
	BigDecimal withdrawMoney(double amount) throws IllegalArgumentException { 
		if (moneyInBank.doubleValue() < amount) {
			System.out.println("Insufficient funds");
		} else if (amount < 0) {
			throw new IllegalArgumentException("Amount to withdraw must be a positive value."); 
		} else {
			moneyInBank = moneyInBank.subtract(new BigDecimal(amount)); 
		}
		return moneyInBank; 
	}
	
	/** 
	 * adds the given amount of money to a user's account 
	 * @param amount	the amount to insert 
	 * @return	the amount in the account after insertion
	 * @throws IllegalArgumentException if the amount to insert is a negative sum 
	 */
	BigDecimal depositMoney(double amount) throws IllegalArgumentException {
		if (amount < 0) throw new IllegalArgumentException("Amount to deposit must be a positive value.");
		moneyInBank = moneyInBank.add(new BigDecimal(amount));
		return moneyInBank; 
	}
	
	/** 
	 * gets a version of the enum text that's easy to use in string contexts 
	 * @param c	the enum to convert
	 * @return	the formatted string 
	 */
	public String enumFormat(Enum c) {
		StringBuilder builder = new StringBuilder(); 
		String enumText = c.toString(); 
		String[] enumArr = enumText.split("_"); 
		for (int i = 0; i < enumArr.length; i++) {
			if (i > 0) builder.append(" "); 
			builder.append(enumArr[i].substring(0, 1));
			builder.append(enumArr[i].substring(1, enumArr[i].length()).toLowerCase());
		}
		return builder.toString(); 
	}

	
	/** 
	 * Overloads toString with the person's information
	 * @return	string representation of the object 
	 */
	public String toString() {
		return String.format("%s\n%d years old\nPerson", name, age); 
	}
}

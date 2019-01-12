/** 
 * Defines who a policeman is and what his position is 
 * @author Elizabeth Min
 */

enum PoliceRole {
	PATROL, SARGEANT, CAPTAIN, CHIEF; 
	
	/**
	 * Gets the number of items present in the enum 
	 * @return num items in enum 
	 */
	static int length() {
		int len = -1; 
		for (PoliceRole role : PoliceRole.values()) len++; 
		return len; 
	}
}

public class Police extends Person implements Employee {
	int id; 
	PoliceRole position; 
	
	/** 
	 * default constructor that defines id as a random number and sets role to patrol 
	 */
	Police() {
		id = (int)Math.random()*1000; 
		position = PoliceRole.PATROL; 
	}
	
	/** 
	 * arg constructor that takes all the values for person, plus the position to assign
	 * @param name	name of the police
	 * @param age	the age of the person 
	 * @param phoneNum	the personal phone number of the person
	 * @param money	the amount of money the person has 
	 * @param role	the position the police has in the department 
	 */
	Police(String name, int age, String phoneNum, double money, PoliceRole role) {
		super(name, age, phoneNum, money); 
		id = (int)Math.random()*1000; 
		position = role; 
	}
	
	/** 
	 * Overrides payEmployee to ensure that only the chief can pay others, and those who are paid must be police 
	 */
	@Override
	public void payEmployee(Person p, double amount) {
		if (p == this && amount > 500.00) throw new IllegalArgumentException("That's embezzling! >:(");  
		if (!(p instanceof Police)) throw new IllegalArgumentException("It's illegal to pay someone outside the department with federal funds! >:("); 
		if (position == PoliceRole.CHIEF) p.depositMoney(amount); 
	}
	
	/** 
	 * Implements getId to return the police's id 
	 */
	@Override
	public int getId() {
		return id; 
	}
	
	/** 
	 * gets the policeman's position
	 * @return	position 
	 */
	public PoliceRole getPosition() {
		return position; 
	}
	
	/** 
	 * Overloads toString as a short self-introduction 
	 */
	public String toString() {
		return String.format("%s - %d years old - Police - Position: %s", name, age, enumFormat(position, false)); 
	}
}

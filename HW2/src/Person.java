import java.math.BigDecimal; 

public class Person {
	String name; 
	int age; 
	String phoneNum;
	BigDecimal moneyInBank; 
	
	Person() {
		name = "John Doe"; 
		age = 40; 
		phoneNum = "123-456-7890"; 
		moneyInBank = new BigDecimal(0.00); 
	}
	
	Person(String name, int age, String phoneNum, double moneyInBank) {
		this.name = name; 
		this.age = age; 
		this.phoneNum = phoneNum; 
		this.moneyInBank = new BigDecimal(moneyInBank); 
	}
	
	String getName() { return name; }
	int getAge() { return age; }
	String getPhoneNum() { return phoneNum; }
	BigDecimal getMoneyInBank() { return moneyInBank; }
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
	BigDecimal depositMoney(double amount) throws IllegalArgumentException {
		if (amount < 0) throw new IllegalArgumentException("Amount to withdraw must be a positive value.");
		moneyInBank = moneyInBank.add(new BigDecimal(amount));
		return moneyInBank; 
	}
}

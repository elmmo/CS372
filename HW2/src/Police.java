enum PoliceRole {
	PATROL, SARGEANT, CAPTAIN, CHIEF; 
	
	static int length() {
		int len = -1; 
		for (PoliceRole role : PoliceRole.values()) len++; 
		return len; 
	}
}

public class Police extends Person implements Employee {
	int id; 
	PoliceRole position; 
	
	Police() {
		id = (int)Math.random()*1000; 
		position = PoliceRole.PATROL; 
	}
	
	Police(String name, int age, String phoneNum, double money, PoliceRole role) {
		super(name, age, phoneNum, money); 
		id = (int)Math.random()*1000; 
		position = role; 
	}
	
	@Override
	public void payEmployee(Person p, double amount) {
		p.depositMoney(amount);
	}
	
	@Override
	public int getId() {
		return id; 
	}
}

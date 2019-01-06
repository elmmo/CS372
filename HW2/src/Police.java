enum PoliceRoles {
	PATROL, SARGEANT, CAPTAIN, CHIEF; 
}

public class Police extends Person implements Employee {
	int id; 
	PoliceRoles position; 
	
	Police() {
		id = (int)Math.random()*1000; 
		position = PoliceRoles.PATROL; 
	}
	
	Police(String name, int age, String phoneNum, PoliceRoles role) {
		super(name, age, phoneNum, 0.00); 
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

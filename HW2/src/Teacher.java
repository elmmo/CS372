
public class Teacher extends Person implements Employee {
	int id; 
	
	Teacher() {
		id = (int)Math.random()*1000; 
	}
	
	Teacher(String name, int age, String phoneNum) {
		super(name, age, phoneNum, 0.00); 
		id = (int)Math.random()*1000; 
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

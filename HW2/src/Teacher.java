
public class Teacher extends Person implements Employee {
	int id; 
	int gradeLevel; 
	String certification; 
	
	Teacher() {
		id = (int)Math.random()*1000; 
	}
	
	Teacher(String name, int age, String phoneNum, double money, int grade, String cert) {
		super(name, age, phoneNum, money); 
		id = (int)Math.random()*1000; 
		gradeLevel = grade; 
		certification = cert; 
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

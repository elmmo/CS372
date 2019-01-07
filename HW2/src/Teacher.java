enum Certification {
	EARLY_CHILDHOOD, ELEMENTARY, SECONDARY, SPECIAL_ED, LIBRARIAN; 
	
	static int length() {
		int len = 0; 
		for (Certification role : Certification.values()) len++; 
		return len; 
	}
}

public class Teacher extends Person implements Employee {
	int id; 
	int gradeLevel; 
	Certification cert; 
	
	Teacher() {
		id = (int)Math.random()*1000; 
	}
	
	Teacher(String name, int age, String phoneNum, double money, int grade, Certification cert) {
		super(name, age, phoneNum, money); 
		id = (int)Math.random()*1000; 
		gradeLevel = grade; 
		this.cert = cert; 
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

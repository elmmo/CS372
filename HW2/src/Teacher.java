/** 
 * Models a teacher with information on certification and grade level 
 * @author Elizabeth Min
 *
 */

enum Certification {
	EARLY_CHILDHOOD, ELEMENTARY, SECONDARY, SPECIAL_ED, LIBRARIAN; 
	
	/**
	 * Gets the number of items present in the enum 
	 * @return num items in enum 
	 */
	static int length() {
		return Certification.values().length; 
	}
}

public class Teacher extends Person implements Employee {
	int id; 
	int gradeLevel; 
	Certification cert; 
	
	/** 
	 * default constructor that assigns a random int as id and takes the defaults of the Person class
	 */
	Teacher() {
		id = (int)Math.random()*1000; 
	}
	
	/** 
	 * arg constructor that allows the user to define all properties of Teacher
	 * @param name	the name of the teacher 
	 * @param age	the age of the teacher
	 * @param phoneNum	the phone number of the teacher 
	 * @param money	the money the teacher has 
	 * @param grade	the grade the teacher teaches 
	 * @param cert	the certification the teacher has 
	 */
	Teacher(String name, int age, String phoneNum, double money, int grade, Certification cert) {
		super(name, age, phoneNum, money); 
		id = (int)Math.random()*1000; 
		gradeLevel = grade; 
		this.cert = cert; 
	}

	/** 
	 * Overrides payEmployee to ensure that the person is another teacher 
	 */
	@Override
	public void payEmployee(Person p, double amount) {
		if (p instanceof Teacher) p.depositMoney(amount); 
	}

	/** 
	 * implements from Employee to get the id of the teacher 
	 */
	@Override
	public int getId() {
		return id; 
	}

	/** 
	 * Overloads toString with the teacher's information
	 */
	public String toString() {
		return String.format("%s - %d years old - Teacher - Grade %d - Certification: %s", name, age, gradeLevel, enumFormat(cert, false)); 
	}

}

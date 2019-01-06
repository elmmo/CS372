
public class Kid extends Person {
	String favCandy; 
	
	Kid() {
		super("Kiddo", 12, null, 0.00); 
		favCandy = "Watermelon Sour Patch Kids"; 
	}
	
	Kid(String name, int age, String phone, double money, String candy) {
		super(name, age, phone, money); 
		if (age > 18) throw new IllegalArgumentException("You're too old to be a kid!");
		favCandy = candy; 
	}
}

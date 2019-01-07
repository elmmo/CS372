
enum Candy {
	STRAWBERRY, RASPBERRY, CHOCOLATE, WATERMELON, SOUR_APPLE, PEACH;
	
	static int length() {
		int len = 0; 
		for (Candy role : Candy.values()) len++; 
		return len; 
	}
}

public class Kid extends Person {
	Candy favCandy; 
	
	Kid() {
		super("Kiddo", 12, null, 0.00); 
		favCandy = Candy.WATERMELON; 
	}
	
	Kid(String name, int age, String phone, double money, Candy candy) {
		super(name, age, phone, money); 
		if (age > 18) throw new IllegalArgumentException("You're too old to be a kid!");
		favCandy = candy; 
	}
}


/** 
 * Properties for a Person who's a Kid 
 * @author Elizabeth Min
 */
enum Candy {
	RASPBERRY, WATERMELON, SOUR_APPLE, PEACH;
	
	/**
	 * Gets the number of items present in the enum 
	 * @return num items in enum 
	 */
	static int length() {
		int len = 0; 
		for (Candy role : Candy.values()) len++; 
		return len; 
	}
}

public class Kid extends Person {
	Candy favCandy; 
	
	/** 
	 * default constructor using the superclass, with a default favCandy of watermelon 
	 */
	Kid() {
		super("Kiddo", 12, null, 0.00); 
		favCandy = Candy.WATERMELON; 
	}
	
	/** 
	 * Arg constructor that takes in kid information and verifies age input for kid status
	 * @param name	name of kid 
	 * @param age	age of kid 
	 * @param phone	kid's phone number
	 * @param money	amount of money kid has 
	 * @param candy	kid's favorite candy 
	 */
	Kid(String name, int age, String phone, double money, Candy candy) {
		super(name, age, phone, money); 
		if (age > 18) throw new IllegalArgumentException("You're too old to be a kid!");
		favCandy = candy; 
	}
	
	/** 
	 * Standard form for the kid producing the kid's name 
	 */
	public String toString() {
		return String.format("%s\n%d years old\nKid\nFavorite Candy: %s", name, age, enumFormat(favCandy, false)); 
	}
}

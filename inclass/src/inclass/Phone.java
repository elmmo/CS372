package inclass;
import java.util.Random; 

public abstract class Phone {
	private static int lastNum = 1111111; 
	private int num; 
	private Random rnd = new Random(); 
	
	public Phone() {
		num = lastNum; 
		lastNum++; 
	}
	
	public Phone(int number) {
		num = number; 
	}
	
	public boolean call(Phone dest) {
		return dest.receive(this); 
	}
	
	public boolean receive(Phone src) {
		boolean answered = rnd.nextInt()%2 == 1; 
		if (answered) {
			answer(src); 
		} else {
			ignore(src); 
		}
		return answered; 
	}
	
	abstract void answer(Phone src); 
	
	abstract void ignore(Phone src); 
	
	public String toString() {
		return String.format("%d-%d", num/10000, num%10000); 
	}
	
	public static void callSimulator() {
		Phone[] phones = {new Mobile(), new Smart(), new Landline(), new Mobile(), new Smart(), new Smart(), }; 
		
		for (Phone p : phones) {
			System.out.printf("Phone: %s\n", p.toString()); 
		}
		
		Random rnd = new Random(); 
		for (int i = 0; i < 10; i++) {
			int c = rnd.nextInt(phones.length);
			int d = rnd.nextInt(phones.length);
			
			while (c == d) d = rnd.nextInt(phones.length); 
			
			if (phones[c] instanceof Textable && phones[d] instanceof Textable) {
				System.out.printf("Phone %s texted %s\n", phones[c].toString(), phones[d].toString()); 
			}
			else if (phones[c].call(phones[d]))
				System.out.printf("Phone %s called %s\n", phones[c].toString(), phones[d].toString()); 
			else 
				System.out.printf("Phone %s declined %s's call\n", phones[c].toString(), phones[d].toString()); 
		}
	}
}

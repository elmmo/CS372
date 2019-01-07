import java.io.File; 
import java.util.Scanner; 
import java.util.ArrayList; 

public class City {
	ArrayList<Object> citizens; 
	Scanner console; 
	
	Object[] propertyGenerator(int age) {
		// person: [0] = type, [1] = money, [2] = role, [3] = specialization
		Object[] person = new Object[4];
		int range = 3; // number of potential roles
		
		// kid properties 
		if (age < 19) {
			person[0] = "KID"; 
			range = Candy.length(); 
		} else { 
			// determining occupation
			int occupation = age/10; 
			switch (occupation) {
			case 1:
			case 2: 
				person[0] = "POLICE"; 
				range = PoliceRole.length()-1; 
				break; 
			case 3: 
			case 4: 
				person[0] = "TEACHER"; 
				range = Certification.length()-1; 
				break; 
			default: 
				person[0] = "PERSON"; 
				break; 
			}
		}
		
		// assigning role 
		if (person[0].equals("KID")) {
			person[2] = (Math.random()*range)+1; 
		} else if (person[0].equals("TEACHER") || person[0].equals("POLICE")) {
			// implement 
		}
		
		// moneyInBank 
		double money = (100*(age/range))*(range*(1.5*age)); 
		person[1] = money; 
		
		return person; 
	}

	void citizenPopulation() throws Exception {
		File file = new File("citizens.txt"); 
		console = new Scanner(file); 
		
		while (console.hasNextLine()) {
			String[] line = (console.nextLine()).split(""); 
			int age = (int)Math.random()*99; 
			Object[] properties = propertyGenerator(age); 
		}
	}
	
	City() {
		citizens = new ArrayList<Object>(); 
		try {
			citizenPopulation(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void finalize() {
		console.close(); 
	}
}

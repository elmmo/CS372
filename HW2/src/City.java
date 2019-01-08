import java.io.File; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.Scanner;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

import java.util.ArrayList; 

enum CitizenType {
	TEACHER, POLICE, KID, PERSON; 
}

public class City {
	ArrayList<Object> citizens; 
	Scanner console; 
	boolean librarian; 
	boolean chief; 

	void citizenPopulation() throws Exception {
		File file = new File("citizens.txt"); 
		console = new Scanner(file); 
		while (console.hasNextLine()) {
			int rnd = (int)(Math.random()*99); 
			String[] line = (console.nextLine()).split("	"); 
			String name = line[0];
			String phoneNum = line[1]; 
			
			// calculating money 
			int factor = (rnd/10)+1; 
			double money = (10*(rnd/factor))*(factor*(1.5*rnd)); 
			System.out.println(money);
			
			// determining person type and role 
			int type = (rnd/10)/3; 
			int role = (rnd%10)/3; 
			
			CitizenType t = CitizenType.values()[type]; 
			if (rnd < 19) {
				t = CitizenType.KID; 
			} else if (type == 2) {
				// handling the case that a person too old to be a kid gets assigned kid status 
				t = CitizenType.PERSON; 
			}
			
			switch (t) {
			case KID:
				citizens.add(new Kid(name, rnd, phoneNum, money, Candy.values()[role])); 
				break; 
			case TEACHER: 
				// certifications 
				Certification cert = Certification.values()[role]; 
				if (!librarian) {
					cert = Certification.LIBRARIAN; 
					librarian = true; 
				}
				// grade level 
				int grade; 
				if (role == 0) { // early childhood 
					grade = 0; 
				} else if (role == 1) { // elementary 
					grade = (int)(Math.random()*8+1); 
				} else if (role == 2) { // secondary 
					grade = (int)(Math.random()*4+1)+8;
				} else { // special ed
					grade= (int)(Math.random()*12+1); 
				}
				
				citizens.add(new Teacher(name, rnd, phoneNum, money, grade, cert)); 
				break; 
			case POLICE: 
				// role
				PoliceRole position = PoliceRole.values()[role]; 
				if (!chief) {
					position = PoliceRole.CHIEF; 
					chief = true; 
				}
				citizens.add(new Police(name, rnd, phoneNum, money, position)); 
				break; 
			default: 
				citizens.add(new Person(name, rnd, phoneNum, money)); 
				break; 
			}
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

import java.io.File; 
import java.util.Scanner;

import java.util.ArrayList; 

enum CitizenType {
	TEACHER, POLICE, KID, PERSON; 
}

public class City {
	ArrayList<Person> citizens; 
	ArrayList<Building> buildings; 
	Scanner console; 
	boolean librarian; 
	boolean chief; 
	boolean cityhall; 
	boolean school; 

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
	
	void buildingPopulation() throws Exception {
		File file = new File("buildings"); 
		console = new Scanner(file); 
		
		while (console.hasNextLine()) {
			String[] line = (console.nextLine()).split("	"); 
			String name = line[0];
			String address = line[1]; 
			if (name.contains("City Hall")) {
				buildings.add(new CityHall(name, address)); 
				cityhall = true; 
			} else if (name.contains("School")) {
				buildings.add(new School(name, address)); 
				school = true; 
			} else {
				buildings.add(new Building(name, address)); 
			}
		}
		
		if (!(cityhall)) buildings.add(new CityHall()); 
		if (!(school)) buildings.add(new School()); 
	}
	
	City() {
		citizens = new ArrayList<Person>(); 
		buildings = new ArrayList<Building>(); 
		try {
			citizenPopulation(); 
			buildingPopulation(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void getAllPeople() {
		System.out.println("\nPEOPLE");
		for (int i = 0; i < citizens.size(); i++) {
			Person c = (Person)citizens.get(i); 
			System.out.println(c.getName());
		}
	}
	
	public void getAllBuildings() {
		System.out.println("\nBUILDINGS");
		for (int i = 0; i < buildings.size(); i++) {
			Building c = (Building)buildings.get(i); 
			System.out.println(c.getName());
		}
	}
	
	public void finalize() {
		console.close(); 
	}
}

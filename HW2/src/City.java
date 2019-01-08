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
	
	/** 
	 * the city's city hall 
	 */
	public CityHall cityHall;
	
	/** 
	 * the city's school 
	 */
	public School school;

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
				Kid k = new Kid(name, rnd, phoneNum, money, Candy.values()[role]); 
				citizens.add(k); 
				if (role > 0) { // 3/4 chance that the kid will be in school
					school.addOccupant(k);
				}
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
					grade = (int)(Math.random()*12+1); 
				}
				
				Teacher teacher = new Teacher(name, rnd, phoneNum, money, grade, cert);
				if (role > 0) { // 3/4 chance that the teacher will be in school
					school.addOccupant(teacher);
				}
				citizens.add(teacher); 
				break; 
			case POLICE: 
				// role
				PoliceRole position = PoliceRole.values()[role]; 
				
				// making sure that there's at least one chief in the police force
				if (!chief) {
					position = PoliceRole.CHIEF; 
					chief = true; 
				}
				
				// creating the policeman and (potentially) assigning location 
				Police policeman = new Police(name, rnd, phoneNum, money, position); 
				citizens.add(policeman); 
				if (role > 1) { // 50/50 chance that the policeman will be in City Hall
					cityHall.addOccupant(policeman);
				}
				break; 
			default: 
				Person p = new Person(name, rnd, phoneNum, money); 
				citizens.add(p); 
				Building b = buildings.get((int)(Math.random()*(buildings.size()-1))); 
				b.addOccupant(p);
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
				cityHall = new CityHall(name, address); 
				buildings.add(cityHall); 
			} else if (name.contains("School")) {
				school = new School(name, address); 
				buildings.add(school); 
			} else {
				buildings.add(new Building(name, address)); 
			}
		}
		
		// verifying if there is a city hall and a school (required buildings) in the city
		if (cityHall == null) buildings.add(new CityHall()); 
		if (school == null) buildings.add(new School()); 
	}
	
	City() {
		citizens = new ArrayList<Person>(); 
		buildings = new ArrayList<Building>(); 
		try {
			buildingPopulation(); 
			citizenPopulation(); 
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

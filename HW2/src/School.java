import java.util.ArrayList;

public class School extends Building {
	ArrayList<Person> teachersAndStudents = new ArrayList<Person>(); 
	
	School() {
		super("School", "4821 School Ave."); 
	}
	
	School(String name, String address) {
		super(name, address); 
	}
	
	public void getTeachersAndKids() {
		System.out.println("\nTEACHERS AND STUDENTS");
		for (int i = 0; i < occupants.size(); i++) {
			if (occupants.get(i) instanceof Teacher || occupants.get(i) instanceof Kid) {
				teachersAndStudents.add(occupants.get(i)); 
				System.out.println(occupants.get(i).toString());
			}
		}
	}

}

import java.util.ArrayList;

/** 
 * Models a school, with information on which teachers and students are in school 
 * @author Elizabeth Min
 *
 */
public class School extends Building {
	ArrayList<Person> teachersAndStudents = new ArrayList<Person>(); 
	
	/** 
	 * creates a school based on set values 
	 */
	School() {
		super("School", "4821 School Ave."); 
	}
	
	/** 
	 * Arg constructor that defines the school based on user input 
	 * @param name	the name of the school 
	 * @param address	the address of the school 
	 */
	School(String name, String address) {
		super(name, address); 
	}
	
	/** 
	 * keeps track of the teachers and kids currently in the school 
	 * @param verbose	whether to print out the names of all the kids and teachers in the school
	 * @return	number of kids and teachers in the school
	 */
	public int getTeachersAndKids(boolean verbose) {
		if (verbose) System.out.println("\nTEACHERS AND STUDENTS");
		for (int i = 0; i < occupants.size(); i++) {
			if (occupants.get(i) instanceof Teacher || occupants.get(i) instanceof Kid) {
				teachersAndStudents.add(occupants.get(i)); 
				if (verbose) System.out.println(occupants.get(i).toString());
			}
		}
		return teachersAndStudents.size(); 
	}
	
	/** 
	 * Gives information about the building
	 * @return	building information 
	 */
	public String toString() {
		return String.format("%s\n%s\n%d teachers and students\ninside", name, address, getTeachersAndKids(false)); 
	}
}

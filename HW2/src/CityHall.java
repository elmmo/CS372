import java.util.ArrayList; 

/** 
 * Defines a CityHall class that can show all the police in city hall 
 * @author Elizabeth Min 
 */
public class CityHall extends Building {
	ArrayList<Person> police = new ArrayList<Person>(); 

	/** 
	 * default constructor that subs in placeholder values for the name and address
	 */
	CityHall() {
		super("City Hall", "456 City Hall St."); 
	}
	
	/** 
	 * arg constructor that takes a name and an address 
	 * @param name	the name of City Hall
	 * @param address	the address of City Hall 
	 */
	CityHall(String name, String address) {
		super(name, address); 
	}
	
	/** 
	 * gets all the police officers in CityHall 
	 * @param	whether to print the names of all the officers
	 * @return	the number of police officers in City Hall
	 */
	public int getPoliceOfficers(boolean verbose) {
		if (verbose) System.out.println("\nPOLICE OFFICERS");
		for (int i = 0; i < occupants.size(); i++) {
			Person occupant = occupants.get(i); 
			if (occupant instanceof Police && !(police.contains(occupant))) {
				police.add((Police)occupants.get(i)); 
				if (verbose) System.out.println(occupants.get(i).toString());
			}
		}
		return police.size(); 
	}
	
	/** 
	 * uses the helper function inherited from Building to be specific to CityHall
	 * @param p	the person to remove from the building 
	 */
	public void removeOccupant(Person p) {
		removeOccupant(p, police); 
	}
	
	/** 
	 * Gives information about the building
	 * @return	building information 
	 */
	public String toString() {
		return String.format("%s\n%s\n%d police officers inside", name, address, getPoliceOfficers(false)); 
	}
}

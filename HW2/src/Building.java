import java.util.ArrayList; 

/** 
 * Base class for what a building should look like 
 * @author Elizabeth Min
 * 
 */
public class Building {
	String name; 
	String address; 
	ArrayList<Person> occupants; 
	
	/**
	 * default constructor creates a random building without any occupants. 
	 * There's no people in it... Rumor is that it's haunted >:)
	 */
	Building() {
		name = "Random Building"; 
		address = "1234 Main St."; 
		occupants = new ArrayList<Person>(); 
	}
	
	/** 
	 * arg constructor that sets up a way of tracking occupants 
	 * @param name	name of the building 
	 * @param address	address of the building
	 */
	Building(String name, String address) {
		this.name = name; 
		this.address = address; 
		occupants = new ArrayList<Person>(); 
	}
	
	/** 
	 * gets the name of the building 
	 * @return	the name of the building 
	 */
	public String getName() { return name; } 
	
	/** 
	 * gets the address of the building 
	 * @return	the address of the building 
	 */
	public String getAddress() { return address; }
	
	/** 
	 * prints out the occupants in the building 
	 */
	public void getOccupants() { 
		if (occupants.size() == 0) {
			System.out.println("There aren't any people in this building!");
		} else {
			System.out.println("\nOCCUPANTS");
			for (int i = 0; i < occupants.size(); i++) {
				Person p = occupants.get(i); 
				System.out.println(p.getName());
			}
		}
	}
	
	/** 
	 * helper function designed for the gui 
	 * @param p	the person to find in the building 
	 * @return	true if the occupant is inside, false if not
	 */
	public boolean isOccupantInside(Person p) {
		return occupants.contains(p); 
	}
	
	/** 
	 * adds a person to the building 
	 * @param p	the person to add to the building 
	 */
	public void addOccupant(Person p) { occupants.add(p); }
	
	/** 
	 * removes a person from the building 
	 * @param p	the person to remove from the building 
	 */
	public void removeOccupant(Person p) { occupants.remove(p); }
	
	/** 
	 * Gives information about the building
	 * @return	building information 
	 */
	public String toString() {
		return String.format("This is the %s, located at %s", name, address); 
	}
}

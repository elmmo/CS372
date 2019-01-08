import java.util.ArrayList; 

public class Building {
	String name; 
	String address; 
	ArrayList<Person> occupants; 
	
	Building() {
		name = "Random Building"; 
		address = "1234 Main St."; 
		occupants = new ArrayList<Person>(); 
	}
	
	Building(String name, String address) {
		this.name = name; 
		this.address = address; 
		occupants = new ArrayList<Person>(); 
	}
	
	public String getName() { return name; } 
	public String getAddress() { return address; }
	public void getOccupants() { 
		if (occupants.size() == 0) {
			System.out.println("There aren't any people in this building!");
		} else {
			for (int i = 0; i < occupants.size(); i++) {
				System.out.println("\nOCCUPANTS");
				Person p = occupants.get(i); 
				System.out.println(p.getName());
			}
		}
	}
	public void addOccupant(Person p) { occupants.add(p); }
	public void removeOccupant(Person p) { occupants.remove(p); }
	
	public String toString() {
		return String.format("This is the %s, located at %s", name, address); 
	}
}

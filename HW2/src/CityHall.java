import java.util.ArrayList; 

/** 
 * Defines a CityHall class that can show all the police in city hall 
 * @author Elizabeth Min 
 */
public class CityHall extends Building {
	ArrayList<Police> police = new ArrayList<Police>(); 

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
	 */
	public void getPoliceOfficers() {
		System.out.println("\nPOLICE OFFICERS");
		for (int i = 0; i < occupants.size(); i++) {
			if (occupants.get(i) instanceof Police) {
				police.add((Police)occupants.get(i)); 
				System.out.println(occupants.get(i).getName());
			}
		}
	}
}

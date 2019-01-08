import java.util.ArrayList; 

public class CityHall extends Building {
	ArrayList<Police> police = new ArrayList<Police>(); 
	
	CityHall() {
		super("City Hall", "456 City Hall St."); 
	}
	
	CityHall(String name, String address) {
		super(name, address); 
	}
	
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

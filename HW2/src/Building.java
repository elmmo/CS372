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
	}
}

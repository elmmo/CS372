/** 
 * simulates the creation of a city -- tests city 
 * @author Elizabeth Min
 */
public class CityTest {

	public static void main(String[] args) {
		City c = new City(); 
		c.getAllPeople(true); 
		c.getAllBuildings();
		c.cityHall.getPoliceOfficers(true);
		c.school.getTeachersAndKids(false);
	}

}

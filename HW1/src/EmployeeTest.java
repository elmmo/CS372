
public class EmployeeTest {

	public static void main(String[] args) {
		Employee ceo = new Employee(); 
		
		Employee jeliu = new Employee("Jefferson Liu", "SDE II", ceo); 
		System.out.println(jeliu.toString());
		Employee niwang = new Employee("Nina Wang", "SDE I", ceo); 
		System.out.println(niwang);
		
		// promotion
		niwang.setManager(jeliu);
		System.out.println(niwang);

	}

}


import java.util.Date; 

public class Employee {
	String id; 
	String name;
	Date hireDate;
	String position; 
	Employee manager = null; 
	
	/** 
	 * generates an id based on the user's name 
	 * @param name	the name of the user to parse and generate an alias from 
	 * @return 		the generated alias 
	 */
	String generateAlias(String name) {
		String[] firstLastName = name.split(" "); 
		String alias = firstLastName[0].substring(0, 2) + firstLastName[firstLastName.length-1]; 
		return alias.toLowerCase(); 
	}
	
	/**
	 * Default constructor. The default constructor is always the CEO 
	 */
	Employee() {
		name = "Sundar Pichai"; 
		id = generateAlias(name); 
		hireDate = new Date(); 
		position = "CEO"; 
	}
	
	/** 
	 * Constructor for creating all employees under the CEO
	 * @param name		the employee name 
	 * @param position	the employee position 
	 * @param manager	the employee's manager 
	 */
	Employee(String name, String position, Employee manager) {
		this.name = name; 
		this.id = generateAlias(name); 
		this.position = position; 
		this.hireDate = new Date(); 
		this.manager = manager; 
	}
	
	// getters 
	public String getName() { return name; }
	
	public Date getHireDate() { return hireDate; }
	
	public String getPosition() { return position; }
	
	public Employee getManager() { return manager; } 
	
	/** 
	 * Returns a self-introduction from the employee with his/her information
	 */
	public String toString() {
		String employee = "Hi, my name's " + name + ". My alias is " + id + ". "; 
		if (manager != null) {
			employee += "I'm a " + position + " here under " + manager.getName() + "."; 
		} else {
			employee += "I'm the " + position + "."; 
		}
		return employee; 
	}
	
	// promotion-grade functions 
	public void setManager(Employee newManager) { manager = newManager; }
	
	public void setPosition(String newPos) { position = newPos; }
	
}

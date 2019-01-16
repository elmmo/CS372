import java.util.Scanner; 
import java.util.regex.*; 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap; 

public class Model {
	HashMap<Integer, Shape> shapes; 
	
	/** 
	 * constructor initializes the HashMap for storage and runs populate 
	 */
	Model() {
		shapes = new HashMap<Integer, Shape>(); 
		this.populate("src/resources/shapes.csv"); 
	}
	
	/** 
	 * Gets the requested file of shapes and parses it to populate the HashMap 
	 * @param path	the path to the file
	 */
	public void populate(String path) {
		try {
			File file = new File(path); 
			Scanner console = new Scanner(file); 
			while (console.hasNextLine()) {
				// parses each line of the file 
				String line = console.nextLine(); 
				
				// checks if there are any extraneous quotations in the string 
				String check = line.substring(1, line.length()-1); 
				while (check.indexOf('"') != -1) {
					int index = check.indexOf('"'); 
					check = check.substring(0, index) + check.substring(index+1, check.length()); 
				}
				String[] properties = check.split(","); 
				
				Shape s = null; 
				// to prevent so much tyepcasting and repetition in the switch statement 
				int id = Integer.parseInt(properties[1].trim()); 
				int length = Integer.parseInt(properties[2].trim()); 
				int length2 = 0; 
				if (properties.length > 4) {
					length2 = Integer.parseInt(properties[3].trim()); 
				}
				String color = properties[properties.length-1].trim(); 
				
				// populates the hashmap using the id as a key and the shape as the value 
				switch (properties[0]) {
				case "circle": 
					s = new Circle(id, length, color); 
					break; 
				case "triangle":
					s = new Triangle(id, length, length2, Integer.parseInt(properties[4].trim()), color); 
					break; 
				case "rectangle":
					s = new Rectangle(id, length, length2, color); 
					break; 
				case "square": 
					s = new Square(id, length, color); 
					break; 
				}
				shapes.put(id, s); 
			}
			console.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	
	/** 
	 * Returns a HashMap containing shapes ordered by their ids 
	 * @return	shapes
	 */
	public HashMap<Integer, Shape> getShapes() {
		return shapes; 
	}
	
	/** 
	 * Helper function for the Controller that gets a list of shape ids 
	 * @return	ids 
	 */
	public Integer[] getIds() {
		Integer[] ids = new Integer[shapes.size()]; 
		int i = 0; 
		for (HashMap.Entry<Integer, Shape> entry : shapes.entrySet()) {
			ids[i] = entry.getKey(); 
			i++; 
		}
		return ids; 
	}
	
	/** 
	 * Helper function that produces an array to pass to the JScrollPane in Controller 
	 * @return	String array of the String representations of the shapes 
	 */
	public String[] getInfo() {
		String[] info = new String[shapes.size()]; 
		int i = 0; 
		for (HashMap.Entry<Integer, Shape> entry : shapes.entrySet()) {
			String e = entry.toString(); 
			int start = e.indexOf("="); 
			info[i] = e.substring(start+1, e.length()); 
			i++; 
		}
		return info; 
	}
}

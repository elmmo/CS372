/**
 * Describes the Circle implementation of Shape 
 * @author Elizabeth Min
 */
public class Circle implements Shape {
	int id; 
	int radius; 
	String color; 
	double area; 
	double circumference;
	
	Circle(int id, int radius, String color) {
		this.id = id; 
		this.radius = radius; 
		this.color = color; 
		area = Math.PI*(Math.pow(radius, 2)); 
		circumference = 2*Math.PI*radius; 
	}
	
	/* (non-Javadoc)
	 * @see Shape#toString()
	 */
	@Override
	public String toString() {
		return String.format("Circle (ID #%d)", id);
	}

	/* (non-Javadoc)
	 * @see Shape#getKind()
	 */
	@Override
	public String getKind() {
		return "Circle"; 
	}

	/* (non-Javadoc)
	 * @see Shape#getDetailString()
	 */
	@Override
	public String getDetailString() {
		return String.format("%s\nColor: %s\nRadius: %d\nArea: %.4f\nCircumference%.4f", toString(), color, radius, area, circumference); 
	}

	/* (non-Javadoc)
	 * @see Shape#getId()
	 */
	@Override
	public int getId() {
		return id; 
	}

}

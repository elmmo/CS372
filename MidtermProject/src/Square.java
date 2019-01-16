/**
 * Describes the Square implementation of Shape 
 * @author Elizabeth Min
 */
public class Square implements Shape {
	int id; 
	int side; 
	String color; 
	int area; 
	int perimeter; 
	
	Square(int id, int side, String color) {
		this.id = id; 
		this.side = side;  
		this.color = color; 
		area = (int)Math.pow(side, 2); 
		perimeter = side*4; 
	}
	
	/* (non-Javadoc)
	 * @see Shape#toString()
	 */
	@Override
	public String toString() {
		return String.format("Square (ID #%d)", id);
	}

	/* (non-Javadoc)
	 * @see Shape#getKind()
	 */
	@Override
	public String getKind() {
		return "Square"; 
	}

	/* (non-Javadoc)
	 * @see Shape#getDetailString()
	 */
	@Override
	public String getDetailString() {
		return String.format("%s\nColor: %s\nSide Length: %d\nArea: %d\nPerimeter: %d", toString(), color, side, area, perimeter); 
	}

	/* (non-Javadoc)
	 * @see Shape#getId()
	 */
	@Override
	public int getId() {
		return id; 
	}

}

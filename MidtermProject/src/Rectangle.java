/**
 * Describes the Rectangle implementation of Shape 
 * @author Elizabeth Min
 */
public class Rectangle implements Shape {
	int id; 
	int width; 
	int length; 
	String color; 
	int area; 
	int perimeter; 
	
	Rectangle(int id, int width, int length, String color) {
		this.id = id; 
		this.width = width; 
		this.length = length; 
		this.color = color; 
		area = width*length; 
		perimeter = 2*width+2*length; 
	}
	
	/* (non-Javadoc)
	 * @see Shape#toString()
	 */
	@Override
	public String toString() {
		return String.format("Rectangle (ID #%d)", id);
	}

	/* (non-Javadoc)
	 * @see Shape#getKind()
	 */
	@Override
	public String getKind() {
		return "Rectangle"; 
	}

	/* (non-Javadoc)
	 * @see Shape#getDetailString()
	 */
	@Override
	public String getDetailString() {
		return String.format("%s\nColor: %s\nWidth: %d\nLength: %d\nArea: %d\nPerimeter%d", toString(), color, width, length, area, perimeter); 
	}

	/* (non-Javadoc)
	 * @see Shape#getId()
	 */
	@Override
	public int getId() {
		return id; 
	}

}

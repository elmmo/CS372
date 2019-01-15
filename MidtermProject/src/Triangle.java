/**
 * Describes the Square implementation of Shape 
 * @author Elizabeth Min
 */
public class Triangle implements Shape {
	int id; 
	int side1; 
	int side2; 
	int side3; 
	String color; 
	double area; 
	int perimeter; 
	
	Triangle(int id, int side1, int side2, int side3, String color) {
		this.id = id; 
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		this.color = color; 
		perimeter = side1 + side2 + side3; 
		
		// for Heron's Formula 
		int p = perimeter/2; 
		area = Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));  
	}
	
	/* (non-Javadoc)
	 * @see Shape#toString()
	 */
	@Override
	public String toString() {
		return String.format("Triangle (ID #%d)", id);
	}

	/* (non-Javadoc)
	 * @see Shape#getKind()
	 */
	@Override
	public String getKind() {
		return "Triangle"; 
	}

	/* (non-Javadoc)
	 * @see Shape#getDetailString()
	 */
	@Override
	public String getDetailString() {
		return String.format("%s\nColor: %s\nSide 1: %d\nSide 2: %d\nSide 3: %d\nArea: %.04f\nPerimeter%d", toString(), color, side1, side2, side3, area, perimeter); 
	}

	/* (non-Javadoc)
	 * @see Shape#getId()
	 */
	@Override
	public int getId() {
		return id; 
	}

}

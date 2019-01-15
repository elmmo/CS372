/** 
 * Interface that enforces the functions a shape should have 
 * @author Elizabeth Min
 *
 */

public interface Shape {
	/** 
	 * Gets information describing the particular shape 
	 * @return	kind and id of the shape 
	 */
	public String toString();
	
	/** 
	 * The kind of shape that it is 
	 * @return	kind 
	 */
	public String getKind(); 
	
	/** 
	 * Gives properties of the particular shape 
	 * @return	kind, ID, properties
	 */
	public String getDetailString(); 
	
	/** 
	 * Gets the id of the shape 
	 * @return	ID 
	 */
	public int getId(); 
}

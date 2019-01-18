
/** 
 * An interface used to implement callback-like functionality 
 * @author Elizabeth
 *
 */
public interface SpiderCallbacks {
	/** 
	 * Determines what to do when the crawler has finished scraping a page 
	 */
	public void onComplete(); 
	
	/** 
	 *	What to do when all the crawler instances have finished 
	 */
	public void report(); 
}

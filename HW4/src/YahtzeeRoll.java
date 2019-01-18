import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** 
 * Rolls each of the die for Yahtzee and controls the value stored inside the caption 
 * @author Elizabeth Min
 */
public class YahtzeeRoll implements Runnable {
	URL resource; 
	ImageIcon[] images; 
	JLabel label;
	int value; 
	JLabel caption;
	
	/** 
	 * default constructor that stores the caption and images from the Yahtzee instance 
	 * @param l			the label to manipulate
	 * @param caption	the caption that holds the die's value 
	 * @param images	images of the six-sided die
	 */
	YahtzeeRoll(JLabel l, JLabel caption, ImageIcon[] images) {
		this.caption = caption; 
		this.images = images; 
		label = l; 
	}
	
	/** 
	 * Uses a thread to scroll through the sides of the die
	 */
	public void run() {
		int start = 0; 
		int stop = (int)(Math.random()*100+10); // the random index to stop scrolling at 
		int i; // iterator that also stores the value of the dice
		for (i = 0; start <= stop; i++) {
			label.setIcon(images[i]); 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// resets the looping through the numbers if it reaches its max 
			if (i+1 == 6) i = -1; 
			start++; 
		}
		value = (i == 0) ? 6 : i; 
		setValue(); 
	}
	
	/** 
	 * Signals that the die has finished scrolling and reached a value 
	 */
	public void setValue() {
		caption.setBackground(Color.white);
		caption.setText(Integer.toString(value)); 
	}
}



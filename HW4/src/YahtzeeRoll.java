import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class YahtzeeRoll implements Runnable {
	URL resource; 
	ImageIcon[] images; 
	JLabel label;
	int value; 
	JLabel caption;
	
	YahtzeeRoll(JLabel l, JLabel caption, ImageIcon[] images) {
		this.caption = caption; 
		this.images = images; 
		label = l; 
	}
	
	public void run() {
		int start = 0; 
		int stop = (int)(Math.random()*100+10); 
		int i; 
		for (i = 0; start <= stop; i++) {
			label.setIcon(images[i]); 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// resets the looping through the numbers if it reaches its max 
			if (i+1 == 6) 
				i = -1; 
			start++; 
		}
		value = (i == 0) ? 6 : i; 
		setValue(); 
	}
	
	public void setValue() {
		caption.setBackground(Color.white);
		caption.setText(Integer.toString(value)); 
	}
}



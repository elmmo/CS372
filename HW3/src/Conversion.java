import java.awt.*; 
import java.awt.event.*;
import java.net.URL;

import javax.swing.*; 

public class Conversion {
	JFrame frame; 
	JLayeredPane pane; 
	
	Conversion() {
		initialize(); 
	}
	
	private void initialize() {
		// creating frame 
		frame = new JFrame(); 
		frame.setBounds(0, 0, 1200, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create layered pane
		pane = new JLayeredPane(); 
		
		createBackground(); 
		
		// creating jlabel 
		JLabel label = new JLabel(); 
		label.setBounds(30, 50, 90, 30);
		label.setOpaque(true);
		label.setBackground(Color.orange);
		label.setText("Hello World");
		pane.add(label, new Integer(2)); 
		
		// adding 
		frame.add(pane); 
		frame.setVisible(true);
	}
	
	private void createBackground() {
		createIcon("/resources/cityHallresize.png", -30, -10, 331, 331, 1); 
		createIcon("/resources/bg-middle.jpg", 240, 0, 650, 331, 1); 
		createIcon("/resources/school.jpg", 830, 0, 441, 331, 1);
		
	}
	
	private void createIcon(String loc, int x, int y, int width, int height, Integer layer) {
		URL resource = getClass().getResource(loc);
		ImageIcon img = new ImageIcon(resource); 
		JLabel bgImg = new JLabel(img); 
		bgImg.setBounds(x, y, width, height); 
		pane.add(bgImg, layer);
	}
}

import java.awt.*; 
import java.awt.event.*;
import java.net.URL;

import javax.swing.*; 

public class Conversion implements MouseMotionListener {
	JFrame frame; 
	JLayeredPane pane; 
	Point diff; 
	City c; 
	Object[] citizenInfo; 
	JLabel[] cZens; 
	
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
		pane.addMouseMotionListener(this);
		
		createBackground(); 
		
		// get information from City 
		c = new City(); 
		citizenInfo = c.getAllPeople(false); 
		cZens = new JLabel[citizenInfo.length]; 
		
		// creating jlabels
		populateCitizens(); 
		
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
		try {
			URL resource = getClass().getResource(loc); 
			ImageIcon img = new ImageIcon(resource); 
			JLabel bgImg = new JLabel(img); 
			bgImg.setBounds(x, y, width, height); 
			pane.add(bgImg, layer);
		} catch (NullPointerException e) {
			System.out.println("Obtaining the URL of the file at " + loc + " failed");
			e.printStackTrace();
		}
	}
	
	private void populateCitizens() {
		for (int i = 0; i < cZens.length; i++) {
			Person p = (Person)citizenInfo[i]; 
			JLabel l = new JLabel(); 
			
			l.setBounds(380, 50+(i*10), 90, 30);
			c.school.getOccupants(); 
			if (c.school.isOccupantInside(p)) l.setBounds(900, 50+(i*10), 90, 30);
			c.cityHall.getOccupants(); 
			if (c.cityHall.isOccupantInside(p)) l.setBounds(200, 50+(i*10), 90, 30);
			
			l.setOpaque(true);
			l.setBackground(Color.orange);
			l.setText(p.getName());
			cZens[i] = l; 
			pane.add(cZens[i], new Integer(2)); 
			
		}
	}
	
	// could make much more efficient 
	public void mouseDragged(MouseEvent e) {
		JLabel l = null; 
		Person p = null; 
		for (int i = 0; i < cZens.length; i++){
			if (cZens[i].getBounds().contains(e.getPoint())) {
				l = cZens[i]; 
				p = (Person)citizenInfo[i]; 
			}
			if (l != null) {
				if (diff == null) {
					diff = new Point(e.getX() - l.getBounds().x, e.getY() - l.getBounds().y); 
				}
				l.setBounds(e.getX() - diff.x, e.getY() - diff.y, l.getBounds().width, l.getBounds().height);
				
				// checks if the dragged label is within the bounds of the school or the city hall 
				if (l.getX() > 890) {
					if (!(c.school.isOccupantInside(p))) {
						c.school.addOccupant(p);
					}
				} else if (c.school.isOccupantInside(p)) {
					c.school.removeOccupant(p);
				} else if (l.getX() < 295) {
					if (!(c.cityHall.isOccupantInside(p))) {
						c.cityHall.addOccupant(p);
					}
				} else if (c.cityHall.isOccupantInside(p)) {
					c.cityHall.removeOccupant(p);
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		diff = null; 
		//System.out.println(e.getX() + " " + e.getY());
	}
}

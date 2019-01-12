import java.awt.*; 
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.MouseInputListener; 

public class Conversion implements MouseInputListener {
	JFrame frame; 
	JLayeredPane pane; 
	Point diff; 
	City c; 
	Object[] citizenInfo; 
	Object[] buildingInfo; 
	JLabel[] cZens; 
	
	// tooltips 
	JTextArea tooltip; 
	boolean exitToolTip = false; 
	
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
		
		//setting up action listeners
		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		
		// get information from City and store info about buildings
		c = new City(); 
		buildingInfo = new Object[2]; 
		citizenInfo = c.getAllPeople(false);
		cZens = new JLabel[citizenInfo.length+buildingInfo.length];
		
		createBackground(); 
		
		// creating jlabels
		populate(); 
		
		// adding 
		frame.add(pane); 
		frame.setVisible(true);
	}
	
	private void createBackground() {
		createIcon("/resources/cityHallresize.png", -30, -10, 331, 331, 1, true); 
		buildingInfo[0] = c.cityHall;  
		createIcon("/resources/bg-middle.jpg", 240, 0, 650, 331, 1, false); 
		createIcon("/resources/school.jpg", 830, 0, 441, 331, 1, true);
		buildingInfo[1] = c.school; 
	}
	
	private JLabel createIcon(String loc, int x, int y, int width, int height, Integer layer, boolean building) {
		try {
			URL resource = getClass().getResource(loc); 
			ImageIcon img = new ImageIcon(resource); 
			JLabel bgImg = new JLabel(img); 
			bgImg.setBounds(x, y, width, height); 
			pane.add(bgImg, layer);
			if (building) {
				if (x == -30) x = -60; // manually adjusting the first picture because not all the labels are the same dimensions
				JLabel info = createIcon("/resources/infoIcon.png", x+70, 10, 50, 50, 2, false); 
				int i = 0; 
				while (i != -1) {
					if (cZens[citizenInfo.length + i] == null) {
						cZens[citizenInfo.length + i] = info; 
						i = -1; 
					} else {
						i++; 
					}
				}
			}
			return bgImg; 
		} catch (NullPointerException e) {
			System.out.println("Obtaining the URL of the file at " + loc + " failed");
			e.printStackTrace();
		}
		return null;
	}
	
	private void populate() {
		for (int i = 0; i < cZens.length; i++) {
			JLabel l = new JLabel(); 
			
			if (i < citizenInfo.length) {
				Person p = (Person)citizenInfo[i]; 
				
				// setting based on where the person was placed 
				l.setBounds(380, 50+(i*10), 90, 30);
				c.school.getAllOccupants(); 
				if (c.school.isOccupantInside(p)) l.setBounds(900, 50+(i*15), 90, 30);
				c.cityHall.getAllOccupants(); 
				if (c.cityHall.isOccupantInside(p)) l.setBounds(200, 50+(i*10), 90, 30);
				
				l.setOpaque(true);
				l.setBackground(Color.orange);
				l.setText(p.getName());
				cZens[i] = l; 
				pane.add(cZens[i], new Integer(2)); 
			}
		}
	}
	
	public int getLabel(MouseEvent listener) {
		for (int i = 0; i < cZens.length; i++) {
			if (cZens[i].getBounds().contains(listener.getPoint())) {
				return i; 
			}
		}
		return -1; 
	}
	
	/** 
	 * 
	 * Input validation is suggested in mouse motion listeners
	 * @param e
	 */
	public void createToolTip(MouseEvent e) {
		Object o; 
		int index = getLabel(e); 
		if (index != -1 && !exitToolTip) {
			JLabel l = cZens[index]; 
			o = (index >= citizenInfo.length) ? buildingInfo[index-citizenInfo.length] : citizenInfo[index]; 
			tooltip = new JTextArea(); 
			
			// settings bounds based on where the person was placed 
			tooltip.setBounds(l.getX()+10, l.getY()+20, 180, 85);
			tooltip.setOpaque(true);
			tooltip.setBackground(Color.white);
			tooltip.setText(o.toString());
			pane.add(tooltip, new Integer(3)); 
			exitToolTip = true; 
		}
	}
	
	// could make much more efficient 
	public void mouseDragged(MouseEvent e) {
		int index = getLabel(e); 
		
		if (index != -1) {
			JLabel l = cZens[index]; 
			Person p = (Person)citizenInfo[index]; 
			
			if (diff == null) {
				diff = new Point(e.getX() - l.getBounds().x, e.getY() - l.getBounds().y); 
			}
			l.setBounds(e.getX() - diff.x, e.getY() - diff.y, l.getBounds().width, l.getBounds().height);
			
			// checks if the dragged label is within the bounds of the school or the city hall 
			if (l.getX() > 890) {
				if (!(c.school.isOccupantInside(p))) {
					c.school.addOccupant(p);
					System.out.printf("Added %s to %s\n", p.getName(), c.school.getName());
				}
			} else if (c.school.isOccupantInside(p)) {
				c.school.removeOccupant(p);
				System.out.printf("Removed %s from %s\n", p.getName(), c.school.getName());
			} else if (l.getX() < 295) {
				if (!(c.cityHall.isOccupantInside(p))) {
					c.cityHall.addOccupant(p);
					System.out.printf("Added %s to %s\n", p.getName(), c.cityHall.getName());
				}
			} else if (c.cityHall.isOccupantInside(p)) {
				c.cityHall.removeOccupant(p);
				System.out.printf("Removed %s from %s\n", p.getName(), c.cityHall.getName());
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
	public void mouseClicked(MouseEvent e) {
		createToolTip(e);
	}
	
	
	public void mouseMoved(MouseEvent e) {
		if (exitToolTip == true && getLabel(e) == -1) {
			pane.remove(tooltip);
			pane.validate(); 
			pane.repaint(); 
			exitToolTip = false; 
		}
	}
	public void mousePressed(MouseEvent e) {}
}

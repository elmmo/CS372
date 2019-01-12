
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.net.URL; 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputListener; 

/** 
 * GUI for the City class 
 * @author Elizabeth
 *
 */
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
	
	/** 
	 * default constructor that initializes the gui
	 */
	Conversion() {
		initialize(); 
	}
	
	/** 
	 * Runs all the initial functions and sets up variables for the rest of the class to use 
	 */
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
	
	/** 
	 * positions the images in the background of the gui
	 */
	private void createBackground() {
		createIcon("/resources/cityHallresize.png", -30, -10, 331, 331, 1, true); 
		buildingInfo[0] = c.cityHall;  
		createIcon("/resources/bg-middle.jpg", 240, 0, 650, 331, 1, false); 
		createIcon("/resources/school.jpg", 830, 0, 441, 331, 1, true);
		buildingInfo[1] = c.school; 
	}
	
	/** 
	 * Modifies the JLabel to fit the specs in the parameters 
	 * @param loc	where to find the image that will go on the icon 
	 * @param x		the x coordinate where the upper left corner should be placed
	 * @param y		the y coordinate where the upper left corner should be placed 
	 * @param width	the width of the jlabel 
	 * @param height	the height of the jlabel 
	 * @param layer		the layer to place the label at in the layered pane 
	 * @param building	if true, this is a building and should be inserted after the citizens are inside 
	 * @return			the formatted JLabel
	 */
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
	
	/** 
	 * Takes information derived from the City object to generate and place the citizens in the city 
	 */
	private void populate() {
		for (int i = 0; i < cZens.length; i++) {
			JLabel l = new JLabel(); 
			
			if (i < citizenInfo.length) {
				Person p = (Person)citizenInfo[i]; 
				
				// setting based on where the person was placed 

				l.setBounds(380+(i*11), 5+(i*19), 90, 30);
				c.school.getAllOccupants(); 
				if (c.school.isOccupantInside(p)) l.setBounds(950+(i*5), 50+(i*10), 90, 30);
				c.cityHall.getAllOccupants(); 
				if (c.cityHall.isOccupantInside(p)) l.setBounds(50+(i*5), 50+(i*10), 90, 30);
				
				l.setOpaque(true);
				l.setBackground(Color.orange);
				if (p instanceof Kid) l.setBackground(Color.pink);
				if (p instanceof Teacher) l.setBackground(Color.blue);
				if (p instanceof Police) l.setBackground(Color.gray);
				l.setText(p.getName());
				cZens[i] = l; 
				pane.add(cZens[i], new Integer(2)); 
			}
		}
	}
	
	/** 
	 * returns the index of the label that the mouse is currently on 
	 * @param listener	the cursor action to listen to 
	 * @return	the index of the label 
	 */
	public int getLabel(MouseEvent listener) {
		for (int i = 0; i < cZens.length; i++) {
			if (cZens[i].getBounds().contains(listener.getPoint())) {
				return i; 
			}
		}
		return -1; 
	}
	
	/** 
	 * Creates a tooltip out of a JTextArea that will display on click 
	 * Input validation is suggested in mouse motion listeners
	 * @param e	cursor movement to respond to 
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
	
	/** 
	 * Allows for people to be dragged from place to place 
	 * Has no effect for info labels and the background 
	 * @param e	the cursor action to respond to 
	 */
	public void mouseDragged(MouseEvent e) {
		int index = getLabel(e); 
		
		if (index != -1 && index < citizenInfo.length) {
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
	
	/** 
	 * Event listener for creating the tooltip 
	 * @param e	the cursor movement to respond to 
	 */
	public void mouseClicked(MouseEvent e) {
		createToolTip(e);
	}
	
	/** 
	 * used for recognizing when a user wants the tooltip to close 
	 * @param e	cursor movement to respond to 
	 */
	public void mouseMoved(MouseEvent e) {
		if (exitToolTip == true && getLabel(e) == -1) {
			pane.remove(tooltip);
			pane.validate(); 
			pane.repaint(); 
			exitToolTip = false; 
		}
	}
	
	/** 
	 * Required interface method that isn't used in this application 
	 */
	public void mouseEntered(MouseEvent e) {}
	
	/** 
	 * Required interface method that isn't used in this application 
	 */
	public void mouseReleased(MouseEvent e) {}
	
	/** 
	 * Required interface method that isn't used in this application 
	 */
	public void mouseExited(MouseEvent e) {}
	
	/** 
	 * Required interface method that isn't used in this application 
	 */
	public void mousePressed(MouseEvent e) {}
}

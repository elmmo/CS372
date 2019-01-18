import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel; 

/** 
 * Creates an instance of the dice throwing from the game Yahtzee
 * @author Elizabeth Min
 */
public class Yahtzee extends JFrame {
	int total; 
	int entries; 
	JLabel result; 
	YahtzeeRoll[] rolls;
	
	/** 
	 * Default constructor that sets up each part of the gui for the dice simulation
	 */
	Yahtzee() {
		// for keeping track of the running die total
		total = 0; 
		entries = 1; 
		
		// initializes the window 
		setSize(1200, 550); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(new FlowLayout()); 
		
		// for storing six instances of the yahtzee simulation 
		rolls = new YahtzeeRoll[5]; 
		
		// images of the die 
		ImageIcon[] imgs = generateImages(); 
		
		// creates the button for launching the program and adds it to to the frame 
		JButton launch = new JButton("Roll!"); 
		launch.setPreferredSize(new Dimension(1200, 50));
		launch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launch(); 
			}
		});
		this.add(launch);
		
		// keeps track of when the value of the die is stored and reached 
		PropertyChangeListener pcl = setUpChangeListener(); 
		
		// initializes each of the die 
		for (int i = 0; i < rolls.length; i++) {			
			JPanel p = new JPanel(); 
			p.setPreferredSize(new Dimension(215, 350));
			
			// sets a label, image, and caption for each die and adds them to the jpanel
			JLabel l = new JLabel(); 
			l.setIcon(imgs[0]);
			JLabel caption = generateLabel(215, 100, Color.gray, "?", null); 
			caption.addPropertyChangeListener(pcl); 
			rolls[i] = new YahtzeeRoll(l, caption, imgs);
			p.add(l); 
			p.add(caption);
			this.add(p); 
		}
		// creates the label for showing the result of the dice rolls and adds it to the frame 
		result = generateLabel(1200, 100, Color.gray, "???", null);
		this.add(result);
		
		this.setVisible(true);
	}
	
	/** 
	 * populates the images array for use in assigning to the dice 
	 * @return	an array of image icons that can be passed to Swing components
	 */
	private ImageIcon[] generateImages() {
		ImageIcon[] images = new ImageIcon[6];
		for (int i = 1; i <= images.length; i++) {
			URL resource = getClass().getResource("resources/" + i + ".png"); 
			images[i-1] = new ImageIcon(resource); 
		}
		return images; 
	}
	
	/** 
	 * Generates a JLabel based on the parameters 
	 * @param width		how wide the JLabel should be 
	 * @param height	how tall the JLabel should be 
	 * @param c			color 
	 * @param text		text to display 
	 * @param img		background image 
	 * @return			the generated JLabel
	 */
	private JLabel generateLabel(int width, int height, Color c, String text, ImageIcon img) {
		JLabel temp = new JLabel(); 
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(c); 
		temp.setOpaque(true);
		temp.setHorizontalAlignment(JLabel.CENTER);
		temp.setText(text);
		if (img != null) { // for captions and text-based labels
			temp.setIcon(img);
		}
		return temp; 
	}
	
	/** 
	 * Sets up a change listener for when the text of a label is changed 
	 * @return	the configured change listener
	 */
	private PropertyChangeListener setUpChangeListener() {
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent pce) {
				// validates that the changed property is the one that's being targeted
				String property = pce.getPropertyName();
				if (property.equals("text")) {
					// changed value is used to calculate running total of the dice 
					String val = (String)pce.getNewValue(); 
					total += Integer.parseInt(val); 
					entries++; // keeps track of how many dice have determined their values 
					// if all entries are in 
					if (entries == 6) {
						result.setBackground(Color.green);
						result.setText(Integer.toString(total));
					}
				}
			}
		}; 
		return propertyChangeListener; 
	}
	
	/** 
	 * starts the yahtzee instance 
	 */
	private void launch() {
		for (int i = 0; i < rolls.length; i++) {
			Thread t = new Thread(rolls[i]); 
			t.start(); 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

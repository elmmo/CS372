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

public class Yahtzee extends JFrame {
	int total; 
	int entries; 
	JLabel result; 
	YahtzeeRoll[] rolls;
	
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
	
	private ImageIcon[] generateImages() {
		// populate images with the dice images 
		ImageIcon[] images = new ImageIcon[6];
		for (int i = 1; i <= images.length; i++) {
			URL resource = getClass().getResource("resources/" + i + ".png"); 
			images[i-1] = new ImageIcon(resource); 
		}
		return images; 
	}
	
	private JLabel generateLabel(int width, int height, Color c, String text, ImageIcon img) {
		JLabel temp = new JLabel(); 
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(c); 
		temp.setOpaque(true);
		temp.setHorizontalAlignment(JLabel.CENTER);
		temp.setText(text);
		if (img != null) {
			temp.setIcon(img);
		}
		return temp; 
	}
	
	private PropertyChangeListener setUpChangeListener() {
		PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent pce) {
				String property = pce.getPropertyName();
				if (property.equals("text")) {
					String val = (String)pce.getNewValue(); 
					total += Integer.parseInt(val); 
					entries++; 
					if (entries == 6) {
						result.setBackground(Color.green);
						result.setText(Integer.toString(total));
					}
				}
			}
		}; 
		return propertyChangeListener; 
	}
	
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

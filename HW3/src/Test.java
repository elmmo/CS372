import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JSeparator;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class Test {

	private JFrame frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// getting the images for building (the only commonly-used building) 
		URL buildingUrl = getClass().getResource("resources/Building.png"); 
		URL cityHallUrl = getClass().getResource("/resources/cityHall.png"); 
		URL schoolUrl = getClass().getResource("/resources/School.png"); 
		
		// setting up the window for the gui to appear 
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{37, 50, 50, 71, 69, 60, 79, 0};
		gridBagLayout.rowHeights = new int[]{50, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel commCenter = new JLabel("");
		commCenter.setToolTipText("Community Center");
		createIcon(commCenter, "Community Center", buildingUrl, 0, 0, 5, 5, 4, 1, false); 
		
		JLabel cityHall = new JLabel("");
		createIcon(cityHall, "City Hall", cityHallUrl, 0, 0, 5, 5, 5, 1, false); 
		
		JLabel church = new JLabel("");
		GridBagConstraints churchConstraints = createIcon(church, "Church", buildingUrl, 0, 0, 5, 5, 1, 2, true); 
		church.setAlignmentX(Component.RIGHT_ALIGNMENT);
		church.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(church, churchConstraints);
		
		JLabel cafeSunMoon = new JLabel("");
		createIcon(cafeSunMoon, "Cafe Sun and Moon", buildingUrl, 0, 0, 5, 5, 2, 2, false); 
		
		JLabel driveThruBoba = new JLabel("");
		GridBagConstraints bobaConstraints = createIcon(driveThruBoba, "Drive Thru Boba", buildingUrl, 0, 0, 5, 5, 1, 3, true); 
		driveThruBoba.setAlignmentX(1.0f);
		bobaConstraints.anchor = GridBagConstraints.NORTHWEST;
		frame.getContentPane().add(driveThruBoba, bobaConstraints);
		
		JLabel esc = new JLabel("");
		createIcon(esc, "Educational Service Center", buildingUrl, 0, 0, 0, 5, 3, 4, false); 
		
		JLabel school = new JLabel();
		createIcon(school, "School", schoolUrl, 0, 0, 0, 5, 4, 4, false); 
	}
	
	private GridBagConstraints createIcon(JLabel label, String text, URL location, int top, int left, int bottom, int right, int x, int y, boolean more) {
		label.setIcon(new ImageIcon(location));
		label.setToolTipText(text);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(top, left, bottom, right); 
		gbc.gridx = x; 
		gbc.gridy = y; 
		if (!(more)) {
			frame.getContentPane().add(label, gbc);
		}
		return gbc; 
	}

}

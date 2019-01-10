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

public class Test {

	private JFrame frame;
	private JTextField txtName;
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
		// getting the images for buildings 
		URL buildingUrl = getClass().getResource("resources/Building.png"); 
		Image buildingImg = toolkit.getImage(buildingUrl); 
		URL cityHallUrl = getClass().getResource("resources/cityHall.png"); 
		Image cityHallImg = toolkit.getImage(cityHallUrl); 
		URL schoolUrl = getClass().getResource("resources/School.png"); 
		Image schoolImg = toolkit.getImage(schoolUrl); 
		
		// getting the images for people 
		URL personUrl = getClass().getResource("resources/person.png"); 
		Image personImg = toolkit.getImage(personUrl); 
		URL kidUrl = getClass().getResource("resources/kid.png"); 
		Image kidImg = toolkit.getImage(kidUrl); 
		URL policeUrl = getClass().getResource("resources/police.png"); 
		Image policeImg = toolkit.getImage(policeUrl); 
		URL teacherUrl = getClass().getResource("resources/teacher.png"); 
		Image teacherImg = toolkit.getImage(teacherUrl); 
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel nameLabel = new JLabel("Name");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 20, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 1;
		frame.getContentPane().add(nameLabel, gbc_nameLabel);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 1;
		frame.getContentPane().add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblImage = new JLabel("");
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.gridx = 2;
		gbc_lblImage.gridy = 2;
		lblImage.setIcon(new ImageIcon(buildingImg));
		frame.getContentPane().add(lblImage, gbc_lblImage);
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea; 
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller extends JPanel implements ListSelectionListener {
	JFrame frame; 
	JSplitPane splitPane; 
	HashMap<Integer, Shape> shapes; 
	Integer[] ids; 
	JLabel icon; 
	JTextArea info; 
	Model m; 
	JList options; 
	
	Controller() {
		initialize(); 
	}
	
	private void initialize() {
		frame = new JFrame("Shapes Are Fun!"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// gets data from the model 
		m = new Model(); 
		shapes = m.getShapes(); 
		ids = m.getIds(); 
		
		// uses model data to create the options sidebar 
		options = new JList(m.getInfo()); 
		options.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		options.setSelectedIndex(0);
		options.addListSelectionListener(this);
		JScrollPane scroll = new JScrollPane(options); 
		scroll.setMinimumSize(new Dimension(50, 100));
		
		// creates the content pane to display more about the selected option
		JPanel display = new JPanel(); 
		display.setMinimumSize(new Dimension(100, 70));
		
		// sets up the display picture in the content pane 
		icon = new JLabel(); 
		icon.setHorizontalAlignment(JLabel.LEFT);
		
		// sets up the info section in the content pane 
		info = new JTextArea(); 
		info.setEditable(true);
		info.setPreferredSize(new Dimension(180, 120));
		info.setOpaque(true);
		info.setBackground(Color.white);

		display.add(icon); 
		display.add(info); 
		
		// creates the split pane 
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, display); 
		splitPane.setDividerLocation(150);
		
		splitPane.setPreferredSize(new Dimension(600, 200)); 
		renderDisplay(options.getSelectedIndex()); // renders the display based on the first shape in the list
		
		frame.add(splitPane); 
		frame.pack(); 
		frame.setVisible(true);
	}
	
	private void renderDisplay(int selectedIndex) {
		try {
			// get item 
			Shape item = getShape(ids[selectedIndex]); 
			String shape = item.getKind(); 
			String caption = item.getDetailString();
			
			// set image 
			URL resource = getClass().getResource("resources/" + shape + ".png"); 
			ImageIcon img = new ImageIcon(resource); 
			icon.setIcon(img);
			
			// set text 
			info.setText(caption);
		} catch (NullPointerException e) {
			System.out.println("Obtaining the URL of the requested image file failed");
			e.printStackTrace();
		}
	}
	
	private Shape getShape(Integer key) {
		// if no key is passed in to search the shapes hashmap for, default to the first shape
		if (key == null) { key = ids[0]; }
		return shapes.get(key); 
	}
	
	// event listener for the options list 
	public void valueChanged(ListSelectionEvent e) {
		JList list = (JList)e.getSource(); 
		renderDisplay(list.getSelectedIndex()); 
	}
	
}
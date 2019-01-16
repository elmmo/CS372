import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel; 

public class Yahtzee extends JFrame {
	Yahtzee() {
		setSize(1200, 450); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(new FlowLayout()); 
		
		YahtzeeRoll[] rolls = new YahtzeeRoll[5]; 
		
		for (int i = 0; i < rolls.length; i++) {			
			JPanel p = new JPanel(); 
			p.setPreferredSize(new Dimension(215, 350));
			
			JLabel caption = new JLabel(); 
			caption.setPreferredSize(new Dimension(215, 100)); 
			caption.setBackground(Color.white); 
			caption.setOpaque(true);
			caption.setHorizontalAlignment(JLabel.CENTER);
			caption.setText("?");
			
			JLabel l = new JLabel(); 
			rolls[i] = new YahtzeeRoll(l, caption);
			p.add(l); 
			this.add(p); 
			
			p.add(caption);
		}
		
		this.setVisible(true);
		
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

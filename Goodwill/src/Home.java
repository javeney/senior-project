import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Home extends JPanel {

	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public Home() {
		setup();
	}
	
	private void setup(){
		
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 0, 742, 586);
		add(panel);
		panel.setLayout(null);
		
		initializeLogo();
		initializeText();
	}
	
	private void initializeLogo(){
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Home.class.getResource("/resources/logo.png")));
		lblLogo.setBounds(50, 50, 642, 77);
		panel.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/resources/quick_guide.png")));
		lblNewLabel.setBounds(25, 138, 629, 388);
		panel.add(lblNewLabel);
	}
	
	private void initializeText(){
	}
}

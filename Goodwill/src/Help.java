
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ScrollPaneConstants;

/**
 * Help.java
 * Provides relevant information for User to follow if needed
 * 
 * @author javeney
 * 
 * Modification: 3/9/16- Created class
 *
 */
@SuppressWarnings("serial")
public class Help extends JPanel {

	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public Help() {
		setup();
	}

	private void setup(){
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 0, 742, 586);
		add(panel);
		panel.setLayout(null);
		
		createTextArea();
	}
	
	private void createTextArea(){
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(38, 37, 665, 511);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		openFile(textArea);
	}
	
	private void openFile(JTextArea txtArea){
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(
					new File(Help.class.getResource("/resources/readme.txt").getPath())));
			txtArea.read(br, null);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

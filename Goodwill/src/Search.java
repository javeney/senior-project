
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Search.java
 * Stores the item information and makes data accesible through
 * getters and setters
 * 
 * @author javeney
 * 
 * Modification: 3/9/16- Created class
 *
 */
@SuppressWarnings("serial")
public class Search extends JPanel {

	private JPanel panel, panelSearch; 
	private JScrollPane scrollPane;
	private JTextField txtSearch;
	private JTable table;
	private DatabaseController db;
	
	private String[][] results = null;
	
	/**
	 * Create the panel.
	 */
	public Search(DatabaseController db) {
		this.db = db;
		setup();
		createTextfields();
		createButtons();
		createTable(results);
	}
	
	private void setup(){
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 0, 742, 586);
		add(panel);
		panel.setLayout(null);
		
		panelSearch = new JPanel();
		panelSearch.setForeground(new Color(0, 0, 139));
		panelSearch.setBounds(130, 32, 482, 62);
		panelSearch.setBackground(new Color(240, 248, 255));
		panel.add(panelSearch);
		panelSearch.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(66, 105, 610, 450);
		scrollPane.setBackground(new Color(240, 248, 255));
		panel.add(scrollPane);		
	}
	
	private void createTextfields(){
		txtSearch = new JTextField();
		txtSearch.setBounds(10, 11, 405, 36);
		txtSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panelSearch.add(txtSearch);
		txtSearch.setColumns(10);
	}
	
	private void createButtons(){
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createTable(db.search(txtSearch.getText()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setRolloverIcon(new ImageIcon(Search.class.getResource("/resources/goBtnHover.png")));
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		btnSearch.setBorder(null);
		btnSearch.setIcon(new ImageIcon(Search.class.getResource("/resources/goBtn.png")));
		btnSearch.setBounds(425, 11, 47, 36);
		panelSearch.add(btnSearch);
	}
	
	private void createTable(String[][] results){
		String[] columnNames = {"ID", "Category", "Description", "Condition", 
				"Location", "Store", "Date", "Time", "Employee", 
				"Location", "Store", "Date", "Time", "Employee", 
				"Location", "Store", "Date", "Time", "Employee",
				"Location", "Store", "Date", "Time", "Employee", 
				"Location", "Store", "Date", "Time", "Employee" };
		
		try{
			results = db.search(txtSearch.getText());
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		table = new JTable(results, columnNames);
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		table.setRowMargin(4);
		table.setRowHeight(25);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(500);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		table.getColumnModel().getColumn(5).setPreferredWidth(52);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(250);
		table.getColumnModel().getColumn(9).setPreferredWidth(110);
		table.getColumnModel().getColumn(10).setPreferredWidth(52);
		table.getColumnModel().getColumn(11).setPreferredWidth(90);
		table.getColumnModel().getColumn(12).setPreferredWidth(80);
		table.getColumnModel().getColumn(13).setPreferredWidth(250);
		table.getColumnModel().getColumn(14).setPreferredWidth(110);
		table.getColumnModel().getColumn(15).setPreferredWidth(52);
		table.getColumnModel().getColumn(16).setPreferredWidth(90);
		table.getColumnModel().getColumn(17).setPreferredWidth(80);
		table.getColumnModel().getColumn(18).setPreferredWidth(250);
		table.getColumnModel().getColumn(19).setPreferredWidth(110);
		table.getColumnModel().getColumn(20).setPreferredWidth(52);
		table.getColumnModel().getColumn(21).setPreferredWidth(90);
		table.getColumnModel().getColumn(22).setPreferredWidth(80);
		table.getColumnModel().getColumn(23).setPreferredWidth(250);
		table.getColumnModel().getColumn(24).setPreferredWidth(110);
		table.getColumnModel().getColumn(25).setPreferredWidth(52);
		table.getColumnModel().getColumn(26).setPreferredWidth(90);
		table.getColumnModel().getColumn(27).setPreferredWidth(80);
		table.getColumnModel().getColumn(28).setPreferredWidth(250);
		
		table.setDragEnabled(false);
		table.setBackground(new Color(255, 255, 255));
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
	}
}

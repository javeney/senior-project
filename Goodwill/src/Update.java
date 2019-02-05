
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Update extends JPanel {
	
	/*Declare text fields*/
	private JTextField txtID, txtStore, txtDate,
		txtTime, txtEmployee;
	private JTextArea txtDescription;
	private JComboBox<String> comboCategory, comboCondition, comboLocation;
	private JScrollPane scrollPane;
	private JPanel panel, panelHide, btnPanel, panelLbl, panelStore;
	private List<JTextField> fields;
	private List<JComboBox<String>> boxes;
	private DatabaseController db;
	private JLabel lblChar;
	
	/**
	 * Create the panel.
	 */
	public Update(DatabaseController db) {
		this.db = db;
		
		setup();
		initializeID();
		initializeCategory();
		initializeDescription();
		initializeCondition();
		initializeLocation();
		initializeDateTime();
		initializeEmployee();
		createButtons();
	}
	
	private void setup(){
		setLayout(null);
		panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(0, 0, 742, 586);
		add(panel);
		panel.setLayout(null);
		
		btnPanel = new JPanel();
		btnPanel.setBackground(new Color(240, 248, 255));
		btnPanel.setBounds(141, 486, 459, 60);
		panel.add(btnPanel);
		btnPanel.setVisible(false);
		btnPanel.setLayout(null);
		
		panelHide = new JPanel();
		panelHide.setBackground(new Color(240, 248, 255));
		panelHide.setVisible(false);
		panelHide.setBounds(33, 80, 671, 388);
		panel.add(panelHide);
		panelHide.setLayout(null);
		
		panelLbl = new JPanel();
		panelLbl.setBounds(0, 0, 115, 383);
		panelHide.add(panelLbl);
		panelLbl.setBackground(new Color(240, 248, 255));
		panelLbl.setLayout(null);		
		
		panelStore = new JPanel();
		panelStore.setBounds(529, 207, 142, 37);
		panelHide.add(panelStore);
		panelStore.setBackground(new Color(240, 248, 255));
		panelStore.setVisible(false);
		panelStore.setLayout(null);	

		scrollPane = new JScrollPane();
		scrollPane.setBounds(125, 47, 546, 89);
		panelHide.add(scrollPane);	
		
		fields = new ArrayList<JTextField>();
		boxes = new ArrayList<JComboBox<String>>();
	}
	
	private void initializeID(){
		JLabel lblID = new JLabel("I.D.");
		lblID.setBounds(33, 30, 115, 36);
		panel.add(lblID);
		lblID.setForeground(new Color(0, 0, 139));
		lblID.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtID = new JTextField();
		txtID.setBounds(158, 33, 464, 36);
		panel.add(txtID);
		txtID.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtID.setColumns(10);
		txtID.setBackground(Color.WHITE);
		txtID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isDigit(c))) || txtID.getText().length() > 35){
					e.consume();
				}
			}
		});
		fields.add(txtID);
	}
	
	private void initializeCategory(){
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(0, 0, 115, 30);
		panelLbl.add(lblCategory);
		lblCategory.setForeground(new Color(0, 0, 139));
		lblCategory.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		comboCategory = new JComboBox<String>();
		comboCategory.setBorder(null);
		comboCategory.setBackground(new Color(255, 255, 255));
		comboCategory.setBounds(125, 0, 546, 36);
		panelHide.add(comboCategory);
		comboCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Mobile phone", "Tablet/E-reader", 
				"Laptop computer", "Video game console", "Desktop computer", "Media player", "Other (Please describe below in detail)"}));
		comboCategory.setSelectedIndex(0);
		comboCategory.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboCategory);
	}
	
	private void initializeDescription(){
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(0, 47, 115, 30);
		panelLbl.add(lblDescription);
		lblDescription.setForeground(new Color(0, 0, 139));
		lblDescription.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		lblChar = new JLabel("");
		lblChar.setBounds(625, 135, 46, 14);
		panelHide.add(lblChar);
		
		txtDescription = new JTextArea();
		scrollPane.setViewportView(txtDescription);
		txtDescription.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int chars = txtDescription.getText().length();
				lblChar.setText(chars + "/180");
			}
		});
		txtDescription.setWrapStyleWord(true);
		txtDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDescription.setLineWrap(true);
	}
	
	private void initializeCondition(){
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(0, 159, 115, 36);
		panelLbl.add(lblCondition);
		lblCondition.setForeground(new Color(0, 0, 139));
		lblCondition.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		comboCondition = new JComboBox<String>();
		comboCondition.setBorder(null);
		comboCondition.setBackground(new Color(255, 255, 255));
		comboCondition.setBounds(125, 162, 546, 36);
		panelHide.add(comboCondition);
		comboCondition.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Like new", 
				"Very good", "Good", "Acceptable", "Poor", "Refurbished"}));
		comboCondition.setSelectedIndex(0);
		comboCondition.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboCondition);
	}
	
	private void initializeLocation(){
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(0, 206, 115, 36);
		panelLbl.add(lblLocation);
		lblLocation.setForeground(new Color(0, 0, 139));
		lblLocation.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JLabel lblStore = new JLabel("Store");
		lblStore.setBounds(0, 0, 50, 36);
		panelStore.add(lblStore);
		lblStore.setForeground(new Color(0, 0, 139));
		lblStore.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtStore = new JTextField();
		txtStore.setBounds(61, 1, 81, 36);
		panelStore.add(txtStore);
		txtStore.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtStore.setColumns(10);
		txtStore.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isDigit(c))) || txtStore.getText().length() > 6){
					e.consume();
				}
			}
		});
		fields.add(txtStore);
		
		comboLocation = new JComboBox<String>();
		comboLocation.setBorder(null);
		comboLocation.setBackground(new Color(255, 255, 255));
		comboLocation.setBounds(125, 209, 393, 36);
		panelHide.add(comboLocation);
		comboLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboLocation.getSelectedItem() == "Store"){
					panelStore.setVisible(true);
				}else{
					txtStore.setText("");
					panelStore.setVisible(false);
				}
			}
		});
		comboLocation.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Salvaging", 
				"E-commerce", "Store", "Refurbishment"}));
		comboLocation.setSelectedIndex(0);
		comboLocation.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboLocation);
	}
	
	private void initializeDateTime(){
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(0, 253, 115, 36);
		panelLbl.add(lblDate);
		lblDate.setForeground(new Color(0, 0, 139));
		lblDate.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(0, 300, 115, 36);
		panelLbl.add(lblTime);
		lblTime.setForeground(new Color(0, 0, 139));
		lblTime.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtDate = new JTextField();
		txtDate.setBounds(125, 256, 284, 36);
		txtDate.setBackground(new Color(255, 255, 255));
		panelHide.add(txtDate);
		txtDate.setText(setDate());
		txtDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDate.setEditable(false);
		txtDate.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBounds(125, 303, 284, 36);
		txtTime.setBackground(new Color(255, 255, 255));
		txtTime.setText(setTime());
		panelHide.add(txtTime);
		txtTime.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTime.setEditable(false);
		txtTime.setColumns(10);
	}
	
	private void initializeEmployee(){
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setBounds(0, 347, 115, 36);
		panelLbl.add(lblEmployee);
		lblEmployee.setForeground(new Color(0, 0, 139));
		lblEmployee.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtEmployee = new JTextField();
		txtEmployee.setBounds(125, 350, 546, 36);
		panelHide.add(txtEmployee);
		txtEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmployee.setColumns(10);
		txtEmployee.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isSpaceChar(c)) && !(Character.isAlphabetic(c))) || txtEmployee.getText().length() > 40){
					e.consume();
				}
			}
		});
		fields.add(txtEmployee);
	}
	
	private void createButtons(){
		JButton btnSave = new JButton("");
		btnSave.setToolTipText("Submit changes.");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkInput() == false){
					JOptionPane.showMessageDialog(null, "Please fill out all fields to save changes.");
				}else{ 
					db.update(txtID.getText(), comboCategory.getSelectedItem().toString(), txtDescription.getText(), comboCondition.getSelectedItem().toString(),
						comboLocation.getSelectedItem().toString(), txtStore.getText(), txtDate.getText(), txtTime.getText(), txtEmployee.getText());
					JOptionPane.showMessageDialog(null, "Changes have been saved.");
					clear();
				}
			}
		});
		btnSave.setRolloverIcon(new ImageIcon(Register.class.getResource("/resources/saveBtn.png")));
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.setBorder(null);
		btnSave.setIcon(new ImageIcon(Register.class.getResource("/resources/saveBtnHover.png")));
		btnSave.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSave.setBounds(256, 0, 150, 60);
		btnSave.setFocusPainted(false);
		btnPanel.add(btnSave);
		
		JButton btnClear = new JButton("");
		btnClear.setToolTipText("Clear all text and selections.");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setRolloverIcon(new ImageIcon(Register.class.getResource("/resources/clearBtn.png")));
		btnClear.setBorderPainted(false);
		btnClear.setContentAreaFilled(false);
		btnClear.setBorder(null);
		btnClear.setIcon(new ImageIcon(Register.class.getResource("/resources/clearBtnHover.png")));
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnClear.setBounds(53, 0, 150, 60);
		btnClear.setFocusPainted(false);
		btnPanel.add(btnClear);
		
		JButton btnGo = new JButton("");
		btnGo.setToolTipText("Search for ID");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] results = db.IDLookup(txtID.getText());
				
				if(results != null){
					setInput(results);
					panelHide.setVisible(true);
					btnPanel.setVisible(true);
					txtID.setEditable(false);
				}else{
					JOptionPane.showMessageDialog(null, "ID has not been registered yet.");
				}
				
			}
		});
		btnGo.setBorderPainted(false);
		btnGo.setContentAreaFilled(false);
		btnGo.setRolloverIcon(new ImageIcon(Update.class.getResource("/resources/goBtnHover.png")));
		btnGo.setOpaque(false);
		btnGo.setIcon(new ImageIcon(Update.class.getResource("/resources/goBtn.png")));
		btnGo.setBounds(632, 33, 57, 36);
		btnGo.setFocusPainted(false);
		panel.add(btnGo);
	}
	
	/**
	 * clear all text fields and return combo boxes to 0
	 */
	private void clear(){
		for(JTextField field : fields){
			field.setText("");
		}
		txtDescription.setText("");
		for(JComboBox<String> box: boxes){
			box.setSelectedIndex(0);
		}
		panelStore.setVisible(false);
		panelHide.setVisible(false);
		btnPanel.setVisible(false);
		txtID.setEditable(true);
	}
	
	/**
	 * check if all fields are filled out before save can be done
	 */
	private boolean checkInput(){
		if(txtID.getText().isEmpty()){ return false; }
		if(txtDescription.getText().isEmpty()){ return false; }
		if(txtEmployee.getText().isEmpty()){ return false; }
		if(comboLocation.getSelectedIndex() == 0) { return false; }
		if(comboLocation.getSelectedItem() == "Store"){
			if(txtStore.getText().isEmpty()){ return false; } }
		if(comboCategory.getSelectedIndex() == 0){ return false; }
		if(comboCondition.getSelectedIndex() == 0){ return false; }
		
		return true; 
	}// end checkInput()
	
	private String setDate(){
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
		Date now = Calendar.getInstance().getTime();
		return date.format(now);
	}
	
	private String setTime(){
		SimpleDateFormat time = new SimpleDateFormat("h:mm a");
		Date now = Calendar.getInstance().getTime();
		return time.format(now);
	}
	
	private void setInput(String[] results){
		txtID.setText(results[0]);
		comboCategory.setSelectedItem(results[1]);
		txtDescription.setText(results[2]);
		lblChar.setText(txtDescription.getText().length() + "/180");
		comboCondition.setSelectedItem(results[3]);
	}
}


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class Register extends JPanel {
	
	/*Declare text fields*/
	private JTextField txtID, txtStore, txtDate,
		txtTime, txtEmployee;
	private JTextArea txtDescription;
	private JComboBox<String> comboCategory, comboCondition, comboLocation;
	private JPanel panel, btnPanel, panelLbl, panelStore;
	private JScrollPane scrollPane;
	private List<JTextField> fields;
	private List<JComboBox<String>> boxes;
	private DatabaseController db;
	
	/**
	 * Create the panel.
	 */
	public Register(DatabaseController db) {
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
		clear();
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
		btnPanel.setLayout(null);
		
		panelLbl = new JPanel();
		panelLbl.setBackground(new Color(240, 248, 255));
		panelLbl.setBounds(33, 30, 115, 433);
		panel.add(panelLbl);
		panelLbl.setLayout(null);
		
		panelStore = new JPanel();
		panelStore.setBackground(new Color(240, 248, 255));
		panelStore.setBounds(562, 287, 142, 37);
		panel.add(panelStore);
		panelStore.setVisible(false);
		panelStore.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(158, 127, 546, 89);
		panel.add(scrollPane);
		
		fields = new ArrayList<JTextField>();
		boxes = new ArrayList<JComboBox<String>>();
	}
	
	private void initializeID(){
		JLabel lblID = new JLabel("I.D.");
		lblID.setBounds(0, 0, 115, 36);
		panelLbl.add(lblID);
		lblID.setForeground(new Color(0, 0, 139));
		lblID.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtID = new JTextField();
		txtID.setBounds(158, 33, 546, 36);
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
		lblCategory.setBounds(0, 47, 115, 36);
		panelLbl.add(lblCategory);
		lblCategory.setForeground(new Color(0, 0, 139));
		lblCategory.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		comboCategory = new JComboBox<String>();
		comboCategory.setBorder(null);
		comboCategory.setBackground(new Color(255, 255, 255));
		comboCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Mobile phone", "Tablet/E-reader", 
				"Laptop computer", "Video game console", "Desktop computer", "Media player", "Other"}));
		comboCategory.setSelectedIndex(0);
		comboCategory.setBounds(158, 80, 546, 36);
		panel.add(comboCategory);
		comboCategory.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboCategory);
	}
	
	private void initializeDescription(){
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(0, 94, 115, 36);
		panelLbl.add(lblDescription);
		lblDescription.setForeground(new Color(0, 0, 139));
		lblDescription.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JLabel lblChar = new JLabel("");
		lblChar.setBounds(658, 217, 46, 14);
		panel.add(lblChar);
		
		txtDescription = new JTextArea();
		txtDescription.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				int chars = txtDescription.getText().length();
				lblChar.setText(chars + "/180");
			}
		});
		scrollPane.setViewportView(txtDescription);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDescription.setLineWrap(true);	
	}
	
	private void initializeCondition(){
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(0, 209, 115, 36);
		panelLbl.add(lblCondition);
		lblCondition.setForeground(new Color(0, 0, 139));
		lblCondition.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		comboCondition = new JComboBox<String>();
		comboCondition.setBorder(null);
		comboCondition.setBackground(new Color(255, 255, 255));
		comboCondition.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Like new", 
				"Very good", "Good", "Acceptable", "Poor", "Refurbished"}));
		comboCondition.setSelectedIndex(0);
		comboCondition.setBounds(158, 242, 546, 36);
		panel.add(comboCondition);
		comboCondition.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboCondition);
	}
	
	private void initializeLocation(){
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(0, 256, 115, 36);
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
		txtStore.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isDigit(c))) || txtStore.getText().length() > 6){
					e.consume();
				}
			}
		});
		txtStore.setColumns(10);	
		fields.add(txtStore);	
		
		comboLocation = new JComboBox<String>();
		comboLocation.setBackground(new Color(255, 255, 255));
		comboLocation.setBorder(null);
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
		comboLocation.setBounds(158, 289, 393, 36);
		panel.add(comboLocation);
		comboLocation.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		boxes.add(comboLocation);
	}
	
	private void initializeDateTime(){
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(0, 303, 115, 36);
		panelLbl.add(lblDate);
		lblDate.setForeground(new Color(0, 0, 139));
		lblDate.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(0, 350, 115, 36);
		panelLbl.add(lblTime);
		lblTime.setForeground(new Color(0, 0, 139));
		lblTime.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtDate = new JTextField();
		txtDate.setBackground(new Color(255, 255, 255));
		txtDate.setEditable(false);
		txtDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtDate.setText(setDate());
		txtDate.setBounds(158, 336, 284, 36);
		panel.add(txtDate);
		txtDate.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBackground(new Color(255, 255, 255));
		txtTime.setEditable(false);
		txtTime.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtTime.setText(setTime());
		txtTime.setBounds(158, 383, 284, 36);
		panel.add(txtTime);
		txtTime.setColumns(10);
	}
	
	private void initializeEmployee(){
		JLabel lblEmployee = new JLabel("Employee");
		lblEmployee.setBounds(0, 397, 115, 36);
		panelLbl.add(lblEmployee);
		lblEmployee.setForeground(new Color(0, 0, 139));
		lblEmployee.setFont(new Font("Century Gothic", Font.BOLD, 18));
		
		txtEmployee = new JTextField();
		txtEmployee.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((!(Character.isSpaceChar(c)) && !(Character.isAlphabetic(c))) || txtEmployee.getText().length() > 40){
					e.consume();
				}
			}
		});
		txtEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmployee.setBounds(158, 430, 546, 36);
		panel.add(txtEmployee);
		txtEmployee.setColumns(10);
		fields.add(txtEmployee);
	}

	private void createButtons(){
		JButton btnSave = new JButton("");
		btnSave.setToolTipText("Submit changes.");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkInput()){
					try {
						if(!db.dupeCheck(txtID.getText())){
							db.add(txtID.getText(), comboCategory.getSelectedItem().toString(), txtDescription.getText(), comboCondition.getSelectedItem().toString(),
									comboLocation.getSelectedItem().toString(), txtStore.getText(), txtDate.getText(), txtTime.getText(), txtEmployee.getText());
							JOptionPane.showMessageDialog(null, "Changes saved.");
							clear();
						}else{ 
							JOptionPane.showMessageDialog(null, "Item ID already exists. Please enter a different ID.");
						}
					} catch (HeadlessException | SQLException e1) {
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Please fill out all fields to save changes.");
					
				}
			}
		});

		btnSave.setFocusPainted(false);
		btnSave.setRolloverIcon(new ImageIcon(Register.class.getResource("/resources/saveBtn.png")));
		btnSave.setBorderPainted(false);
		btnSave.setContentAreaFilled(false);
		btnSave.setBorder(null);
		btnSave.setIcon(new ImageIcon(Register.class.getResource("/resources/saveBtnHover.png")));
		btnSave.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnSave.setBounds(256, 0, 150, 60);
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
	}
	
	private void clear(){
		for(JTextField field : fields){
			field.setText("");
		}
		txtDescription.setText("");
		for(JComboBox<String> box: boxes){
			box.setSelectedIndex(0);
		}
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
		SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
		Date now = Calendar.getInstance().getTime();
		return time.format(now);
	}
}

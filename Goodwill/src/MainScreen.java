
import javax.swing.JFrame;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Cursor;

/**
 * MainScreen.java
 * Main GUI screen, has controls to switch through panels that make up entire
 * GUI. 
 * 
 * @author javeney
 * 
 * Modification: 3/9/16- Created class with all fields not related to location
 *
 */
public class MainScreen {

	private JFrame frmMain;
	private JPanel panelView, panelMain, panelBtn, panelRegister, 
		panelUpdate, panelSearch, panelHelp, panelStatus, panelHome;
	private CardLayout cards;
	private DatabaseController db;
	
	/**
	 * Create the application.
	 */
	public MainScreen(DatabaseController db) {
		this.db = db;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		setup();
		createButtons();
		frmMain.setVisible(true);
	}
	
	private void setup(){
		frmMain = new JFrame();
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage(MainScreen.class.getResource("/resources/Icon1.png")));
		frmMain.setBounds(100, 100, 999, 700);
		frmMain.setResizable(false);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setBounds(0, 0, 993, 671);
		frmMain.getContentPane().add(panelMain);
		panelMain.setLayout(null);
		
		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 255));
		panelBtn.setBounds(10, 11, 221, 586);
		panelMain.add(panelBtn);
		panelBtn.setLayout(null);
		
		cards = new CardLayout();
		
		panelView = new JPanel();
		panelView.setBackground(new Color(240, 248, 255));
		panelView.setBounds(241, 11, 742, 586);
		panelMain.add(panelView);
		panelView.setLayout(cards);
		
		panelHome = new Home();
		panelView.add(panelHome, "home");
		cards.show(panelView, "home");
		
		panelStatus = new Status(db);
		panelStatus.setBackground(new Color(255, 255, 255));
		panelStatus.setBounds(10, 621, 973, 40);
		panelMain.add(panelStatus);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 139));
		separator.setForeground(new Color(0, 0, 139));
		separator.setBounds(9, 608, 974, 2);
		panelMain.add(separator);
		
	}
	
	private void createButtons(){
		
		JButton btnRegister = new JButton("");
		btnRegister.setToolTipText("Add a new item.");
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setRolloverIcon(new ImageIcon(MainScreen.class.getResource("/resources/registerHover.png")));
		btnRegister.setBorderPainted(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setBorder(null);
		btnRegister.setIcon(new ImageIcon(MainScreen.class.getResource("/resources/register.png")));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRegister = new Register(db);
				panelView.add(panelRegister, "register");
				cards.show(panelView, "register");
			}
		});
		btnRegister.setFocusPainted(false);
		btnRegister.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnRegister.setBounds(10, 25, 200, 115);
		panelBtn.add(btnRegister);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.setToolTipText("Update a registered item.");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setIcon(new ImageIcon(MainScreen.class.getResource("/resources/update.png")));
		btnUpdate.setRolloverIcon(new ImageIcon(MainScreen.class.getResource("/resources/updateHover.png")));
		btnUpdate.setBorderPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setBorder(null);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelUpdate = new Update(db);
				panelView.add(panelUpdate, "update");
				cards.show(panelView, "update");
			}
		});
		btnUpdate.setFocusPainted(false);
		btnUpdate.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnUpdate.setBounds(10, 165, 200, 115);
		panelBtn.add(btnUpdate);
		
		JButton btnSearch = new JButton("");
		btnSearch.setToolTipText("Search for item(s).");
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setIcon(new ImageIcon(MainScreen.class.getResource("/resources/searchBtn.png")));
		btnSearch.setRolloverIcon(new ImageIcon(MainScreen.class.getResource("/resources/searchBtnHover.png")));
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorder(null);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSearch = new Search(db);
				panelView.add(panelSearch, "search");
				cards.show(panelView, "search");
			}
		});
		btnSearch.setFocusPainted(false);
		btnSearch.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		btnSearch.setBounds(10, 305, 200, 115);
		panelBtn.add(btnSearch);
		
		JButton btnHelp = new JButton("");
		btnHelp.setToolTipText("User guide/Troubleshooting.");
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setIcon(new ImageIcon(MainScreen.class.getResource("/resources/helpBtn.png")));
		btnHelp.setRolloverIcon(new ImageIcon(MainScreen.class.getResource("/resources/helpBtnHover.png")));
		btnHelp.setBorderPainted(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorder(null);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHelp = new Help();
				panelView.add(panelHelp, "help");
				cards.show(panelView, "help");
			}
		});
		btnHelp.setFocusPainted(false);
		btnHelp.setBounds(10, 445, 200, 115);
		panelBtn.add(btnHelp);
	}
}

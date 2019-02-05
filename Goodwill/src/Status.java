
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Status extends JPanel {

	private JLabel lblConnection;
	
	/**
	 * Create the panel.
	 */
	public Status(DatabaseController db) {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 973, 40);
		add(panel);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);

		SimpleDateFormat date = new SimpleDateFormat("EEEEEEEEEE, MMMMMMMM dd, yyyy hh:mm a");
		Date today = Calendar.getInstance().getTime();
		JLabel lblDate = new JLabel(date.format(today));
		lblDate.setBackground(new Color(255, 255, 255));
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(553, 0, 420, 40);
		lblDate.setFont(new Font("Century Gothic", Font.ITALIC, 18));
		lblDate.setForeground(new Color(0, 0, 205));
		panel.add(lblDate);
		
		new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Calendar now = Calendar.getInstance();
	            lblDate.setText(date.format(now.getTime()));
	            updateLbl(db);
	        }
	    }).start();
		
		JLabel lblTitle = new JLabel("Goodwill Donation Tracking System");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setBounds(0, 0, 420, 40);
		lblTitle.setFont(new Font("Century Gothic", Font.PLAIN, 18));
		lblTitle.setForeground(new Color(0, 0, 205));
		panel.add(lblTitle);
		
		lblConnection = new JLabel("");
		lblConnection.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnection.setForeground(new Color(0, 0, 205));
		lblConnection.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblConnection.setBounds(423, 0, 127, 40);
		panel.add(lblConnection);
		updateLbl(db);
	}
	
	private void updateLbl(DatabaseController db){
		if(db.isConnected == true){
			lblConnection.setForeground(Color.GREEN);
			lblConnection.setText("Online");
		}else{
			lblConnection.setForeground(Color.RED);
			lblConnection.setText("Offline");
		}
	}
}

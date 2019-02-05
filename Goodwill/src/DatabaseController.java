import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

/*
 * database connection handler
 * public methods: DatabaseConnection, AddItem, UpdateItem
 * IDLookup(returns string array), and KeywordQuery (returns ResultSet)
 */

public class DatabaseController {
	String userName = "root";
	//Don't forget to change to actual password before running!!!
	String password = "password";
	String url = "jdbc:mysql://localhost/sys?";
	String database = "sys";
	Connection conn;
	boolean isConnected;
	
	public void databaseConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection(url+"user="+
	                  userName+"&password=" + password+"&useSSL=false");
	         isConnected = true;
		} catch (Exception e) {
			isConnected = false;
			e.printStackTrace();
		}
	}
	
	/*
	 * AddItem method. Gets Info from GUI and sends it to the Add_Item procedure on the database.
	 * Input variables: ID, category, description, condition, location1, store1, date1, time1, employeeName1
	 */
	public void add(String ID, String category, String description, String condition, String loc1,
			String store1, String date1, String time1, String empName1){
				
		try {
			
			CallableStatement callSt = conn.prepareCall("{CALL "+database+".`Add_Item`(?, ?, ?, ?, ?, ?, ?, ?, ?)}");

			callSt.setString(1, ID);
			callSt.setString(2, category);
			callSt.setString(3, description);
			callSt.setString(4, condition);
			callSt.setString(5, loc1);
			callSt.setString(6, store1);
			callSt.setString(7, date1);
            callSt.setString(8, time1);
            callSt.setString(9, empName1);


            callSt.execute();

        }catch (SQLException e){

        	e.printStackTrace();
        }
	}
	
	/*
	 * dupeCheck method. Takes a ID string in and checks the database for duplicate strings.
	 * Returns True if the string is a duplicate, False if there is the ID is not in the database.
	 */
	public boolean dupeCheck(String ID) throws SQLException{
		ResultSet result = IDQuery(ID);
		return result.first();
	}
		
	/*
	 * UpdateItem method. Receives updated info from GUI and sends it to the Update_Item stored procedure.
	 * Input variables: ID, category, description, condition, new location, new store, new date , new time, and new employee name
	 */
	public void update(String ID, String category, String description, String condition, String loc,
			String store, String date, String time, String empName){
		
		ResultSet result = IDQuery(ID);
		
		try {
            CallableStatement callSt = conn.prepareCall("{CALL `sys`.`update_Item`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            		"?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            callSt.setString(1, ID);
            callSt.setString(2, category);
            callSt.setString(3, description);
            callSt.setString(4, condition);
            
            boolean entered = false;
            for(int i = 5; i<=29; i++){
            	result.first();
            	if(result.getString(i)!=null){
            		callSt.setString(i,result.getString(i));
            		continue;
            	}
            	if(entered == false){
            		callSt.setString(i,loc);
            		callSt.setString(i+1,store);
            		callSt.setString(i+2,date);
            		callSt.setString(i+3, time);
            		callSt.setString(i+4, empName);
            		i+=4;
            		entered = true;
            		continue;
            	}
            	callSt.setString(i, null);
            	
            }
            
            callSt.execute();



        }catch (SQLException e){

        	e.printStackTrace();
        }
		
	}
	
	/*
	 * IDLookup method. Used in Update portion of GUI.
	 * Sends ID to IDQuery method and formats and sends
	 * the ResultSet in a String array.
	 * String output: ID, category, description, condition
	 */
	public String[] IDLookup(String ID){
		ResultSet result = IDQuery(ID);
		String[] aString = new String[4];
		try {
			if (!result.first()){
				return null;
			}else{
				
				aString[0] = result.getString(1);
				aString[1] = result.getString(2);
				aString[2] = result.getString(3);
				aString[3] = result.getString(4);
				return aString;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aString;
		
	}
	
	/*
	 * IDQuery method. Sends ID string to database for search. Returns results if found.
	 * If no results found, returns null.
	 * This is a private method only used by IDLookup.
	 */
	private ResultSet IDQuery(String ID){
		ResultSet result = null;
		try {
			CallableStatement callSt = conn.prepareCall("{Call `sys`.`ID_Query`(?)}");
			
			callSt.setString(1, ID);
			
			callSt.execute();
			result = callSt.getResultSet();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * KeywordQuery method. Sends keyword string to database for search. Returns results if found.
	 * If no results found, returns null.
	 * Each row is an item.
	 * columns will be formatted like the database table.
	 */
	public String[][] search(String keyword) throws SQLException {
		ResultSet result = null;
		String[][] results = null;
		String key = "%"+keyword+"%";
		try {
			CallableStatement callSt = conn.prepareCall("{Call `sys`.`Keyword_Query`(?)}");
			
			callSt.setString(1, key);
			
			callSt.execute();
			result = callSt.getResultSet();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		result.last();
		results = new String[result.getRow()][29];
		result.beforeFirst();
		
		while(result!=null && result.next()){
			for(int j=0;j<29;j++){
				results[i][j]=result.getString(j+1);
			}
			i++;
		}
		
		return results;
	}
	
}

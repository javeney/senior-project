
public class MainController {

	public static void main(String[] args){
		DatabaseController db = new DatabaseController();
		db.databaseConnection();
		
		try{
			@SuppressWarnings("unused")
			MainScreen screen = new MainScreen(db);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//
}

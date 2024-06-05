package practice;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;

public class UseDatabaseUtility {

	public static void main(String[] args) throws SQLException {
		DatabaseUtility dblib= new DatabaseUtility();
		dblib.getdirectConnection();
		ResultSet result = dblib.excuteSelectQuery("select * from project;");
	
		while(result.next()) 
		{
		String data=result.getString(1);
		System.out.println(data);
		}
		int res = dblib.excuteNonSelectQuery(" insert into project value('trial','Aditya','15/06/2024','facebook','done','5');");

		System.out.println(res);
		dblib.closeConnection();
	}

}

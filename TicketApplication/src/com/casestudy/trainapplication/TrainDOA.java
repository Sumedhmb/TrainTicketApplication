package com.casestudy.trainapplication;
import java.sql.*;


public class TrainDOA {
	static ResultSet resultSet ;
	static final String DRIVER = "oracle.jdbc.OracleDriver" ;
	static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	static final String user = "hr";
	static final String password = "Sumedh_09";

	
	public static Train findTrain(int trainNo) throws SQLException, ClassNotFoundException
	{
		
		Class.forName(DRIVER);
		//Class.forName("oracle.jdbc.OracleDriver");//loading driver
		//String url = "jdbc:oracle:thin@127.0.0.1:1521:XE";
		//String user = "hr";
		//String password = "Sumedh_09";
		
		//Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "hr", "Sumedh_09");//Connection
		
		Connection connection = DriverManager.getConnection(URL, user, password);
		
		Statement statement = connection.createStatement();//creating a statement
		
		resultSet = statement.executeQuery("select * from trains");
		
		boolean flag = false ;
		while(resultSet.next())
		{
			if(resultSet.getInt(1)==trainNo)
				{
				flag = true ;
				break ;
				}
		}
		if(flag)
		{
			Train t = new Train(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDouble(5));
			return t ;
		}
		else
			return null ;
	}
	
}

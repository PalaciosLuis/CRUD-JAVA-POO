package db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
		public static Connection getConexion() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
				String url = "jdbc:mysql://localhost:3306/ArepaJuegos?allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
				String usr = "root";
				String pwd = "MySQL123$";
				try {
					con = DriverManager.getConnection(url, usr, pwd);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return con;
		}
	

}

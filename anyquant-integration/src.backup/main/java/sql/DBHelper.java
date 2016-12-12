package sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

	private static final String url = "jdbc:mysql://localhost:3306/mysql?"
			+ "user=root&password=VAIOcorei5&useUnicode=true&characterEncoding=UTF8";
	private static final String name = "com.mysql.jdbc.Driver";

	public Connection conn = null;
	private static DBHelper dbHelper = null;

	private DBHelper() {
		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url);// 获取连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DBHelper getInstance() {
		if (dbHelper == null) {
			dbHelper = new DBHelper();
		}
		return dbHelper;
	}

}

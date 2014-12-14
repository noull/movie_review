package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import main.BeanUtil;

public class JoinDao_OracleImpl implements JoinDao {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
	}

	public void add(JoinVo user_Join) throws Exception {

		Connection conn = null;
		Statement stmt = null;
		String join_sql = "Insert into User_List values(" + "0 ,'"
				+ BeanUtil.us2kr(user_Join.getNickPK()) + "','" + BeanUtil.us2kr(user_Join.getPassword())
				+ "','" + BeanUtil.us2kr(user_Join.getE_mail()) + "', 0" + ",'"
				+ BeanUtil.us2kr(user_Join.getFavorite_Cate1()) + "','"
				+ BeanUtil.us2kr(user_Join.getFavorite_Cate2()) + "','"
				+ BeanUtil.us2kr(user_Join.getFavorite_Cate3()) + "','"
				+ BeanUtil.us2kr(user_Join.getFavorite_Cate4()) + "','" + user_Join.getSpare1()
				+ "','" + user_Join.getSpare2() + "','" + user_Join.getSpare3()
				+ "')";
		System.out.println(join_sql);
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.createStatement();
			stmt.executeUpdate(join_sql);
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.BeanUtil;

public class Bang01Dao_OracleImpl implements Bang01Dao {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
	}

	public List<Bang01Vo> findAll() throws Exception {
		List<Bang01Vo> lst = new ArrayList<Bang01Vo>();
		
		System.out.println("Áß°£1");

		// String sql1 =
		// "SELECT categories, textPK, title, content, nickPK, TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime, readCount, recomCount, fileUp, fileDown FROM Board_List ORDER By no DESC";
		String sql1 = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
				+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime"
				+ "  FROM Board_List ORDER By textPK DESC";
		System.out.println(sql1);
		
		Connection conn = null;
		PreparedStatement stmt1 = null;
		ResultSet rs1 = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt1 = conn.prepareStatement(sql1);
			rs1 = stmt1.executeQuery();

			while (rs1.next()) {

				Bang01Vo vo = new Bang01Vo();

				vo.setCategories(rs1.getInt("categories"));
				vo.setTextPK(rs1.getInt("textPK"));
				vo.setTitle(rs1.getString("title"));
				vo.setContent(rs1.getString("content"));
				vo.setNickPK(rs1.getString("nickPK"));
				vo.setReadCount(rs1.getInt("ReadCount"));
				vo.setRecomCount(rs1.getInt("recomCount"));
				vo.setFileUp(rs1.getString("fileUp"));
				vo.setFileDown(rs1.getString("fileDown"));
				vo.setTheTime(rs1.getString("theTime"));

				lst.add(vo);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs1 != null) {
				rs1.close();
			}
			if (stmt1 != null) {
				stmt1.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return lst;
	}

	public void add(Bang01Vo prm) throws Exception {

//		String sql = "INSERT INTO Bang01T VALUES( SEQ_BANG01.NEXTVAL ,'"
//				+ BeanUtil.us2kr(prm.getText()) + "' ,SYSDATE,NULL,NULL,NULL)";
//		System.out.println(sql);

		String add_Content = "INSERT INTO Board_List(categories, textPK, title, content, nickPK,  "
				+ "thetime, ReadCount, recomCount, fileUp , fileDown ) VALUES("
				+ prm.getCategories() + ", test_read.NEXTVAL, '"
				+ prm.getTitle() + "','" + prm.getContent() + "','"
				+ prm.getNickPK()+ "', SYSDATE, 0, 0,'" + prm.getFileUp()
				+ "','" + prm.getFileDown() 
				+  "')";
		System.out.println(add_Content);
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.createStatement();
			stmt.executeUpdate(add_Content);
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

	public List<Bang01Vo> search(String search_Word, int Switch)
			throws Exception {

		List<Bang01Vo> lst = new ArrayList<Bang01Vo>();
		String search_sql = null;

		if (Switch == 1)
			search_sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
				+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List WHERE nickPk LIKE '%"
					+ search_Word + "%'";
		else if (Switch == 2)
			search_sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
					+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List WHERE title LIKE '%"
					+ search_Word + "%'";
		else if (Switch == 3)
			search_sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
					+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List WHERE content LIKE '%"
					+ search_Word + "%'";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs1 = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.prepareStatement(search_sql);
			rs1 = stmt.executeQuery();
			while (rs1.next()) {

				Bang01Vo vo = new Bang01Vo();

				vo.setCategories(rs1.getInt("categories"));
				vo.setTextPK(rs1.getInt("textPK"));
				vo.setTitle(BeanUtil.us2kr(rs1.getString("title")));
				vo.setContent(BeanUtil.us2kr(rs1.getString("content")));
				vo.setNickPK(BeanUtil.us2kr(rs1.getString("nickPK")));
				vo.setReadCount(rs1.getInt("ReadCount"));
				vo.setRecomCount(rs1.getInt("recomCount"));
				vo.setFileUp(BeanUtil.us2kr(rs1.getString("fileUp")));
				vo.setFileDown(BeanUtil.us2kr(rs1.getString("fileDown")));
				vo.setTheTime(BeanUtil.us2kr(rs1.getString("theTime")));

				lst.add(vo);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs1 != null) {
				rs1.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return lst;
	}


	public Bang01Vo modify(int textPk) throws Exception {
		String modify_sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
				+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List Board_List where textPk=" + textPk;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Bang01Vo vo = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.prepareStatement(modify_sql);
			rs = stmt.executeQuery();

			if (rs.next()) {

				vo = new Bang01Vo();

				vo.setCategories(rs.getInt("categories"));
				vo.setTextPK(rs.getInt("textPK"));
				vo.setTitle(BeanUtil.us2kr(rs.getString("title")));
				vo.setContent(BeanUtil.us2kr(rs.getString("content")));
				vo.setNickPK(BeanUtil.us2kr(rs.getString("nickPK")));
				vo.setReadCount(rs.getInt("ReadCount"));
				vo.setRecomCount(rs.getInt("recomCount"));
				vo.setFileUp(BeanUtil.us2kr(rs.getString("fileUp")));
				vo.setFileDown(BeanUtil.us2kr(rs.getString("fileDown")));
				vo.setTheTime(BeanUtil.us2kr(rs.getString("theTime")));
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return vo;
	}

	public void del(Bang01Vo prm) throws Exception {

		System.out.println("del!!!");
		String sql = "DELETE FROM Board_List WHERE textPK =" + prm.getTextPK();
		System.out.println(sql);
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
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


	public void update(Bang01Vo prm) throws Exception {

		String sql = "UPDATE Board_List SET title = '" + BeanUtil.us2kr(prm.getTitle())
				+ "',Content ='" + BeanUtil.us2kr(prm.getContent()) + "', fileUp ='"
				+ (prm.getFileUp()) + "', fileDown ='" + (prm.getFileDown())
				+ "' WHERE textPK =" + prm.getTextPK();
		System.out.println(prm.getTextPK());
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
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

	public boolean readContent(String read_Id, int read_textPk, String nick_Id)
			throws Exception {
		System.out.println("aaaa");
		read_Id = read_Id.trim();
		nick_Id = nick_Id.trim();
		
		
		String read_Sql = "SELECT COUNT(*) AS COUNT FROM Board_Read WHERE textPk = "
				+ read_textPk + " AND nickPk = '" + read_Id + "'";
		System.out.println(read_Sql);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		int read_check = 1;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15","ora15");

			stmt = conn.prepareStatement(read_Sql);
			rs = stmt.executeQuery();

			if (rs.next())
				read_check = rs.getInt("COUNT");

			if (read_Id.equals(nick_Id) || read_check != 0) {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

				return false;
			} else {
				String boredUp_Sql = "UPDATE Board_List SET readCount = readCount + 1 WHERE textPK = "
						+ read_textPk;
				String readUp_Sql = "INSERT INTO Board_Read values('" + read_Id
						+ "'," + read_textPk + ")";
				stmt = conn.prepareStatement(boredUp_Sql);
				stmt.executeUpdate();

				stmt = conn.prepareStatement(readUp_Sql);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return true;

	}

	public boolean recomContent(String recom_Id, int text_Pk, String nick_Id)
			throws Exception {

		recom_Id = recom_Id.trim();
		nick_Id = nick_Id.trim();
		
		String recom_Sql = "SELECT COUNT(*) AS COUNT FROM board_Recom WHERE textPk = "
				+ text_Pk + " AND nickPk = '" + recom_Id + "'";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int recom_check = 0;
		try {
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");

			stmt = conn.prepareStatement(recom_Sql);
			rs = stmt.executeQuery();
			if (rs.next())
				recom_check = rs.getInt("COUNT");

			if (recom_Id.equals(nick_Id) || recom_check != 0) {

				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

				return false;
			} else {// update ï¿½ï¿½ï¿½ï¿½

				System.out.println("else");
				String boredUp_Sql = "UPDATE Board_List SET recomCount = recomCount + 1 WHERE textPK = "
						+ text_Pk;
				String recomUp_Sql = "INSERT INTO Board_Recom values('"
						+ recom_Id + "'," + text_Pk
						+ ", Seq_repl.NEXTVAL)";

				stmt = conn.prepareStatement(boredUp_Sql);
				stmt.executeUpdate();

				stmt = conn.prepareStatement(recomUp_Sql);
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return true;
	}


	public LoginVo login(String id) throws Exception {

		String sql = "select nickPK,user_Point,password from user_list where nickPK='"
				+ id + "'";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LoginVo log = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				log = new LoginVo();
				log.setNickPK((rs.getString("nickPK")));
				log.setUser_Point(rs.getInt("user_Point"));
				log.setPassword((rs.getString("password")));

			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return log;
	}


	public List<Bang01Vo> show(String search_Word) throws Exception {

		List<Bang01Vo> lst = new ArrayList<Bang01Vo>();
		String sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
				+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List WHERE textPK =" + search_Word;
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "ora15",
					"ora15");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {

				Bang01Vo vo = new Bang01Vo();
				vo.setCategories(rs.getInt("categories"));
				vo.setTextPK(rs.getInt("textPK"));
				vo.setTitle((rs.getString("title")).trim());
				vo.setContent((rs.getString("content")).trim());
				vo.setNickPK((rs.getString("nickPK")).trim());
				vo.setReadCount(rs.getInt("ReadCount"));
				vo.setRecomCount(rs.getInt("recomCount"));
				vo.setFileUp((rs.getString("fileUp")).trim());
				vo.setFileDown((rs.getString("fileDown")).trim());
				vo.setTheTime(rs.getString("theTime").trim());
				lst.add(vo);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return lst;
	}
	
	

	public Bang01Vo detail_view(String search_Word) throws Exception {
		String sql = "SELECT categories, textPK, title, content, nickPK,  ReadCount, recomCount, fileUp , fileDown, "
				+ "TO_CHAR(theTime, 'YYYY-MM-DD HH24:MI:SS')AS theTime FROM Board_List WHERE textPK ="+search_Word;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Bang01Vo vo=null;
		
		try{
	
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while( rs.next() ){
				 vo = new Bang01Vo();
				vo.setCategories(rs.getInt("categories"));
				vo.setTextPK(rs.getInt("textPK"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setNickPK(rs.getString("nickPK"));
				vo.setReadCount(rs.getInt("ReadCount"));
				vo.setRecomCount(rs.getInt("recomCount"));
				vo.setFileUp(rs.getString("fileUp"));
				vo.setFileDown(rs.getString("fileDown"));
	
			}
			
		}catch( Exception e){ throw e; }
		finally{
			if( rs != null ){ rs.close(); }
			if( stmt != null ){ stmt.close(); }
			if( conn != null ){ conn.close(); }
		}
		
		return vo;
	}
	

	public void re_add(Reple_List re) throws Exception {
		
		String sql = "insert into repl_List values("+re.getTextPK()+",Seq_repl.nextval,'"+re.getNickPK()+"','"+BeanUtil.us2kr(re.getRepl_content())+"',0,null,null)";
		Connection conn = null;
		Statement stmt = null;
		
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
		}
		catch( Exception e ){ throw e; }
		finally{
			if( stmt != null ){ stmt.close(); }
			if( conn != null ){ conn.close(); }
		}
		
		
	}



	public List<Reple_List> re_findAll(String search_Word) throws Exception {
		
		String sql = "select nickPK, repl_content, repl_recomCount, repl_PK from repl_List WHERE textPK ="+search_Word;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		List<Reple_List> re_list=new ArrayList<Reple_List>();
		System.out.println(sql);
		
		
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			
			while( rs.next() ){
				
				
				Reple_List re=new Reple_List();
				re.setNickPK(rs.getString("nickPK"));
				re.setRepl_content(rs.getString("repl_content"));
				re.setRepl_recomCount(rs.getInt("repl_recomCount"));
				re.setRepl_PK(rs.getInt("repl_PK"));
				re_list.add(re);
			}
			
			
			
		}
		catch( Exception e ){ throw e; }
		finally{
			if(rs!=null){rs.close();}
			if( stmt != null ){ stmt.close(); }
			if( conn != null ){ conn.close(); }
		}
		
		return re_list;
	}



	public void re_delete(String repl_pk) throws Exception {
		String sql = "delete from repl_List where repl_pk="+repl_pk;
		
		Connection conn = null;
		Statement stmt = null;
		
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			
		}
		catch( Exception e ){ throw e; }
		finally{
			if( stmt != null ){ stmt.close(); }
			if( conn != null ){ conn.close(); }
		}
		
	}



	public void re_update(Reple_List re) throws Exception {
		String sql = "UPDATE repl_List SET repl_content='"+BeanUtil.us2kr(re.getRepl_content())+"' where repl_pk="+re.getRepl_PK();
		
		Connection conn = null;
		Statement stmt = null;
		System.out.println(sql);
		
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ora15", "ora15");
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
			
		}
		catch( Exception e ){ throw e; }
		finally{
			if( stmt != null ){ stmt.close(); }
			if( conn != null ){ conn.close(); }
		}
		
	}
}

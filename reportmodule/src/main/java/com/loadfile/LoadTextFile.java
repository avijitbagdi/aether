package com.loadfile;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LoadTextFile {

	public static Connection getConnection() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/aetherreport";
		String username = "aethermysql";
		String password = "Mysql";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void main(String[] args) throws Exception {
		Integer id = 1;
		String fileName = "E:/producttestfile/test.txt";

		FileInputStream fis = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			File file = new File(fileName);
			fis = new FileInputStream(file);
			pstmt = conn.prepareStatement("insert into sqlquery(sqlid, sqldescription, query) values (?, ?, ?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, fileName);
			pstmt.setAsciiStream(3, fis, (int) file.length());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			pstmt.close();
			fis.close();
			conn.close();
		}
	}
}

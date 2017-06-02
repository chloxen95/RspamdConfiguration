package com.chloxen95.RspamdConfiguration.SymbolAndSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReadSymbolFromSQL {

	@BeforeClass
	public static void LoadJDBCDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws SQLException {
		List<List<String>> result = new ArrayList<>();
		ResultSet rs = getResultSet("select * from symbol");
		while (rs.next()) {
			int i = 0;
			List<String> symbol = new ArrayList<>();
			while (++i <= 6) {
				symbol.add(rs.getString(i));
			}
			result.add(symbol);
		}
		System.out.println(result.size());
	}

	public ResultSet getResultSet(String sql) {
		Connection conn;
		Statement stmt;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rspamd?characterEncoding=utf8&useSSL=true",
					"root", "root");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.err.println("Something wrong");
		}
		return rs;
	}

}

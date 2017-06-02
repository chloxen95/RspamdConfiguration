package com.chloxen95.RspamdConfiguration.SymbolAndSQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class AddSymbolToDB {

	@BeforeClass
	public static void LoadJDBCDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void RunInsert(List<List<String>> data) throws SQLException {
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/rspamd?characterEncoding=utf8&useSSL=true", "root", "root");
		PreparedStatement ps = conn.prepareStatement("INSERT into symbol values (?, ?, ?, ?, ?, ?, ?)");
		for (List<String> oneSymbol : data) {
			int i = 0;
			for (String s : oneSymbol) {
				ps.setString(++i, s);
			}
			ps.addBatch();
			i = 0;
		}
		ps.executeBatch();
	}

	@Test
	public void test() throws IOException, SQLException {
		ParseJsonToMap pjm = new ParseJsonToMap();
		pjm.ReadFile();
		RunInsert(pjm.result);
	}

}

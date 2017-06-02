package com.chloxen95.RspamdConfiguration.Service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.chloxen95.RspamdConfiguration.Service.SymbolReader;
import com.chloxen95.RspamdConfiguration.Utils.DatabaseUtils;

public class SymbolReaderImpl implements SymbolReader {

	@Override
	public List<List<String>> getSymbol() throws SQLException {
		String sql = "select * from symbol";
		ResultSet rs = DatabaseUtils.getResultSet(sql);
		List<List<String>> result = new ArrayList<>();
		while (rs.next()) {
			int i = 0;
			List<String> symbol = new ArrayList<>();
			while (++i <= 7) {
				symbol.add(rs.getString(i));
			}
			result.add(symbol);
		}
		rs.close();
		return result;
	}
	
	// @Test
	public void test() throws SQLException{
		getSymbol();
	}

}

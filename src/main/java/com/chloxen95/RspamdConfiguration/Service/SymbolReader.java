package com.chloxen95.RspamdConfiguration.Service;

import java.sql.SQLException;
import java.util.List;

public interface SymbolReader {

	public List<List<String>> getSymbol() throws SQLException;

}

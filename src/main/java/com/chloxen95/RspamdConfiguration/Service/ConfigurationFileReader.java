package com.chloxen95.RspamdConfiguration.Service;

import java.io.IOException;
import java.util.List;

public interface ConfigurationFileReader {
	
	public List<String> getConfigurationFileLineList(String path, Boolean comment, Boolean blankLine) throws IOException; 
	
	public String getConfigurationFileOneLine(String path) throws IOException;

}

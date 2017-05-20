package com.chloxen95.RspamdConfiguration.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ConfigurationFileReader {
	
	public List<String> getConfigurationFile(String path, Boolean comment, Boolean blankLine) throws FileNotFoundException, IOException; 
	

}

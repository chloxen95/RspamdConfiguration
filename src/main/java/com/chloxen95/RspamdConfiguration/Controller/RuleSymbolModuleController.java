package com.chloxen95.RspamdConfiguration.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileList;
import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileReader;
import com.chloxen95.RspamdConfiguration.Service.ConfigurationParser;
import com.chloxen95.RspamdConfiguration.Service.SymbolReader;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfParserImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfigurationFileListImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfigurationFileReaderImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.SymbolReaderImpl;

@Controller
public class RuleSymbolModuleController {
	
	String pathPrefix = "D:/Java Project from STS/RspamdConfiguration/src/test/resources/";

	ConfigurationParser cp;
	ConfigurationFileReader cfr;
	ConfigurationFileList cfl;
	SymbolReader sr;

	@RequestMapping(value = "/rule", method = RequestMethod.GET)
	public String JumptoRulePage(HttpServletRequest request) {
		return "rules/rule";
	}
	
	@RequestMapping(value = "/symbol", method = RequestMethod.GET)
	public String JumptoSymbolPage(HttpServletRequest request) {
		return "rules/symbol";
	}
	
	@RequestMapping(value = "/module", method = RequestMethod.GET)
	public String JumptoModulePage(HttpServletRequest request) {
		return "rules/module";
	}
	
	@RequestMapping(value = "/rules/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetConfigurationFileList(String type, String postfix) throws IOException {
		cfl = new ConfigurationFileListImpl();
		Map<String, Object> result = new IdentityHashMap<>();
		cfl.getConfigurationFileList(pathPrefix + type + "/", postfix, result);
		
		return result;
	}

	@RequestMapping(value = "/rules/parse", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetParsedConfigurationFileContextByMap(String path) throws IOException {
		if (path.endsWith(".lua")) {
			cp = new ConfParserImpl();
			return cp.getFileContent(pathPrefix + "rule/" + path);
		} else {
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Invalid file type");
			return result;
		}

	}

	@RequestMapping(value = "/rules/context", method = RequestMethod.POST)
	@ResponseBody
	public List<String> GetEntireConfigurationFileContextByList(String fileName, Boolean comment, Boolean blankLine)
			throws FileNotFoundException, IOException {
		cfr = new ConfigurationFileReaderImpl();
		if(fileName.endsWith("lua"))
			return cfr.getConfigurationFileLineList(pathPrefix + "rule/" + fileName, comment, blankLine);
		else
			return null;

	}
	
	@RequestMapping(value = "/rules/symbol/json", method = RequestMethod.GET)
	@ResponseBody
	public String GetSymbolByJsonOneLineFile() throws IOException{
		cfr = new ConfigurationFileReaderImpl();
		return cfr.getConfigurationFileOneLine(pathPrefix + "symbol/symbol.min.json");
	}
	
	@RequestMapping(value = "/rules/symbol/db", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> GetSymbolBySQL() throws SQLException{
		Map<String, Object> result = new HashMap<>();
		sr = new SymbolReaderImpl();
		result.put("symbol", sr.getSymbol());
		return result;
	}

}

package com.chloxen95.RspamdConfiguration.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileReader;
import com.chloxen95.RspamdConfiguration.Service.ConfigurationParser;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfParserImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfigurationFileReaderImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.IncParserImpl;

@Controller
@RequestMapping
public class ConfigurationReaderController {

	String pathPrefix = "D:/Java Project from STS/RspamdConfiguration/";

	ConfigurationParser cp;
	ConfigurationFileReader cfr;

	@RequestMapping(value = "/conf")
	public String JumptoConfPage() {

		return "configuration/conf_editor";
	}
	
	@RequestMapping(value = "/inc")
	public String JumptoIncPage() {

		return "configuration/inc_editor";
	}
	
	@RequestMapping(value = "/file/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetConfigurationFileList(String path) throws IOException {
		if (path.endsWith(".inc")) {
			cp = new IncParserImpl();
			return cp.getFileContent(pathPrefix + path);
		} else if (path.endsWith(".conf")) {
			cp = new ConfParserImpl();
			return cp.getFileContent(pathPrefix + path);
		} else {
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Invalid file type");
			return result;
		}

	}

	@RequestMapping(value = "/file/parse", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetParsedConfigurationFileContextByMap(String path) throws IOException {
		if (path.endsWith(".inc")) {
			cp = new IncParserImpl();
			return cp.getFileContent(pathPrefix + path);
		} else if (path.endsWith(".conf")) {
			cp = new ConfParserImpl();
			return cp.getFileContent(pathPrefix + path);
		} else {
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Invalid file type");
			return result;
		}

	}

	@RequestMapping(value = "/file/entire", method = RequestMethod.POST)
	@ResponseBody
	public List<String> GetEntireConfigurationFileContextByList(String path, Boolean comment, Boolean blankLine)
			throws FileNotFoundException, IOException {
		cfr = new ConfigurationFileReaderImpl();
		return cfr.getConfigurationFile(pathPrefix + path, comment, blankLine);

	}

}

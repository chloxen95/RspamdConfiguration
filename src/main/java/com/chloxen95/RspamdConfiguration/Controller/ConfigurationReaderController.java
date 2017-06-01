package com.chloxen95.RspamdConfiguration.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileList;
import com.chloxen95.RspamdConfiguration.Service.ConfigurationFileReader;
import com.chloxen95.RspamdConfiguration.Service.ConfigurationParser;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfParserImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfigurationFileListImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.ConfigurationFileReaderImpl;
import com.chloxen95.RspamdConfiguration.Service.impl.IncParserImpl;

@Controller
@RequestMapping
public class ConfigurationReaderController {

	String pathPrefix = "D:/Java Project from STS/RspamdConfiguration/src/test/resources/";

	ConfigurationParser cp;
	ConfigurationFileReader cfr;
	ConfigurationFileList cfl;

	@RequestMapping(value = "/conf")
	public String JumptoConfPage() {

		return "configuration/conf_editor";
	}
	
	@RequestMapping(value = "/inc")
	public String JumptoIncPage() {

		return "configuration/inc_editor";
	}
	
	@RequestMapping(value = "/configuration/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetConfigurationFileList(String path, String postfix) throws IOException {
		cfl = new ConfigurationFileListImpl();
		Map<String, Object> result = new IdentityHashMap<>();
		cfl.getConfigurationFileList(pathPrefix + postfix + "/" + path, postfix, result);
		
		return result;
	}

	@RequestMapping(value = "/configuration/parse", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> GetParsedConfigurationFileContextByMap(String path) throws IOException {
		if (path.endsWith(".inc")) {
			cp = new IncParserImpl();
			return cp.getFileContent(pathPrefix + "inc/" + path);
		} else if (path.endsWith(".conf")) {
			cp = new ConfParserImpl();
			return cp.getFileContent(pathPrefix + "conf/" + path);
		} else if (path.endsWith(".lua")) {
			cp = new ConfParserImpl();
			return cp.getFileContent(pathPrefix + "rule/" + path);
		} else {
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Invalid file type");
			return result;
		}

	}

	@RequestMapping(value = "/configuration/context", method = RequestMethod.POST)
	@ResponseBody
	public List<String> GetEntireConfigurationFileContextByList(String fileName, Boolean comment, Boolean blankLine)
			throws FileNotFoundException, IOException {
		cfr = new ConfigurationFileReaderImpl();
		if(fileName.endsWith("conf"))
			return cfr.getConfigurationFileLineList(pathPrefix + "conf/" + fileName, comment, blankLine);
		else if(fileName.endsWith("inc"))
			return cfr.getConfigurationFileLineList(pathPrefix + "inc/" + fileName, comment, blankLine);
		else if(fileName.endsWith("lua"))
			return cfr.getConfigurationFileLineList(pathPrefix + "rule/" + fileName, comment, blankLine);
		else
			return null;

	}

}

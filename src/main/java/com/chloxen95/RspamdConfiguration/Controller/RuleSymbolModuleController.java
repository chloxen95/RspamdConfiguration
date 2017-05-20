package com.chloxen95.RspamdConfiguration.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RuleSymbolModuleController {

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

}

package com.chloxen95.RspamdConfiguration.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemStatusController {

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public String JumpToHistoryPage(HttpServletRequest request){
		return "status/history";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public String JumpToCurrentPage(HttpServletRequest request){
		return "status/current";
	}
	
	
	
}

package hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ProfileContr {

	@Autowired
	private Environment env;

	@RequestMapping("/testProfile")
	@ResponseBody
	public String testProfile() {
		return env.getProperty("profile");
	}

}

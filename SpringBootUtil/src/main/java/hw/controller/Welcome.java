package hw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class Welcome {
//	profile文件中的配置
	@Value("${welcomeMsg}")
	private String welString;
	@Value("${version}")
	private String version;
	@Value("${date}")
	private String date;
	
//	公共的配置，在application.properties文件中配置
	@Value("${spring.beetl.root}")
	private String templatePath;
	@Value("${spring.beetl.prefix}")
	private String prefix;
	@Value("${spring.beetl.suffix}")
	private String suffix;
	@Value("${spring.beetl.order}")
	private int order;
	@Value("${spring.beetl.cofig}")
	private String properties;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "profile属性:"+
				welString+"	"+
				version+"	"+
				date+"	"+
				"公共属性:"+
				templatePath+"	"+
				prefix+"	"+
				suffix+"	"+
				properties+"	"+
				String.valueOf(order);
	}
	
	/*@RequestMapping("/error")
	public ModelAndView error()
	{
		ModelAndView mv=new ModelAndView("error");
		
		return mv;
		
	}*/

}

package hw.controller;

import javax.servlet.http.HttpServletResponse;

import hw.conf.MyWebConf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conf")
public class Config {
	
	private static Logger logger=LoggerFactory.getLogger(Config.class);
	
	@Autowired
	private MyWebConf webConf;
	
	@RequestMapping("/author")
	public String webConf(HttpServletResponse response) {
		
		String info= webConf.getAuthorName()+"	"+
			   webConf.getAuthorAge()+"	"+
			   webConf.getAuthorSex()+"	"+
			   webConf.getAuthorPhone()+"	"+
			   webConf.getDescription();
		
		logger.info(info);
		return info;
		
	}
	
}

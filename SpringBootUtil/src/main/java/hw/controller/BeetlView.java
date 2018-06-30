package hw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("beetl")
public class BeetlView {
	private static Logger logger=LoggerFactory.getLogger(BeetlView.class);
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		
		ModelAndView mv=new ModelAndView("hello");
		mv.addObject("user","何伟");
		
		logger.info("user:何伟");
		
		return mv;
		
	}

}

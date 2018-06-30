package hw.controller;

import hw.model.Message;
import hw.service.interfaces.IMessageServ;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/message")
@Slf4j
public class MessageContr {
	@Autowired 
	private IMessageServ messageServ; 
	
	@RequestMapping("/all")
	public ModelAndView message(Model model){ 
		model.addAttribute("messages", messageServ.allMessages()); 
		ModelAndView mv=new ModelAndView("message");
		return mv; 
	}
	
	@RequestMapping("/listPage")
	public ModelAndView listPage(@RequestParam String name,Integer start,Integer len,Model model){
		model.addAttribute("messages", messageServ.listPage(name,start,len)); 
		ModelAndView mv=new ModelAndView("message");
		return mv; 
	}
	@RequestMapping("/getMessageById")
	public ModelAndView listPage(@RequestParam("id") Integer id){
		ModelAndView mv=new ModelAndView("message");
		List<Message> messages=messageServ.getMessageById(id);
		log.info(messages.toString());
		mv.addObject("messages",messages );
		return mv; 
	}

}

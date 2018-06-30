package hw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hw.dao.beetl.UserDaoMapper;
import hw.model.User;
import hw.service.UserMapperServImpl;
import hw.service.interfaces.IUserMapperServ;

@RestController
//@Controller
@RequestMapping("user")
public class UserMapperDaoContr {
	
	private static Logger log=LoggerFactory.getLogger(UserMapperDaoContr.class);
//	@Autowired()
//	private UserMapperServImpl userMapperServImpl;
	
	@Autowired
	private IUserMapperServ userMapperServ;
	
	@RequestMapping("/count")
	public String getUserCount() {
		int count=userMapperServ.total();
		log.info("用户人数："+String.valueOf(count));
		return String.valueOf(count);
	}
	@RequestMapping("/getUser")
	public String getUser(String name) {
		List<User> users=userMapperServ.getUsersByName(name);
		log.info("用户："+users.toString());
		return users.toString();
	}
	@RequestMapping("/allUsers")
	public ModelAndView getAllUsers(Model m) {
		List<User> users=userMapperServ.allUsers();
		m.addAttribute("users",users);
		ModelAndView mv=new ModelAndView("user");
//		mv.addObject("users",users);
		for(User user:users) {
			log.info(user.toString());
		}
		return mv;
	}
	@RequestMapping("/updateUser")
	public Boolean updateUser(int id,String name) {
		return userMapperServ.updateUser(id, name);
	}
	@RequestMapping("/addUser")
	public Boolean addUser(User user) {
		return userMapperServ.addUser(user);
	}
	@RequestMapping("/getAgeById")
	public Integer getAgeById(Integer id) {
		return userMapperServ.getAgeById(id);
	}
	@RequestMapping("/getUsersByNA")
	public List<User> getUsersByNA(String name,Integer age) {
		List<User> users =userMapperServ.getUsersByNA(name, age);
		for(User user : users) {
			log.info(user.getName()+" ");
		}
		return users;
	}
	
	

}

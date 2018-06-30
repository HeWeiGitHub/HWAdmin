package hw.service.interfaces;

import hw.model.User;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface IUserMapperServ {

	public int total();

	public List<User> getUsersByName(String name);

	public List<User> allUsers();

	public Boolean updateUser(int id, String name);

	public Boolean addUser(User user);
	
	public Boolean removeUser(User user);

	public Integer getAgeById(Integer id);
	
	public User getUserById(Integer id);

	public List<User> getUsersByNA(String name, Integer age);
	
	

}

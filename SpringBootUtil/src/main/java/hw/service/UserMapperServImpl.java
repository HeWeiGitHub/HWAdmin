package hw.service;

import hw.dao.beetl.UserDaoMapper;
import hw.model.User;
import hw.service.interfaces.IUserMapperServ;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//@CacheConfig(cacheNames = "UserCache")
//@CacheConfig：主要用于配置该类中会用到的一些共用的缓存配置。在这里@CacheConfig(cacheNames = "users")：配置了该数据访问
//对象中返回的内容将存储于名为users的缓存对象中，我们也可以不使用该注解，直接通过@Cacheable自己配置缓存集的名字来定义。

//@Service注解放在实现类上，放在接口出错,但是可以注入接口，会自动调用实现类
@Service
public class UserMapperServImpl implements IUserMapperServ {

	// 这里的单引号不能少，否则会报错，被识别是一个对象;
//	public static final String CACHE_NAME = "'UserCache'";
	public static final String CACHE_NAME = "UserCache";
	public static final String CACHE_KEY = "'cacheKey'";

	@Autowired
	private UserDaoMapper userDaoMapper;

	@Autowired
	private SQLManager sqlManager;

	@Cacheable(value = CACHE_NAME, key = "#id")
	public Integer getAgeById(Integer id) {
		// try {
		// return userDaoMapper.getAgeById(id);
		// } catch (Exception e) {
		// return 0;
		// }
		Integer age = userDaoMapper.getAgeById(id);
		if (age != null) {
			return age;
		} else {
			return 0;
		}
	}

	// 清除缓存
	// @CacheEvict(value=CACHE_NAME,key="#user.id")
	// @CacheEvict(value=CACHE_NAME)
	@CachePut(value = CACHE_NAME, key = "#user.id")
	public Boolean addUser(User user) {
		try {
			userDaoMapper.insert(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@CacheEvict(value = CACHE_NAME, key = "#user.id")
	public Boolean removeUser(User user) {
		try {
			userDaoMapper.deleteById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@CachePut(value = CACHE_NAME, key = "'userCount'")
	public int total() {
		return userDaoMapper.total();
	}

	@Cacheable(value = CACHE_NAME, key = "#name")
	public List<User> getUsersByName(String name) {

		return userDaoMapper.getUsersByName(name);
	}

	@Cacheable(value = CACHE_NAME, key = "#id")
	public User getUserById(Integer id) {
		return userDaoMapper.getUserById(id);
	}

	// 添加缓存
	@Cacheable(value = CACHE_NAME, key = "'users'")
	public List<User> allUsers() {
		return userDaoMapper.allUsers();
	}

	@CacheEvict(value = CACHE_NAME, key = "#id")
	public Boolean updateUser(int id, String name) {
		try {
			userDaoMapper.updateUser(id, name);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 用SQLManager来实现
	@Cacheable(value = CACHE_NAME, key = "#user.id")
	public List<User> getUsersByNA(String name, Integer age) {
		List<User> list = sqlManager.execute(new SQLReady(
				"select * from user where name=? and age = ?", name, age),
				User.class);
		return list;
	}

}

package hw.service;

import hw.dao.myBatis.MessageMapper;
import hw.model.Message;
import hw.service.interfaces.IMessageServ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
@CacheConfig(cacheNames="MessageCache")
@Service
public class MessageServImpl implements IMessageServ {
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private PageHelper pageHelper;
	
	public static final String CACHE_NAME = "'MessageCache'";
	public static final String CACHE_KEY = "cacheKey";
	
//	@CachePut(value=CACHE_NAME,key="allMessages")
	@CachePut(key="'allMessages'")
	public List<Message> allMessages() {
		return messageMapper.findMessageInfo();
	}
	
	@Cacheable(key="#id")
	public List<Message> listPage(String name,Integer start,Integer len){
		pageHelper.startPage(start,len);
	 return messageMapper.listPage(name);
	}

	@Override
	public List<Message> getMessageById(Integer id) {
		return messageMapper.getMessageById(id);
	}

	
}

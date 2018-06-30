package hw.service.interfaces;

import hw.model.Message;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

public interface IMessageServ {
	
	public List<Message> allMessages();
	
	public List<Message> listPage(String name,Integer start,Integer len);
	
	public List<Message> getMessageById(Integer id);

}

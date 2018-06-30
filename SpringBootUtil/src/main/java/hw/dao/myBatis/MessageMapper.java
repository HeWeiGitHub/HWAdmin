package hw.dao.myBatis;

import java.util.List;

import hw.model.Message;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface MessageMapper {
	public List<Message> findMessageInfo();

	public List<Message> listPage(String name);
	
	@Select("select * from message where id=#{id}")
	public List<Message> getMessageById(Integer id);
}

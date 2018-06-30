package hw.dao.beetl;

import hw.model.User;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatement;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

//@Repository("userDaoMapper")
//@Repository
/**
 * @author hw
 * @date 2017年8月11日 
 * @description 
 * 		beetlSql的Mapper类
 */
public interface UserDaoMapper extends BaseMapper<User>{

	public int total();
	@SqlStatement(params="name")
	public List<User> getUsersByName(String name);
	public List<User> allUsers();
	@SqlStatement(params="id,name")
	public int updateUser(int id,String name);
	@Sql(value="select age from user where id=?",returnType=Integer.class)
	public Integer getAgeById(int Integer);
//	@SqlStatement(params="id")
	public User getUserById(@Param("id") int id);

}

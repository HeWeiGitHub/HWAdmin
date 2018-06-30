package hw.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.DateTemplate;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.TableTemplate;

/*
* CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `bir` datetime DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
* gen by beetlsql 2016-03-02
*/

/**
 * @author Administrator
 * @date 2017年8月11日 
 * @description 
 * 		beetlSql的实体类
 */
@Table(name="user")
@TableTemplate("order by id asc")
//默认User类对应user表
//lombok的注解，自动生成属性方法
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
//	protected SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	sdf=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	@AutoID
	private Integer id ;
	private Integer age ;
	private String name ;
	private Timestamp bir ;
	
//	public User() {};
	
//	public User(Integer id,String name,Integer age,Timestamp bir) {
//		this.id=id;
//		this.name=name;
//		this.age=age;
//		this.bir=bir;
//	}
//	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public Integer getAge() {
//		return age;
//	}
//	
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Timestamp getBir() {
//		return bir;
//	}
////	@DateTemplate(accept="minDate,maxDate",compare=">=,<")
//	public void setBir(Timestamp bir) {
//		this.bir = bir;
//	}
//	@Override
//	public String toString() {
//		return "User{ id:"+this.id+"name:"+this.name+" age:"+this.age+" bir:"+this.bir.toString()+"}";
//	}
	
	

}
package hw.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.TableTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name="message")
@TableTemplate("order by id asc")
//lombok的注解，自动生成属性方法
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//如果使用ehcache缓存，model就要继承Serializable类
public class Message implements Serializable {
	private Integer id;
	private String nickName;
	private String ip;
	private Timestamp insertTime;

}

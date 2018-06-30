package hw.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author hw
 * @date 2017年7月21日 
 * @description 
 */
@ConfigurationProperties(prefix="web")
@PropertySource("classpath:/config/me.properties")
@Component("MyWebConfig")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyWebConf {
	
	private String authorName;
	private String authorSex;
	private String authorAge;
	private String authorPhone;
	
	private String description;

	
}

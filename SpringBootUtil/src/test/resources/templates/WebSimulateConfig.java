package hw.conf;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.simulate.WebSimulate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ConditionalOnMissingBean(WebSimulate.class)

public class WebSimulateConfig {

	@Bean	
	@ConditionalOnBean(ObjectMapper.class)
	public WebSimulate getWebSimulate(GroupTemplate gt,ObjectMapper objectMapper){
		return new WebSimulate(gt,new ObjectMapperJsonUtil(objectMapper) );
	}
}

package hw.conf;

import java.util.Properties;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.simulate.WebSimulate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeetlTemplateConfig {
	@Value("${spring.beetl.root}")
	String templatesPath;// 模板跟目录
	
	@Value("${spring.beetl.suffix}")
	String suffix;// 模板后缀
	
	@Value("${spring.beetl.order}")
	int order;
	
	@Value("${beetl-beetlsql.dev}")
	boolean dev;
	
	

	@Bean(initMethod = "init", name = "beetlConfig")
	@ConditionalOnMissingBean(name={"beetlConfig"})
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		try {
			Properties extProperties = new Properties();
			if(dev){
				extProperties.put("RESOURCE.autoCheck", "true");
			}
			
			beetlGroupUtilConfiguration.setConfigProperties(extProperties);
			ClasspathResourceLoader cploder = new ClasspathResourceLoader(BeetlTemplateConfig.class.getClassLoader(),
					templatesPath);
			beetlGroupUtilConfiguration.setResourceLoader(cploder);
			
			return beetlGroupUtilConfiguration;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Bean(name = "beetlViewResolver")
	@ConditionalOnMissingBean(name={"beetlViewResolver"})
	public BeetlSpringViewResolver getBeetlSpringViewResolver(
			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
//		beetlSpringViewResolver.setViewNames("*."+suffix);
		beetlSpringViewResolver.setSuffix(suffix);
		beetlSpringViewResolver.setOrder(order);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
	
	@Bean(name = "groupTemplate")
	public GroupTemplate getGroupTemplate(
			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		return  beetlGroupUtilConfiguration.getGroupTemplate();
	}
	
	
	
}

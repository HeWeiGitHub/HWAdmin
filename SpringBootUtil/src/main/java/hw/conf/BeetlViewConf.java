package hw.conf;

import hw.Application;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.filter.CharacterEncodingFilter;
@Configuration
public class BeetlViewConf {
	
	@Value("${spring.beetl.root}")
	String templatesPath;// 模板跟目录
	
	@Value("${spring.beetl.suffix}")
	String suffix;// 模板后缀
	
	@Value("${spring.beetl.order}")
	int order;
	
	@Value("${beetl-beetlsql.dev}")
	boolean dev;
	
	

	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            // WebAppResourceLoader 配置root路径是关键
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource(templatesPath).getFile().getPath());
            beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取配置文件信息
        beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource(templatesPath));
        return beetlGroupUtilConfiguration;

	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(
			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
//		beetlSpringViewResolver.setViewNames("*."+suffix);
		beetlSpringViewResolver.setSuffix("."+suffix);
		beetlSpringViewResolver.setOrder(order);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
	
	@Bean("characterEncodingFilter") 
    public Filter characterEncodingFilter() {  
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
        characterEncodingFilter.setEncoding("UTF-8");  
        characterEncodingFilter.setForceEncoding(true);  
        return characterEncodingFilter;  
    }  
	
//	@Bean(name = "groupTemplate")
//	public GroupTemplate getGroupTemplate(
//			@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
//		return  beetlGroupUtilConfiguration.getGroupTemplate();
//	}

}

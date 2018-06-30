package hw;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		ThymeleafAutoConfiguration.class})
//@EnableAutoConfiguration
public class Application {
	
//	static {
//        try{
//            //初始化log4j
//            String log4jPath = Application.class.getClassLoader().getResource("").getPath()+"config/log4j.properties";
//            System.out.println("初始化Log4j。。。。");
//            System.out.println("path is "+ log4jPath);
//            PropertyConfigurator.configure(log4jPath);
//        }catch (Exception e){
//        	System.out.println("Log4j初始化失败。。。。");
//            e.printStackTrace();
//        }
//    }

	public static void main(String[] args) {
		
		try{
            //初始化log4j
            String log4jPath = Application.class.getClassLoader().getResource("").getPath()+"config/log4j.properties";
            System.out.println("初始化Log4j。。。。");
            System.out.println("path is "+ log4jPath);
            PropertyConfigurator.configure(log4jPath);
        }catch (Exception e){
        	System.out.println("Log4j初始化失败。。。。");
            e.printStackTrace();
        }
		
//		SpringApplication.run(Application.class, args);
		SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
	}
//	private static final Logger logger=LoggerFactory.getLogger(Application.class);
	
	/*beetl模板配置，可以在这里配置也可以通过java的Config类来配置*/
	/*@Value("${spring.beetl.root}")
	private String templatePath;
	
	@Bean(name = "beetlConfig", initMethod = "init")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		logger.info(templatePath);
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(
					patternResolver.getResource(templatePath).getFile()
//					patternResolver.getResource("classpath:/templates").getFile()
							.getPath());
			beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("/config/beetl.properties"));
		logger.info("BeetlGroupUtilConfiguration is finishied");
		return beetlGroupUtilConfiguration;
	}
	
	@Bean(name="beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver=new BeetlSpringViewResolver();
//		beetlSpringViewResolver.setPrefix("/templates/");
		beetlSpringViewResolver.setSuffix(".html");
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}*/
	
	/*
	@ConditionalOnClass ： classpath中存在该类时起效 
	@ConditionalOnMissingClass ： classpath中不存在该类时起效 
	@ConditionalOnBean ： DI容器中存在该类型Bean时起效 
	@ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效 
	@ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效 
	@ConditionalOnExpression ： SpEL表达式结果为true时 
	@ConditionalOnProperty ： 参数设置或者值一致时起效 
	@ConditionalOnResource ： 指定的文件存在时起效 
	@ConditionalOnJndi ： 指定的JNDI存在时起效 
	@ConditionalOnJava ： 指定的Java版本存在时起效 
	@ConditionalOnWebApplication ： Web应用环境下起效 
	@ConditionalOnNotWebApplication ： 非Web应用环境下起效
	
	@AutoConfigureAfter：在指定的配置类初始化后再加载 
	@AutoConfigureBefore：在指定的配置类初始化前加载 
	@AutoConfigureOrder：数越小越先初始化
	
	DispatcherServletAutoConfiguration 注册DispatcherServlet 
	EmbeddedServletContainerAutoConfiguration.EmbeddedTomcat 注册Tomcat容器 
	ErrorMvcAutoConfiguration 注册异常处理器 
	HttpEncodingAutoConfiguration 注册编码过滤器CharacterEncodingFilter 
	HttpMessageConvertersAutoConfiguration 注册json或者xml处理器 
	JacksonAutoConfiguration 注册json对象解析器 
	MultipartAutoConfiguration 注册文件传输处理器 
	TransactionAutoConfiguration 注册事物管理处理器 
	ValidationAutoConfiguration 注册数据校验处理器 
	WebMvcAutoConfiguration 注册SpringMvc相关处理器
	*/

}

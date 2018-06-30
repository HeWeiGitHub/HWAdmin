package hw.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageHelper;

/**
 * MyBatis基础配置
 *
 * @author hw
 * @since 2015-12-19 10:11
 */
@Configuration
@Profile("prod")
@AutoConfigureAfter(DataSourceConf_Prod.class)
// @MapperScan("hw.dao.myBatis")
public class MyBatisConf {

	// private static final String TYPE_ALIASES_PACKAGE = "com.blog.model";
	// private static final String MAPPER_LOCATION = "classpath:/mybatis/*.xml";
	// @Autowired
	// Environment env;

	// @Autowired
	// DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean(PageHelper pageHelper,
			Environment env,
			@Qualifier("hikaricpDataSource") DataSource dataSource) {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(env
				.getProperty("mybatis.typeAliasesPackage"));

		// 添加插件
		Interceptor[] plugins = new Interceptor[] { pageHelper };
		bean.setPlugins(plugins);

		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources(env
					.getProperty("mybatis.mapperLocations")));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 分页插件
	@Bean(name = "pageHelper")
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("reasonable", "true");
		p.setProperty("supportMethodsArguments", "true");
		p.setProperty("returnPageInfo", "check");
		p.setProperty("params", "count=countSql");
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		p.setProperty("dialect", "mysql");
		pageHelper.setProperties(p);
		return pageHelper;
	}
	
//	mybatis的Mapper
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(Environment env) {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer
				.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage(env
				.getProperty("mybatis.BasePackage"));
		return mapperScannerConfigurer;
	}
	
//	通用Mapper tk.mybatis，需要有basedao和其他dao
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(Environment env) {
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage(env.getProperty("mybatis.BasePackage"));
//        Properties properties = new Properties();
//        properties.setProperty("mappers", "xyz.ibenben.zhongdian.common.BaseDao");
//        properties.setProperty("notEmpty", "false");
//        properties.setProperty("IDENTITY", "MYSQL");
//        mapperScannerConfigurer.setProperties(properties);
//		return mapperScannerConfigurer;
//	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(
			SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	// 事务已在beetlSqlConf配置中开启
	// @Bean(name = "txManager")
	// public DataSourceTransactionManager getDataSourceTransactionManager(
	// @Qualifier("druidDataSource") DataSource dataSource) {
	// DataSourceTransactionManager dsm = new DataSourceTransactionManager();
	// dsm.setDataSource(dataSource);
	// return dsm;
	// }

}

package hw.conf;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile(value = "prod")
@AutoConfigureBefore(MyBatisConf.class)
public class DataSourceConf_Prod {

	private Logger logger = LoggerFactory.getLogger(DataSourceConf_Prod.class);
	
//	hikaricp的数据库连接池
	@Primary
	@Bean(name = "hikaricpDataSource")
	public DataSource hikaricpDataSource(Environment env) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
		ds.setUsername(env.getProperty("spring.datasource.username"));
		ds.setPassword(env.getProperty("spring.datasource.password"));
		ds.setDriverClassName(env
				.getProperty("spring.datasource.driverClassName"));
		// ds.setDbType(env.getProperty("dbcp.datasource.type"));
		// ds.setInitialSize(Integer.valueOf(env
		// .getProperty("dbcp.datasource.initialSize")));
		ds.setMinimumIdle(Integer.valueOf(env
				.getProperty("dbcp.datasource.minIdle")));
		ds.setMaximumPoolSize(Integer.valueOf(env
				.getProperty("dbcp.datasource.maxActive")));
		ds.setConnectionTimeout(Long.valueOf(env
				.getProperty("dbcp.datasource.maxWait")));
		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<hikaricpDataSource init>>>>>>>>>>>>>>>>>>>>>>>>>");
		return ds;
	}

	// druid的数据库连接池
	// beetlsql 会早期注入，因此用Autowired 注入配置不行，得用更早的Enviroment 来获取配置变量
	// 使用beetl-framework-starter方式配置数据源，默认创建名为“dataSource”的bean
//	@Bean(name = "druidDataSource")
//	@Primary
//	public DataSource druidDataSource(Environment env) {
//		DruidDataSource ds = new DruidDataSource();
//		ds.setUrl(env.getProperty("spring.datasource.url"));
//		ds.setUsername(env.getProperty("spring.datasource.username"));
//		ds.setPassword(env.getProperty("spring.datasource.password"));
//		ds.setDriverClassName(env
//				.getProperty("spring.datasource.driverClassName"));
//		ds.setDbType(env.getProperty("druid.datasource.type"));
//		ds.setInitialSize(Integer.valueOf(env
//				.getProperty("druid.datasource.initialSize")));
//		ds.setMinIdle(Integer.valueOf(env
//				.getProperty("druid.datasource.minIdle")));
//		ds.setMaxActive(Integer.valueOf(env
//				.getProperty("druid.datasource.maxActive")));
//		ds.setMaxWait(Long.valueOf(env.getProperty("druid.datasource.maxWait")));
//
//		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<DruidDataSource init>>>>>>>>>>>>>>>>>>>>>>>>>");
//		return ds;
//	}

	// dbcp2的数据库连接池,dbcp2需要jdk1.7，否则会报错：Unsupported major.minor version 51.0
//	@Bean(name = "dataSource")
//	@Bean(name = "dbcp2DataSource")
//	public DataSource dbcp2DataSource(Environment env) {
//		BasicDataSource ds = new BasicDataSource();
//		ds.setUrl(env.getProperty("spring.datasource.url"));
//		ds.setUsername(env.getProperty("spring.datasource.username"));
//		ds.setPassword(env.getProperty("spring.datasource.password"));
//		ds.setDriverClassName(env
//				.getProperty("spring.datasource.driverClassName"));
//		// ds.setDbType(env.getProperty("dbcp.datasource.type"));
//		ds.setInitialSize(Integer.valueOf(env
//				.getProperty("dbcp.datasource.initialSize")));
//		ds.setMinIdle(Integer.valueOf(env
//				.getProperty("dbcp.datasource.minIdle")));
//		ds.setMaxTotal(Integer.valueOf(env
//				.getProperty("dbcp.datasource.maxActive")));
//		ds.setMaxWaitMillis(Long.valueOf(env
//				.getProperty("dbcp.datasource.maxWait")));
//
//		logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<dbcp2DataSource init>>>>>>>>>>>>>>>>>>>>>>>>>");
//		return ds;
//	}

	// @Bean(name = "druidDataSource", initMethod = "init", destroyMethod =
	// "close")
	// @Primary
	// public DataSource druidDataSource() {
	// DruidDataSource druidDataSource = new DruidDataSource();
	// druidDataSource.setUrl(propertyResolver.getProperty("url"));
	// druidDataSource.setUsername(propertyResolver.getProperty("username"));
	// druidDataSource.setPassword(propertyResolver.getProperty("password"));
	// druidDataSource.setDriverClassName(propertyResolver
	// .getProperty("driverClassName"));
	// druidDataSource.setDbType(propertyResolver.getProperty("type"));
	// druidDataSource.setInitialSize(Integer.valueOf(propertyResolver
	// .getProperty("initialSize")));
	// druidDataSource.setMinIdle(Integer.valueOf(propertyResolver
	// .getProperty("minIdle")));
	// druidDataSource.setMaxActive(Integer.valueOf(propertyResolver
	// .getProperty("maxActive")));
	// druidDataSource.setMaxWait(Long.valueOf(propertyResolver
	// .getProperty("maxWait")));
	// druidDataSource
	// .setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	// druidDataSource
	// .setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	// druidDataSource.setValidationQuery(validationQuery);
	// druidDataSource.setTestWhileIdle(testWhileIdle);
	// druidDataSource.setTestOnBorrow(testOnBorrow);
	// druidDataSource.setTestOnReturn(testOnReturn);
	// druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
	// druidDataSource
	// .setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
	// try {
	// datasource.setFilters(filters);
	// } catch (SQLException e) {
	// logger.error("druid configuration initialization filter", e);
	// }
	// datasource.setConnectionProperties(connectionProperties);
	// System.out
	// .println("-------------------- druidDataSource init ---------------------");
	// return druidDataSource;
	// }

}

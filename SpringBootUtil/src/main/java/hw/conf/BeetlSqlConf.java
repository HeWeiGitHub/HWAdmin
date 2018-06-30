package hw.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.beetl.core.om.ObjectUtil;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@ConditionalOnBean(DataSource.class)
@EnableTransactionManagement
@Profile("prod")
public class BeetlSqlConf implements EnvironmentAware {

	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
		this.propertyResolver = new RelaxedPropertyResolver(environment,
				"spring.datasource");

	}
	
//	@Bean(name = "dataSource")
//	  public DataSource dataSource(Environment env) {
//	    DruidDataSource ds = new DruidDataSource();
//	    ds.setUrl(env.getProperty("spring.datasource.url"));
//	    ds.setUsername(env.getProperty("spring.datasource.username"));
//	    ds.setPassword(env.getProperty("spring.datasource.password"));
//	    ds.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
//	    return ds;
//	  }
	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(
			@Qualifier("hikaricpDataSource") DataSource datasource,Environment env) {
		
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		// DruidDataSource source=new DruidDataSource();
		source.setMasterSource(datasource);
		
		factory.setCs(source);
		factory.setDbStyle((DBStyle)ObjectUtil.tryInstance(env.getProperty("beetlsql.dbStyle")));
		factory.setInterceptors(Boolean.valueOf(env.getProperty("beetl-beetlsql.dev")) ? new Interceptor[] { new DebugInterceptor() } : new Interceptor[0]);
		factory.setNc((NameConversion)ObjectUtil.tryInstance(env.getProperty("beetlsql.nameConversion")));
		ClasspathLoader loader = new ClasspathLoader(env.getProperty("beetlsql.sqlPath"));
		//不能直接设置通过loader的autocheck
		Properties ps = new Properties();
		ps.put("PRODUCT_MODE", Boolean.valueOf(env.getProperty("beetl-beetlsql.dev"))?"false":"true");
		factory.setExtProperties(ps);
		factory.setSqlLoader(loader);
		return factory;
	}

	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer(Environment env) {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		// Mapper类扫面的基础包
		conf.setBasePackage(env.getProperty("beetlsql.basePackage"));
		// Mapper类名的后缀
		conf.setDaoSuffix(env.getProperty("beetlsql.daoSuffix"));
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

	@Bean(name = "txManager")
	public DataSourceTransactionManager getDataSourceTransactionManager(
			@Qualifier("hikaricpDataSource") DataSource datasource) {
		DataSourceTransactionManager dsm = new DataSourceTransactionManager();
		dsm.setDataSource(datasource);
		return dsm;
	}
}

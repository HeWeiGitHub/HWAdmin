package hw.conf;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DataSourceConf_Dev {

	private Logger logger=LoggerFactory.getLogger(DataSourceConf_Dev.class);
//		 测试用的数据库连接
		 @Bean(name = "dataSource")
		 public DataSource getDataSource() {
		 logger.info("-------------------- primaryDataSource init ---------------------");
		 return DataSourceBuilder.create().url("jdbc:mysql://127.0.0.1/test")
		 .username("root").password("1234").build();
		 }
}

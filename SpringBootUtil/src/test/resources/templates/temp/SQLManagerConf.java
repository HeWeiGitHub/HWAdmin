package hw.conf;

import javax.sql.DataSource;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQLManagerConf {

	@Autowired
	private DataSource dataSource;
	
	@Bean(name="sqlManager")
	public SQLManager sqlManager() {
		
//	ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, "",
//			userName, password);
//	更常见的是，已经有了DataSource，创建ConnectionSource 可以采用如下代码
	ConnectionSource source = ConnectionSourceHelper.getSingle(dataSource);
//	如果是主从Datasource
//	ConnectionSource source = ConnectionSourceHelper.getMasterSlave(master,slaves)
	
	DBStyle mysql = new MySqlStyle();
	// sql语句放在classpagth的/sql 目录下
	SQLLoader loader = new ClasspathLoader("/beetlSql");
	// 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的
	UnderlinedNameConversion nc = new UnderlinedNameConversion();
	// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
	SQLManager sqlManager = new SQLManager(mysql, loader, source, nc,
			new Interceptor[] { new DebugInterceptor() });
	return sqlManager;	
	}

}

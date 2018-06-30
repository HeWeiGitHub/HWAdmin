package hw.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
//标注启动了缓存
@EnableCaching
public class CacheConfiguration {
	private final static Logger logger=LoggerFactory.getLogger(CacheConfiguration.class);

 /*
  * ehcache 主要的管理器
  */
 @Bean(name = "ehCacheCacheManager")
 public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
	 EhCacheCacheManager ehCacheCacheManager=new EhCacheCacheManager (bean.getObject ());
	 logger.info("ehCacheCacheManager configurated...................................");
//     return new EhCacheCacheManager (bean.getObject ());
     return ehCacheCacheManager;
 }

 /*
  * 据shared与否的设置,Spring分别通过CacheManager.create()或new CacheManager()方式来创建一个ehcache基地.
  */
 @Bean(name = "ehCacheManagerFactoryBean")
 public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
     EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
     cacheManagerFactoryBean.setConfigLocation (new ClassPathResource ("config/ehcache.xml"));
     cacheManagerFactoryBean.setShared (true);
     logger.info("ehcacheManagerFactoryBean configurated...................................");
     return cacheManagerFactoryBean;
 }
}

package cn.com.kugou.provider.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/6
 */
@Configuration
public class DataSourceConfig {
    @Value("${datasource.primary}")
    private String primaryDataSource;
    @Value("${datasource.secondary}")
    private String secondaryDataSource;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        logger.info("-------------多数据源装配开始-----------");
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        dsMap.put(this.getPrimaryDataSource(), primaryDataSource());
        dsMap.put(this.getSecondaryDataSource(), secondaryDataSource());

        dynamicDataSource.setTargetDataSources(dsMap);
        logger.info("-------------多数据源装配完成-----------");
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    public String getPrimaryDataSource() {
        return primaryDataSource;
    }

    public void setPrimaryDataSource(String primaryDataSource) {
        this.primaryDataSource = primaryDataSource;
    }

    public String getSecondaryDataSource() {
        return secondaryDataSource;
    }

    public void setSecondaryDataSource(String secondaryDataSource) {
        this.secondaryDataSource = secondaryDataSource;
    }
}
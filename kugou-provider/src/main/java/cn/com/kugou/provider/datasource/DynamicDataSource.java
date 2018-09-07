package cn.com.kugou.provider.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/6
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);


    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("----------数据源为:{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}

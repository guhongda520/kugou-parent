package cn.com.kugou.provider.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/6
 */
public class DataSourceContextHolder {
    private final static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 默认数据源
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        logger.info("----------切换到{}数据源---------", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}


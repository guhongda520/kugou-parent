package cn.com.kugou.common.datasource;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author 黄尚
 * @brief
 * @details
 * @date 2018/9/6
 *
 * 自定义注解 + AOP的方式实现数据源动态切换。
 * 用户可以通过@DS注解动态切换数据源，默认使用主数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${datasource.primary}")
    private String primaryDataSource;

    @Before("execution(* cn.com.kugou.*.daoimpl.*.*(..))")
    public void beforeSwitchDS(JoinPoint point){
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        // 如果没有注解DS注解，默认使用主数据源
        String dataSource = this.getPrimaryDataSource();
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            logger.info("切换数据源失败：{}", e);
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("execution(* cn.com.kugou.*.daoimpl.*.*(..))")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDB();
    }

    public String getPrimaryDataSource() {
        return primaryDataSource;
    }

    public void setPrimaryDataSource(String primaryDataSource) {
        this.primaryDataSource = primaryDataSource;
    }
}

package com.example.project_a.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description Shiro 配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * @description Shiro Filter 拦截器相关配置
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("-------------ShiroConfig.shiroFilter() 开始------------");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置shiro 安全管理器 (必须设置)
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 设置默认登录页面(如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射)
        shiroFilterFactoryBean.setLoginUrl("http://127.0.0.1:8010/login");

        /**自定义过滤器 (过滤链定义，从上向下顺序执行，一般将 "/**" 放在最为下边,这是一个坑呢，一不小心代码就不好使了)*/
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("-------------Shiro拦截器工厂类注入成功-------------");
        return shiroFilterFactoryBean;
    }

    /**
     * @description 注入 shiro的核心安全事务管理器 securityManager
     *      1、 自定义的 realm
     *      2、 自定义session管理
     */
    @Bean(name="securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置 自定义的 realm
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理
        securityManager.setSessionManager(mySessionManager());
        // 自定义缓存管理器
        return securityManager;
    }

    /**
     * @description 2、注入自定义Realm类：AuthRealm
     */
    @Bean(name="myShiroRealm")
    public AuthRealm myShiroRealm() {
        return new AuthRealm();
    }

    /**
     * @description 2、 shiro的session管理器：可以用来实现自定义的session管理器
     */
    @Bean
    public DefaultWebSessionManager mySessionManager(){
//        DefaultWebSessionManager manager = new ShiroSessionManager();
        DefaultWebSessionManager manager =  new DefaultWebSessionManager();
        //manager.setCacheManager(cacheManager);                                 // 加入缓存管理器

        manager.setSessionDAO(getRedisSessionDao());                             // 设置 自定义的SessionDao
        manager.setGlobalSessionTimeout(18000);   // 设置全局session超时时间

        manager.setDeleteInvalidSessions(true);                                  // 删除过期的session
        manager.setSessionValidationSchedulerEnabled(true);                      // 是否定时检查session
        return manager;
    }

    /**
     * @description 4、RedisSessionDao
     */
    @Bean
    public RedisSessionDAO getRedisSessionDao(){
        return new RedisSessionDAO();
    }

}

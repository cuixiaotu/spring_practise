package com.xiaotu.shiro.auth.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xiaotu.shiro.auth.shiro.ShiroRealm;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录成功后跳转url
        shiroFilterFactoryBean.setSuccessUrl("index");
        //未授权url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //定义filterChain ,静态资源不拦
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/fonts/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");

        //druid数据源监控不拦截
        filterChainDefinitionMap.put("/druid/**","anon");
        //配置退出过滤器
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/","anon");
        //其他需要认证
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        // 配置
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

}

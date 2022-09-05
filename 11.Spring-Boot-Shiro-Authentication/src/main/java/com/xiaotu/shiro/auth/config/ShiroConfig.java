package com.xiaotu.shiro.auth.config;

import com.xiaotu.shiro.auth.shiro.ShiroRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;

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
        filterChainDefinitionMap.put("/css/**","anno");
        filterChainDefinitionMap.put("/js/**","anno");
        filterChainDefinitionMap.put("/fonts/**","anno");
        filterChainDefinitionMap.put("/img/**","anno");

        //druid数据源监控不拦截
        filterChainDefinitionMap.put("/druid/**","anno");
        //配置退出过滤器
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/","anno");
        //其他需要认证
        filterChainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public WebSecurityManager securityManager(){
        // 配置
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public Realm shiroRealm(){
        // 配置Realm，需自己实现
        Realm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

}

package com.mengxuegu.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer //开启认证服务器功能
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置被允许访问此认证服务器的客户端详情信息
     * 方式1: 内存方式管理
     * 方式2: 数据库管理
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用内存方式
        clients.inMemory()
                .withClient("mengxuegu-pc") //客户端pc
                // 客户端密码，要加密，不然一直要求登录，获取不到令牌，而且一定不能被泄露
                .secret(passwordEncoder.encode("mengxuegu-secret"))
                // 资源id，如商品资源
                .resourceIds("product-server")
                // 授权类型，可同时支持多种授权类型
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                // 授权范围标识，哪部分资源可访问 (all是标识，不是代表所有)
                .scopes("all")
                // false 跳转到授权页面手动点击授权，true 不用手动授权，直接响应授权码
                .autoApprove(false)
                // 客户端回调地址
                .redirectUris("http://www.mengxuegu.com/");
    }
}

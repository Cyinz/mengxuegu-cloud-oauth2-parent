package com.mengxuegu.oauth2.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
public class TokenConfig {

    /**
     * Redis 管理令牌
     */
/*    @Autowired
    private RedisConnectionFactory redisConnectionFactory;*/

    /**
     * JDBC管理令牌
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public TokenStore tokenStore() {
        // Redis管理令牌
        /*return new RedisTokenStore(redisConnectionFactory);*/

        // JDBC管理令牌
        return new JdbcTokenStore(dataSource());
    }

}

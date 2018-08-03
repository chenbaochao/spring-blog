package com.blog.cbc.auth.config;


import com.blog.cbc.auth.service.security.BlogUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore = new InMemoryTokenStore();

    private final AuthenticationManager authenticationManager;

    private final BlogUserDetailsService userDetailsService;

    public AuthorizationServer(AuthenticationManager authenticationManager, BlogUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("cbc")
                    .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("cbc"))
                    .authorizedGrantTypes("refresh_token","password")
                    .scopes("ui")
        .and()
                    .withClient("cbc-admin")
                    .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("cbc-admin"))
                    .authorizedGrantTypes("refresh_token", "password")
                    .scopes("ui","ui-admin")
        .and()
                    .withClient("blog-account")
                    .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("blog-account"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
        .and()
                    .withClient("blog-admin")
                    .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("blog-admin"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server")
        .and()
                    .withClient("blog-service")
                    .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("blog-service"))
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("server");


    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
    }

}

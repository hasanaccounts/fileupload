// This source code is generated by GenAI - WatsonX
package com.pru.fancy.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/v3/api-docs/swagger*/**", "/v3/api-docs", "/v2/api-docs", "/configuration/**", "/swagger*/**", "/swagger-ui/**", "/webjars/**", "/actuator", "/actuator/**", "/health", "/refreshResources", "/ports", "/run-websocket/**").permitAll().antMatchers("/uploadFile").hasAnyRole("USER").and().oauth2ResourceServer().opaqueToken().introspectionUri("https://idp.example.com/introspect").introspectionClientCredentials("client", "secret");
    }
}

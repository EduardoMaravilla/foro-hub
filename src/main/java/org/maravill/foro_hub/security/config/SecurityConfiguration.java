package org.maravill.foro_hub.security.config;

import org.maravill.foro_hub.security.config.filters.JwtsAuthenticationFilter;
import org.maravill.foro_hub.security.config.handlers.CustomAccessDeniedHandler;
import org.maravill.foro_hub.security.config.handlers.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   CustomAccessDeniedHandler accessDeniedHandler,
                                                   CustomAuthenticationEntryPoint entryPointHandler,
                                                   AuthenticationProvider authenticationProvider,
                                                   JwtsAuthenticationFilter jwtsAuthenticationFilter,
                                                   AuthorizationManager<RequestAuthorizationContext> authorizationManager) throws Exception {
        httpSecurity.sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authenticationProvider(authenticationProvider);
        httpSecurity.addFilterBefore(jwtsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry.anyRequest().access(authorizationManager));
        httpSecurity.exceptionHandling(handlingConfigurer -> handlingConfigurer
                .authenticationEntryPoint(entryPointHandler)
                .accessDeniedHandler(accessDeniedHandler));
        return httpSecurity.build();
    }

}


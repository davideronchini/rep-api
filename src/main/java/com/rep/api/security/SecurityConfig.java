package com.rep.api.security;

import com.rep.api.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.rep.api.user.Permission.*;
import static com.rep.api.user.Role.ADMIN;
import static com.rep.api.user.Role.BUSINESS;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/rep-api/v1/auth/**").permitAll()
                        .requestMatchers("/rep-api/v1/business/**").hasAnyRole(BUSINESS.name())
                        .requestMatchers(GET, "/rep-api/v1/business/**").hasAnyAuthority(BUSINESS_READ.name())
                        .requestMatchers(POST, "/rep-api/v1/business/**").hasAnyAuthority(BUSINESS_CREATE.name())
                        .requestMatchers(PUT, "/rep-api/v1/business/**").hasAnyAuthority(BUSINESS_UPDATE.name())
                        .requestMatchers(DELETE, "/rep-api/v1/business/**").hasAnyAuthority(BUSINESS_DELETE.name())
                        .requestMatchers("/rep-api/v1/admin/**").hasAnyRole(ADMIN.name())
                        .requestMatchers(GET, "/rep-api/v1/admin/**").hasAnyAuthority(ADMIN_READ.name())
                        .requestMatchers(POST, "/rep-api/v1/admin/**").hasAnyAuthority(ADMIN_CREATE.name())
                        .requestMatchers(PUT, "/rep-api/v1/admin/**").hasAnyAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/rep-api/v1/admin/**").hasAnyAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/rep-api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> {
                            SecurityContextHolder.clearContext();
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                );


        return http.build();
    }
}

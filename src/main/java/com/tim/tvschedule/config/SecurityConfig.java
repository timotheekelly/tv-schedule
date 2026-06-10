package com.tim.tvschedule.config;

import com.tim.tvschedule.application.auth.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CorsConfigurationSource corsConfigurationSource;

    @Value("${frontend.url:http://localhost:5173}")
    private String frontendUrl;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CorsConfigurationSource corsConfigurationSource) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/error",
                                "/oauth2/**",
                                "/api/auth/user"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl(frontendUrl, true)
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_OK)
                        )
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                );

        return http.build();
    }
}

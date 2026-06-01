package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
                this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())

                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .authorizeHttpRequests(auth -> auth

                                                // 登入、Swagger 不需要權限
                                                .requestMatchers(
                                                                "/api/auth/**",
                                                                "/swagger-ui/**",
                                                                "/swagger-ui.html",
                                                                "/v3/api-docs/**")
                                                .permitAll()

                                                // USER 與 ADMIN 都可以查看客戶
                                                .requestMatchers(HttpMethod.GET,
                                                                "/api/customers",
                                                                "/api/customers/**")
                                                .hasAnyRole("USER", "ADMIN")

                                                // 只有 ADMIN 可以新增客戶
                                                .requestMatchers(HttpMethod.POST,
                                                                "/api/customers",
                                                                "/api/customers/**")
                                                .hasRole("ADMIN")

                                                // 只有 ADMIN 可以修改客戶
                                                .requestMatchers(HttpMethod.PUT,
                                                                "/api/customers",
                                                                "/api/customers/**")
                                                .hasRole("ADMIN")

                                                // 只有 ADMIN 可以刪除客戶
                                                .requestMatchers(HttpMethod.DELETE,
                                                                "/api/customers",
                                                                "/api/customers/**")
                                                .hasRole("ADMIN")

                                                .anyRequest().authenticated())

                                // 讓每次 API 請求都先讀取 Bearer token
                                .addFilterBefore(
                                                jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class)

                                .build();
        }
}
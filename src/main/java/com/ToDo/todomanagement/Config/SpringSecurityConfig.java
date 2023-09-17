package com.ToDo.todomanagement.Config;

import com.ToDo.todomanagement.Security.JwtAuthenticationEntryPoint;
import com.ToDo.todomanagement.Security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Basic Auth
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeHttpRequests((auth) -> {
//            auth.requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN");
//            auth.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "USER");
//            auth.requestMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyRole("ADMIN", "USER");
//            auth.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            auth.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        httpSecurity.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // In Memory
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails pavel = User.builder().username("pavel").password(passwordEncoder().encode("password")).roles("USER").build();
//        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(pavel, admin);
//    }
}

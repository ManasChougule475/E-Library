package com.minorproject.library.e_Library.security;

import com.minorproject.library.e_Library.security.filters.JwtAuthenticationFilter;
import com.minorproject.library.e_Library.security.filters.JwtRequestFilter;
import com.minorproject.library.e_Library.service.CustomOAuth2UserService;
import com.minorproject.library.e_Library.service.MyUserDetailService;
import com.minorproject.library.e_Library.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    private final MyUserDetailService myUserDetailService;

//    @Autowired
//    public SecurityConfig(MyUserDetailService myUserDetailService) {
//        this.myUserDetailService = myUserDetailService;
//    }

    private final JwtUtil jwtUtil;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(JwtUtil jwtUtil, JwtRequestFilter jwtRequestFilter) {
        this.jwtUtil = jwtUtil;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil);
//        jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");  // Setting the login URL for JWT authentication

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/book/**").hasAnyRole("MEMBER", "LIBRARIAN") // to access this endpoint authentication is required and only users with given role are allowed to access the endpoint
                .requestMatchers("/member/**").hasAnyRole("LIBRARIAN") // only librarian can access this endpoint
                .requestMatchers("/auth/**", "/public/**").permitAll() // any user can access this endpoints (no need of authentication to access this endpoints)
                .anyRequest().authenticated())
            // Configuring OAuth2 login using GitHub as the provider
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/oauth2/authorization/github")  // Custom login page for GitHub OAuth2
                .defaultSuccessUrl("/", true) // Redirect users to "/" upon successful login
                .userInfoEndpoint(userInfo -> userInfo.userService(this.customOAuth2UserService()))) // Custom logic to handle user info
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Building the security filter chain
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

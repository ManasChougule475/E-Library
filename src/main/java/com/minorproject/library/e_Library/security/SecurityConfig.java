package com.minorproject.library.e_Library.security;

import com.minorproject.library.e_Library.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;

    @Autowired
    public SecurityConfig(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/book/**").hasAnyRole("MEMBER", "LIBRARIAN") // to access this endpoint authentication is required and only users with given role are allowed to access the endpoint
                .requestMatchers("/member/**").hasAnyRole("LIBRARIAN") // only librarian can access this endpoint
                .requestMatchers("/auth/**", "/public/**").permitAll() // any user can access this endpoints (no need of authentication to access this endpoints)
                .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults()); // used form login (use browser to access above endpoints) to authenticate user against database credentials
        return http.build();

//        http.authorizeHttpRequests(authorize->authorize.anyRequest().permitAll()); // to add librarian inside db with bcrypted password
//        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // authenticationProvider authenticates user based on user details present in myUserDetailService (in-memory userList) and
        // it uses PasswordEncoder(BCryptPasswordEncoder)  which encodes user entered password before comparing them with stored encoded password retrieved from db from the UserDetailsService
        authenticationProvider.setUserDetailsService(this.myUserDetailService);
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

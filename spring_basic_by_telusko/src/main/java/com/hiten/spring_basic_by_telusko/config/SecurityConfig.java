package com.hiten.spring_basic_by_telusko.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;

    }

//    Adding security filter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer-> customizer.disable())
                .authorizeHttpRequests(request->request
                        .requestMatchers("register","login")
                        .permitAll()
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();




    }

// We want user from database that's why this code not used
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1= User
//                .withDefaultPasswordEncoder()
//                .username("bala")
//                .password("12345")
//                .roles("Admin")
//                .build();
//
//        UserDetails user2= User
//                .withDefaultPasswordEncoder()
//                .username("Hiten")
//                .password("4187")
//                .roles("User")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }







//    validating user from database

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }



//    for JWT implementation

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cofig) throws Exception {
        return cofig.getAuthenticationManager();
    }
}

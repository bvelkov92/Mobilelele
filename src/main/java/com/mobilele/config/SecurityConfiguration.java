package com.mobilele.config;

import com.mobilele.repository.UserRepository;
import com.mobilele.service.Impl.MobileleUserDetailsService;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity){
        httpSecurity.authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/users/profile","/users/profile/changePassword", "/users/profile/editProfile").authenticated()
                        .requestMatchers("/offers/add", "/offers/all").authenticated()
                        .requestMatchers("/administration", "/administration/brandAndModels", "/administration/users").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> formLogin.loginPage("/users/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/users/login?error")
        ).logout(
                logout -> logout.logoutUrl("/users/logout")
                        .logoutSuccessUrl("/users/login")
                        .invalidateHttpSession(true)
        );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return  new MobileleUserDetailsService(userRepository);
    }
}

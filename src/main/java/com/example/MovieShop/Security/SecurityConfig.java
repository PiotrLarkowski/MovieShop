package com.example.MovieShop.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("qawsed")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }
    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/actor").permitAll()
                .antMatchers(HttpMethod.POST,"/actor").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/actor").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/actor").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/address").permitAll()
                .antMatchers(HttpMethod.POST,"/address").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/address").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/address").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/client").permitAll()
                .antMatchers(HttpMethod.POST,"/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/client").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/movie").permitAll()
                .antMatchers(HttpMethod.POST,"/movie").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movie").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movie").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/movieRent").permitAll()
                .antMatchers(HttpMethod.POST,"/movieRent").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/movieRent").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/movieRent").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/genres").permitAll()

                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

}

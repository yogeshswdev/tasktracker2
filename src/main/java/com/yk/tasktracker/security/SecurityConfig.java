package com.yk.tasktracker.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter {

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsManager() {
     * UserDetails john = User.builder().username("john")
     * .roles("EMPLOYEE")
     * .password("{noop}test1234")
     * .build();
     * UserDetails mary = User.builder().username("mary")
     * .roles("EMPLOYEE", "MANAGER")
     * .password("{noop}test1234")
     * .build();
     * UserDetails susan = User.builder().username("susan")
     * .roles("EMPLOYEE", "MANAGER", "ADMIN")
     * .password("{noop}test1234")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(john, mary, susan);
     * }
     */

    // add jdbc support
    @Bean
    public UserDetailsManager jdbDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id = ? ");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id = ? ");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> {
            try {
                configurer
                        // .requestMatchers("/tasks/v1/list").permitAll()
                        // .requestMatchers("/leaders").hasAnyRole("MANAGER", "ADMIN")
                        // .requestMatchers("/admins").hasAnyRole("ADMIN")

                        // http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().formLogin();
                        // return http.build();

                        .anyRequest().authenticated()
                        .and().formLogin()
                        .defaultSuccessUrl("/tasks/v1/list", true)
                        .permitAll();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        })

        // .exceptionHandling(configurer ->
        // configurer.accessDeniedPage("/access-denied"))

        ;
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChainLogout(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(logout -> {
            try {
                http.logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        return http.build();
    }

}
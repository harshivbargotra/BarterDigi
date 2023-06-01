package com.stackroute.adminservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.stackroute.adminservice.filter.JwtFilter;
import com.stackroute.adminservice.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private JwtFilter jwtFilter;
	

	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
    }
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/adminService/authenticate","/adminService/register","/api/v1/auth/**","/v3/api-docs/**","/v2/api-docs","/swagger-resources/**","/swagger-ui/**","/webjars/**").permitAll()
                .antMatchers("/adminService/swagger-ui/index.html","/adminService")
                .permitAll()
                .antMatchers("/adminService/**").hasAuthority("ADMIN")
                .antMatchers("/swagger-ui/index.html#/").permitAll()
                .antMatchers("/adminService/api/v1/auth/**","/adminService/v3/api-docs/**","/adminService/v2/api-docs","/adminService/swagger-resources/**","/adminService/swagger-ui/**","/adminService/webjars/**").permitAll()
                .antMatchers("/api/v1/auth/**","/v3/api-docs/**","/v2/api-docs","/swagger-resources/**","/swagger-ui/**","/webjars/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

                

    }


}

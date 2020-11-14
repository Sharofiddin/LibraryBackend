package my.projects.library.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import my.projects.library.security.filters.AuthenticationFilter;
import my.projects.library.security.filters.LoginFilter;
import my.projects.library.security.filters.MyCORSFilter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
     
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    @Override  
    protected void configure(HttpSecurity http) throws Exception {  
          http.csrf().disable().cors().and().authorizeRequests()  
                  .antMatchers(HttpMethod.POST, "/api/login").permitAll()  
                  .anyRequest().authenticated()  
                  .and()  
                  .addFilterBefore(new MyCORSFilter(), ChannelProcessingFilter.class)
                  .addFilterBefore(new LoginFilter("/api/login", authenticationManager()),  
                          UsernamePasswordAuthenticationFilter.class)  
                  .addFilterBefore(new AuthenticationFilter(),  
                          UsernamePasswordAuthenticationFilter.class);  
      }  
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

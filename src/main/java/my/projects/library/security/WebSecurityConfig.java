package my.projects.library.security;

import java.util.Arrays;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import my.projects.library.security.filters.AuthenticationFilter;
import my.projects.library.security.filters.LoginFilter;
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
                  .addFilterBefore(new LoginFilter("/api/login", authenticationManager()),  
                          UsernamePasswordAuthenticationFilter.class)  
                  .addFilterBefore(new AuthenticationFilter(),  
                          UsernamePasswordAuthenticationFilter.class);  
      }  
    
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
//    @Bean  
//    CorsConfigurationSource corsConfigurationSource() {  
//          UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
//          CorsConfiguration config = new CorsConfiguration();  
//          config.setAllowedOrigins(Arrays.asList("*"));  
//          config.setAllowedMethods(Arrays.asList("*"));  
//          config.setAllowedHeaders(Arrays.asList("*"));  
//          config.setAllowCredentials(true);  
//          config.applyPermitDefaultValues();  
//          
//          source.registerCorsConfiguration("/api/login", config);
//          source.registerCorsConfiguration("/**", config);  
//          return source;  
//      }  
//    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

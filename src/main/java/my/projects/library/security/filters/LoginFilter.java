package my.projects.library.security.filters;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import my.projects.library.beans.Appuser;
import my.projects.library.services.AuthenticationService;

@Slf4j
public class LoginFilter extends AbstractAuthenticationProcessingFilter{
	public LoginFilter(String url, AuthenticationManager authManager) {  
        super(new AntPathRequestMatcher(url));  
        setAuthenticationManager(authManager);  
    }  
  
  @Override  
  public Authentication attemptAuthentication(  
            HttpServletRequest req, HttpServletResponse res)  
            throws IOException {  
        Appuser appuser = new ObjectMapper()  
                .readValue(req.getInputStream(), Appuser.class);  
        log.info("/api/login Appuser:{}", appuser);
        return getAuthenticationManager().authenticate(  
                new UsernamePasswordAuthenticationToken(  
                        appuser.getLogin(),  
                        appuser.getPassword(),  
                        Collections.emptyList()  
                )  
        );  
    }  
  
  @Override  
  protected void successfulAuthentication(  
            HttpServletRequest req,  
            HttpServletResponse res, FilterChain chain,  
            Authentication auth) {  
        AuthenticationService.addJWTToken(res, auth.getName());  
    }  
}

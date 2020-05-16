package my.projects.library.services;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationService {
	static final long EXPIRATIONTIME = 864_000_00;  
    static final String SIGNINGKEY = "must_keep_secure";  
    static final String BEARER_PREFIX = "Bearer";  
  
    public static  void addJWTToken(HttpServletResponse response, String username) {  
        String jwtToken = Jwts.builder().setSubject(username)  
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))  
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)  
                .compact();  
        response.addHeader("Authorization", BEARER_PREFIX + " " + jwtToken);  
        response.addHeader("Access-Control-Expose-Headers", "Authorization");  
    }  
  
    public static Authentication getAuthentication(HttpServletRequest request) {  
        String token = request.getHeader("Authorization");  
        if (token != null) {  
            String user = Jwts.parser()  
                    .setSigningKey(SIGNINGKEY)  
                    .parseClaimsJws(token.replace(BEARER_PREFIX, ""))  
                    .getBody()  
                    .getSubject();  
  
            if (user != null) {  
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());  
            } else {  
                throw new RuntimeException("Authentication failed");  
            }  
        }  
        return null;  
    }  
}

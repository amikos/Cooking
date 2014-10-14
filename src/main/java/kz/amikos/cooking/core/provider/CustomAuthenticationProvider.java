package kz.amikos.cooking.core.provider;

import java.util.Collection;

import kz.amikos.cooking.core.service.user.UserService;
import kz.amikos.cooking.web.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private UserService userService;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
 
        User user = userService.loadUserByUsername(username);
 
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        
        System.out.println("finded user " + user.getUsername());
        System.out.println("roles user " + user.getAuthorities().toString());
 
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
 
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }
    
    public static User getAuthenticatedUser() {
    	User user = null;
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			user = (User) auth.getPrincipal();
		}
		return user;
    }
    
    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
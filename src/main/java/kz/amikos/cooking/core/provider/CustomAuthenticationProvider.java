package kz.amikos.cooking.core.provider;

import kz.amikos.cooking.core.service.user.UserService;
import kz.amikos.cooking.web.models.UserRole;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
 
    @Autowired
    private UserService userService;
 
    @Override
    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
 
        kz.amikos.cooking.web.models.User myUser = userService.getByUsername(username);
        
        if (myUser == null) {
            throw new BadCredentialsException("Username not found.");
        }

        try {
            if (!password.equals(myUser.getPassword())) {
                throw new BadCredentialsException("Wrong password.");
            }
        }catch(ObjectNotFoundException e){
            throw new BadCredentialsException("Username not found.");
        }

        List<GrantedAuthority> authorities = buildUserAuthority(myUser.getUserRole());
        
        User user = buildUserForAuthentication(myUser, authorities);
 
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }
    
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(kz.amikos.cooking.web.models.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
			user.isEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
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
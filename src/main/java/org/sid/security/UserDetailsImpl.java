package org.sid.security;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.entity.AppUser;
import org.sid.service.AccontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsImpl implements UserDetailsService  {
	@Autowired
     AccontService accontService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser= accontService.loadUserbyUsername(username);
		if (appUser==null)  throw new UsernameNotFoundException("UserName Not Fond!");
		Collection<GrantedAuthority> authorization=new ArrayList<>();
		appUser.getRoles().forEach(us->{
			authorization.add(new SimpleGrantedAuthority(us.getRoleName()));
		});
		
		return new User(appUser.getUsername(),appUser.getPassword(),authorization);
	}

}

package org.sid.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class jwtAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,x-Requested-With,Content-Type,Access-Control-Requested-Method,Access-Control-Requested-Method,Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials,Authorization");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		if(request.getMethod().equals("Option")) {
          response.setStatus(HttpServletResponse.SC_OK);	
          }
		else if(request.getRequestURI().equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		else {
			
		
		String jwtToken= request.getHeader(ParamSecurity.HEADER);
		if ((jwtToken==null)|| !jwtToken.startsWith(ParamSecurity.HEADER_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(ParamSecurity.SECRET)).build();
		DecodedJWT decodedJWT=jwtVerifier.verify(jwtToken.substring(ParamSecurity.HEADER_PREFIX.length()));
		String username=decodedJWT.getSubject();
		List<String>roles=decodedJWT.getClaims().get("roles").asList(String.class);
		Collection<GrantedAuthority>authorities=new ArrayList<>();
		roles.forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r));
		});
		UsernamePasswordAuthenticationToken user= 
				new  UsernamePasswordAuthenticationToken(username,null, authorities);
		SecurityContextHolder.getContext().setAuthentication(user);
		chain.doFilter(request, response);
		
	}
	}
	
}

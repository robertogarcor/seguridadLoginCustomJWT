package com.roberto.seguridadLoginCustomJWT.jwt;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.roberto.seguridadLoginCustomJWT.models.MyUserPrincipal;

@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String SECRET_KEY;
	@Value("${jwt.expiration}")
	private int EXPIRATION;
	private String AUTHORITIES = "authorities";
	
	public String generateToken(Authentication auth) {
		MyUserPrincipal userPrincipal = (MyUserPrincipal) auth.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + EXPIRATION * 1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	public Collection<? extends GrantedAuthority> getAuthorities(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Malformend Token");
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported Token");
		} catch (ExpiredJwtException e) {
			logger.error("Expired Token");
		} catch (IllegalArgumentException e ) {
			logger.error("Invalid Token");
		} catch (SignatureException e) {
			logger.error("Fail in signature");
		}
		return false;
	}

}

package com.roberto.seguridadLoginCustomJWT.apiController;

import com.roberto.seguridadLoginCustomJWT.dto.JwtDto;
import com.roberto.seguridadLoginCustomJWT.dto.LoginUserDto;
import com.roberto.seguridadLoginCustomJWT.jwt.JwtProvider;
import com.roberto.seguridadLoginCustomJWT.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8082")
public class SecurityLoginApiController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
		System.out.println("Hello api authenticate login.");
		if(bindingResult.hasErrors()) {
			System.out.println("Username or password invalid.");
			return new ResponseEntity(new Message("Username or password invalid."), HttpStatus.BAD_REQUEST);
		}
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								loginUserDto.getUsername(),
								loginUserDto.getPassword())
				);
		System.out.println(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwt = jwtProvider.generateToken(authentication);
		System.out.println(jwt);
		JwtDto jwTdto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwTdto, HttpStatus.OK);
	}



}

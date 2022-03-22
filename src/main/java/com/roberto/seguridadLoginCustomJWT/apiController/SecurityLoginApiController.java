package com.roberto.seguridadLoginCustomJWT.apiController;

import com.roberto.seguridadLoginCustomJWT.dto.JwtDto;
import com.roberto.seguridadLoginCustomJWT.dto.LoginUserDto;
import com.roberto.seguridadLoginCustomJWT.dto.NewUserDto;
import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.roberto.seguridadLoginCustomJWT.jwt.JwtProvider;
import com.roberto.seguridadLoginCustomJWT.models.Role;
import com.roberto.seguridadLoginCustomJWT.models.User;
import com.roberto.seguridadLoginCustomJWT.services.RoleService;
import com.roberto.seguridadLoginCustomJWT.services.UserService;
import com.roberto.seguridadLoginCustomJWT.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8082")
public class SecurityLoginApiController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

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

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Data user or email invalid. Try again!"), HttpStatus.BAD_REQUEST);
		}
		if(userService.existsByUsername(newUserDto.getUsername())) {
			return new ResponseEntity<>(new Message("Username is not available."), HttpStatus.NOT_ACCEPTABLE);
		}
		if(userService.existsByEmail(newUserDto.getEmail())) {
			return new ResponseEntity<>(new Message("Email is not available."), HttpStatus.NOT_ACCEPTABLE);
		}
		// Register New User
		User user = new User(
				newUserDto.getUsername(),
				passwordEncoder.encode(newUserDto.getPassword()),
				newUserDto.getSurname(),
				newUserDto.getEmail()
				);
		Set<Role> roles = new HashSet<>();
		Role role = roleService.getByRolename(RoleType.ROLE_USER).get();
		System.out.println("Role: " + role);
		roles.add(roleService.getByRolename(RoleType.ROLE_USER).get());
		user.setRoles(roles);
		userService.saveUser(user);
		return new ResponseEntity<>(new Message("User save ok!"), HttpStatus.CREATED);
	}

}

package com.roberto.seguridadLoginCustomJWT.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.roberto.seguridadLoginCustomJWT.jwt.JwtEntryPoint;
import com.roberto.seguridadLoginCustomJWT.jwt.JwtTokenFilter;
import com.roberto.seguridadLoginCustomJWT.services.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsService;
	
	@Autowired
	private JwtEntryPoint jwtEntryPoint;
	
	
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().authorizeRequests()
			.antMatchers("/login", "/checklogin").permitAll()
			.antMatchers("/api/auth/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.and()
			// Solo valido para API -> Si activo no funciona el form login
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			//.and()
			.logout(logout -> logout
					.logoutSuccessUrl("/login?logout")
					.clearAuthentication(true)
					.deleteCookies("JSESSIONID")
					.invalidateHttpSession(true)
					);
		http.csrf().disable(); // Deshabilitado, 401 Unauthorized en peticiones POST API
		http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/fonts/**");
	}
	
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}

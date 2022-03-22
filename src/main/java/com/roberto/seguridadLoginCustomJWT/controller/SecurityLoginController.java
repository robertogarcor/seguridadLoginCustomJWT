package com.roberto.seguridadLoginCustomJWT.controller;

import java.security.Principal;

import com.roberto.seguridadLoginCustomJWT.dto.LoginUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.roberto.seguridadLoginCustomJWT.models.MyUserPrincipal;
import com.roberto.seguridadLoginCustomJWT.services.MyUserDetailsServiceImpl;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller()
public class SecurityLoginController {

	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsService;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginUserDto", new LoginUserDto());
		return "login";
	}

	@PostMapping("/login")
	public String processLogin(@ModelAttribute LoginUserDto loginUserDto, RedirectAttributes redirectAttrs) {
		return "index";
	}

	/**
	 * Alternativas a Model -> 
	 * 		ModelAndView -> clase de spring que engloga el modelo y la vista.
	 * 		ModelMap -> similar a Model.
	 */
	@GetMapping("/")
	public String index(Principal p, Model model) {
		System.out.println("Hello user " + p.getName());
		MyUserPrincipal myUserPrincipal = (MyUserPrincipal) myUserDetailsService.loadUserByUsername(p.getName());
		model.addAttribute("user", myUserPrincipal);
		return "index";
	}
	
}

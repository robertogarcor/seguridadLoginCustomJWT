package com.roberto.seguridadLoginCustomJWT.authController;

import com.roberto.seguridadLoginCustomJWT.dto.LoginUserDto;
import com.roberto.seguridadLoginCustomJWT.dto.NewUserDto;
import com.roberto.seguridadLoginCustomJWT.enums.RoleType;
import com.roberto.seguridadLoginCustomJWT.models.MyUserPrincipal;
import com.roberto.seguridadLoginCustomJWT.models.Role;
import com.roberto.seguridadLoginCustomJWT.models.User;
import com.roberto.seguridadLoginCustomJWT.services.MyUserDetailsServiceImpl;
import com.roberto.seguridadLoginCustomJWT.services.RoleService;
import com.roberto.seguridadLoginCustomJWT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller()
public class FormAuthController {

    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("newUserDto", new NewUserDto());
        return "user-register";
    }

    // Sin @ModelAttribute tambien funciona
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute ("newUserDto") NewUserDto newUserDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        System.out.println(newUserDto.getUsername());
        if (bindingResult.hasErrors()) {
            return "user-register";
        }
        if(userService.existsByUsername(newUserDto.getUsername())) {
            //redirectAttributes.addFlashAttribute("existsUser", "Username is not available.");
            model.addAttribute("existsUser", "Username is not available.");
            return "user-register";
        }
        if(userService.existsByEmail(newUserDto.getEmail())) {
            model.addAttribute("existsEmail", "Email is not available.");
            return "user-register";
        }
        // Register New User
        User user = new User(
                newUserDto.getUsername(),
                passwordEncoder.encode(newUserDto.getPassword()),
                newUserDto.getSurname(),
                newUserDto.getEmail()
        );
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRolename(RoleType.ROLE_USER).get());
        user.setRoles(roles);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successUser", "User register ok!");
        return "redirect:/login";
        //return new ResponseEntity<>(new Message("User save ok!"), HttpStatus.CREATED);
    }
}

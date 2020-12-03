package com.sda.shop.controllers;

import com.sda.shop.entity.AuthoritiesEntity;
import com.sda.shop.entity.UserEntity;
import com.sda.shop.repository.AuthoritiesRepository;
import com.sda.shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityController() {
        logger.info(getClass().getSimpleName() + " created");
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("login-form");
        return modelAndView;
    }

    @GetMapping("/register")
    ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserEntity());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUserRequest(@ModelAttribute("user") UserEntity userEntity) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        userEntity.setEnabled(true);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity = userRepository.save(userEntity);
        if (null != userEntity.getAuthorities() && !userEntity.getAuthorities().isEmpty()) {


            AuthoritiesEntity authoritiesEntity = new AuthoritiesEntity();

            authoritiesEntity.setUsername(userEntity.getUsername());
            authoritiesEntity.setAuthority("USER");
            authoritiesRepository.save(authoritiesEntity);
        }

        return modelAndView;

    }

    @GetMapping("/editUser/{user}")
    public ModelAndView editUser(@PathVariable(name = "user") String username) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", userRepository.findById(username).get());
        return modelAndView;


    }

    @GetMapping("/login-error")
    public ModelAndView loginError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("login-form");
        HttpSession session = request.getSession();
        String errorMessage = null;
        if (session != null) {
            Object object = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (object instanceof BadCredentialsException) {
                errorMessage = "user or password is  wrong";
            }
        }
        modelAndView.addObject("errorMessage", errorMessage);
        return modelAndView;
    }
}

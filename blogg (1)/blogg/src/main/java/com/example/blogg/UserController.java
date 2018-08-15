package com.example.blogg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String getReg(Users user){
        return "register";
    }

    @PostMapping("/register")
    public String postReg(Users user, HttpServletRequest request){

        Users users = userRepository.findByUsername(user.getUsername());
        if (users == null) {
            userRepository.save(new Users(user.getUsername(), user.getPassword()));
        }

        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Users user){
        return "login";
    }


    @PostMapping("/login")
    public String login(@Valid Users user, HttpServletRequest request, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "login";
        }

        Users users = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (users != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("LoggedIn", true);
            return "redirect:blog";
        }
        return "login";
    }

    @GetMapping("/blog")
    public String getBlog() {
        return "blog";
    }
}

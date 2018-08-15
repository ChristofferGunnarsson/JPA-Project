package com.example.blogg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String reg(HttpServletRequest request){

        HttpSession session = request.getSession(true);
        session.setAttribute("LoggedIn", true);


        return "register";

    }


    @GetMapping("/login")
    public String getLogin(Users user){

        return "login";
    }


    @PostMapping("/login")
    public String login(Users user, HttpServletRequest request, BindingResult bindingResult){

        HttpSession session = request.getSession(true);
        session.setAttribute("LoggedIn", true);
        if(bindingResult.hasErrors()){
            return "login";
        }

        /*
        userRepository.findByUsername(user.getUsername());
        userRepository.findByPassword(user.getPassword());
        */

        Users users = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        System.out.println(users.getUserID() + " - " + users.getUsername() + " - " + users.getPassword());
        return "login";
    }
}

package com.example.blogg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private BlogRepository blogRepository;

    private int userId;

    @GetMapping("/")
    public String getIndex(Model model){

        List<Posts> blogposts = (List<Posts>) blogRepository.findAll();
        model.addAttribute("blogposts", blogposts);
        return "index";
    }

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
        this.userId = users.getUserID();
        if (users != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("LoggedIn", true);
            session.setAttribute("userID", user.getUserID());
            return "redirect:blog";
        }
        return "login";
    }

    @GetMapping("/blog")
    public String getBlog(Posts post) {

        return "blog";
    }

    @PostMapping("/blog")
    public String postBlog(@Valid Posts posts, HttpServletRequest request) {

        HttpSession session = request.getSession(true);
        if(session.getAttribute("LoggedIn") != null){

            blogRepository.save(new Posts(this.userId,posts.getBlogposts()));
            return "blog";
        }
        return "index";
    }
}

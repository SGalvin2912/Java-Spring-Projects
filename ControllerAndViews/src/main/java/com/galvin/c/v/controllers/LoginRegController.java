//package com.galvin.c.v.controllers;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import com.galvin.c.v.models.LoginUser;
//import com.galvin.c.v.models.User;
//import com.galvin.c.v.services.UserService;
//
//@Controller
//public class LoginRegController {
//
//	@Autowired
//    private UserService userServ;
//    
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("newUser", new User());
//        model.addAttribute("newLogin", new LoginUser());
//        return "index.jsp";
//    }
//    
//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("newUser") User newUser, 
//            BindingResult result, Model model, HttpSession session) {
//        this.userServ.register(newUser, result);
//        if(result.hasErrors()) {
//            model.addAttribute("newLogin", new LoginUser());
//            return "registrationPage.jsp";
//        }
//        session.setAttribute("user_id", newUser.getId());
//        return "redirect:/home";
//    }
//    
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
//            BindingResult result, Model model, HttpSession session) {
//        User user = userServ.login(newLogin, result);
//        if(result.hasErrors()) {
//            model.addAttribute("newUser", new User());
//            return "loginPage.jsp";
//        }
//        session.setAttribute("user_id", user.getId());
//        return "redirect:/home";
//    }
//    
//    @GetMapping("/home")
//    public String home() {
//    	return "homePage.jsp";
//    }
//}

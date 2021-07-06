package com.galvin.javaexam1.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.galvin.javaexam1.models.Idea;
import com.galvin.javaexam1.models.LoginUser;
import com.galvin.javaexam1.models.User;
import com.galvin.javaexam1.services.UserService;

@Controller
public class HomeController {

	@Autowired
    private UserService userServ;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	System.out.println("**********");
    	System.out.println(session.getAttribute("user_id"));
    	System.out.println("**********");
    	if(session.getAttribute("user_id") == null) {
    		return "redirect:/";
    	}
    	Long loggedInId = (Long)session.getAttribute("user_id");
    	User loggedInUserObj = this.userServ.findOneUser(loggedInId);
    	model.addAttribute("loggedInUser", loggedInUserObj);
    	List<Idea> allIdeaItems = this.userServ.findAllIdeaItems();
    	model.addAttribute("allIdeaItems", allIdeaItems);
    	return "dashboard.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    @GetMapping("/idea/new")
    public String newIdeaItem(@ModelAttribute("idea")Idea idea) {
    	return "newIdea.jsp";
    }
    
    @PostMapping("/idea/create")
    public String createIdeaItem(@Valid @ModelAttribute("idea") Idea idea, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "newIdea.jsp";
    	}else {
    		Long idOfLoggedInUser = (Long)session.getAttribute("user_id");
    		User loggedInUserObj = this.userServ.findOneUser(idOfLoggedInUser);
    		idea.setUploader(loggedInUserObj);
    		this.userServ.createIdeaItem(idea);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/idea/{id}/info")
    public String showIdeaItem(@PathVariable("id") Long id, Model model) {
    	Idea ideaObj = this.userServ.findOneIdeaItem(id);
    	model.addAttribute("ideaObj", ideaObj);
    	List<User> allUsers = this.userServ.findAllUsers();
    	model.addAttribute("allUsers", allUsers);
    	return "ideaInfo.jsp";
    }
    
    @GetMapping("/addUserToIdea/{id}")
    public String addUserToIdea(@PathVariable("id")Long ideaId, HttpSession session) {
    	this.userServ.joinIdeaToUser((Long)session.getAttribute("user_id"), ideaId);
    	return "redirect:/home";
    }
    
    @GetMapping("/idea/{id}/edit")
    public String editIdeaItem(@PathVariable("id") Long id, Model model) {
    	Idea ideaObj = this.userServ.findOneIdeaItem(id);
    	model.addAttribute("ideaObj", ideaObj);
    	return "editIdeaInfo.jsp";
    }
    
    @PostMapping("/idea/{id}/update")
    public String updateIdeaItem(@PathVariable("id") Long id, @Valid @ModelAttribute("ideaObj") Idea idea, BindingResult result) {
    	if(result.hasErrors()) {
    		return "editIdeaInfo.jsp";
    	}else {
    		Idea oGIdeaObj = this.userServ.findOneIdeaItem(id);
    		idea.setUploader(oGIdeaObj.getUploader());
    		this.userServ.updateOneIdeaItem(idea);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/idea/{id}/delete")
    public String deleteIdeaItem(@PathVariable("id")Long id) {
    	this.userServ.deleteOneIdeaItem(id);
    	return "redirect:/home";
    }
    
}

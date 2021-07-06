package com.galvin.beltreviewer.controllers;

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

import com.galvin.beltreviewer.models.LoginUser;
import com.galvin.beltreviewer.models.Menu;
import com.galvin.beltreviewer.models.User;
import com.galvin.beltreviewer.services.UserService;

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
    	List<Menu> allMenuItems = this.userServ.findAllMenuItems();
    	model.addAttribute("allMenuItems", allMenuItems);
    	return "dashboard.jsp";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user_id");
    	return "redirect:/";
    }
    
    @GetMapping("/menu/new")
    public String newMenuItem(@ModelAttribute("menu")Menu menu) {
    	return "newMenu.jsp";
    }
    
    @PostMapping("/menu/create")
    public String createMenuItem(@Valid @ModelAttribute("menu") Menu menu, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "newMenu.jsp";
    	}else {
    		Long idOfLoggedInUser = (Long)session.getAttribute("user_id");
    		User loggedInUserObj = this.userServ.findOneUser(idOfLoggedInUser);
    		menu.setUploader(loggedInUserObj);
    		this.userServ.createMenuItem(menu);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/menu/{id}/info")
    public String showMenuItem(@PathVariable("id") Long id, Model model) {
    	Menu menuObj = this.userServ.findOneMenuItem(id);
    	model.addAttribute("menuObj", menuObj);
    	return "itemInfo.jsp";
    }
    
    @GetMapping("/menu/{id}/edit")
    public String editMenuItem(@PathVariable("id") Long id, Model model) {
    	Menu menuObj = this.userServ.findOneMenuItem(id);
    	model.addAttribute("menuObj", menuObj);
    	return "editItemInfo.jsp";
    }
    
    @PostMapping("/menu/{id}/update")
    public String updateMenuItem(@PathVariable("id") Long id, @Valid @ModelAttribute("menuObj") Menu menu, BindingResult result) {
    	if(result.hasErrors()) {
    		return "editItemInfo.jsp";
    	}else {
    		Menu oGMenuObj = this.userServ.findOneMenuItem(id);
    		menu.setUploader(oGMenuObj.getUploader());
    		this.userServ.updateOneMenuItem(menu);
    		return "redirect:/home";
    	}
    }
    
    @GetMapping("/menu/{id}/delete")
    public String deleteMenuItem(@PathVariable("id")Long id) {
    	this.userServ.deleteOneMenuItem(id);
    	return "redirect:/home";
    }
    
}

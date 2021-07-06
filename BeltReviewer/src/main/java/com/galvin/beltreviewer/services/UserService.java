package com.galvin.beltreviewer.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.galvin.beltreviewer.models.LoginUser;
import com.galvin.beltreviewer.models.Menu;
import com.galvin.beltreviewer.models.User;
import com.galvin.beltreviewer.repositories.MenuRepository;
import com.galvin.beltreviewer.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private MenuRepository menuRepo;
    
//    REGISTER METHOD
    public User register(User newUser, BindingResult result) {
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
//    LOGIN METHOD
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        User user = potentialUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

	public User findOneUser(Long id) {
		return this.userRepo.findById(id).orElse(null);
	}
	
//	METHODS FOR THE MENU OPERATIONS
	public List<Menu> findAllMenuItems() {
		return (List<Menu>)this.menuRepo.findAll();
	}
	
	public Menu createMenuItem(Menu m) {
		return this.menuRepo.save(m);
	}
	
	public Menu findOneMenuItem(Long id) {
		return this.menuRepo.findById(id).orElse(null);
	}
    
	public Menu updateOneMenuItem(Menu m) {
		return this.menuRepo.save(m);
	}
	
	public void deleteOneMenuItem(Long id) {
		this.menuRepo.deleteById(id);
	}
}

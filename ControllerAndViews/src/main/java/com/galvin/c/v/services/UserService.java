package com.galvin.c.v.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.validation.BindingResult;

//import com.galvin.c.v.models.LoginUser;
import com.galvin.c.v.models.User;
import com.galvin.c.v.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User findUserById(Long id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		}else {
			return null;
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		if(user == null) {
			return false;
		}else {
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
	
    
//    public User register(User newUser, BindingResult result) {
//        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
//            result.rejectValue("email", "Unique", "This email is already in use!");
//        }
//        if(!newUser.getPassword().equals(newUser.getConfirm())) {
//            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
//        }
//        if(result.hasErrors()) {
//            return null;
//        } else {
//            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
//            newUser.setPassword(hashed);
//            return userRepo.save(newUser);
//        }
//    }
//    
//    public User login(LoginUser newLogin, BindingResult result) {
//        if(result.hasErrors()) {
//            return null;
//        }
//        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
//        if(!potentialUser.isPresent()) {
//            result.rejectValue("email", "Unique", "Unknown email!");
//            return null;
//        }
//        User user = potentialUser.get();
//        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
//            result.rejectValue("password", "Matches", "Invalid Password!");
//        }
//        if(result.hasErrors()) {
//            return null;
//        } else {
//            return user;
//        }
//    }
}

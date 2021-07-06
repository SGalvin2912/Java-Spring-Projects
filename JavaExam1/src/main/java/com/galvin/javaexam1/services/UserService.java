package com.galvin.javaexam1.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.galvin.javaexam1.models.LoginUser;
import com.galvin.javaexam1.models.Idea;
import com.galvin.javaexam1.models.User;
import com.galvin.javaexam1.repositories.IdeaRepository;
import com.galvin.javaexam1.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepo;
    
    @Autowired
    private IdeaRepository ideaRepo;

	private List<User> usersWhoLike;
    
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
	
//	METHODS FOR THE IDEA OPERATIONS
	public List<Idea> findAllIdeaItems() {
		return (List<Idea>)this.ideaRepo.findAll();
	}
	
	public List<User> findAllUsers() {
		return (List<User>)this.userRepo.findAll();
	}
	
	public Idea createIdeaItem(Idea i) {
		return this.ideaRepo.save(i);
	}
	
	public Idea findOneIdeaItem(Long id) {
		return this.ideaRepo.findById(id).orElse(null);
	}
    
	public Idea updateOneIdeaItem(Idea m) {
		return this.ideaRepo.save(m);
	}
	
	public void deleteOneIdeaItem(Long id) {
		this.ideaRepo.deleteById(id);
	}
	
	public void joinIdeaToUser(Long userId, Long ideaId) {
		User u = this.userRepo.findById(userId).orElse(null);
		Idea i = this.ideaRepo.findById(ideaId).orElse(null);
		i.getUsersWhoLike().add(u);
		this.ideaRepo.save(i);
	}
	
	public List<User> getUsersWhoLike() {
		return usersWhoLike;
	}

	public void setUsersWhoLike(List<User> usersWhoLike) {
		this.usersWhoLike = usersWhoLike;
	}

	public int likeCount() {
		return getUsersWhoLike().size();
	}
//	
	public boolean isLiked() {
		return getUsersWhoLike().size() > 0;
	}

//	public void createLike(Long ideaId, User authenticatedUser) {
//		Idea i = this.ideaRepo.findById(ideaId).orElse(null);
//		i.getLikes().add(authenticatedUser);
//		this.ideaRepo.save(i);
//	}
}

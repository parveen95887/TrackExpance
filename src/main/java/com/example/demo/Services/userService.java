package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.user;
import com.example.demo.repository.UserRepository;

@Service
public class userService {
    
	 @Autowired
	 private UserRepository userRepository;
	
	//create  
	public user saveUser(user user) {
		
	  user user1=userRepository.save(user);
	  return user1;
	}

	 // Update an existing user
    public user updateUser(long id, user updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user newuser= userRepository.save(user);
            return newuser;
        }).orElseThrow(() -> new RuntimeException("User not found"));
         
    }

    // Get a user by ID
    public Optional<user> getUserById(Long uid) {
        return userRepository.findById(uid);
    }

    // Get all users
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }
    
    public boolean validateUser(String email, String password) {
        user user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    
 
    
   public user getByEmail(String email)
   {
	   user currentuser=this.userRepository.findByEmail(email);
	   return currentuser;
   }
    
    // Delete a user by ID
    public void deleteUser(Long uid) {
        userRepository.deleteById(uid);
    }
}

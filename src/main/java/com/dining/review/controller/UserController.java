package com.dining.review.controller;

import com.dining.review.model.User;
import com.dining.review.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/registerUser")
    public void registerUser(@RequestBody User user){
        validateUser(user.getUserName());

        userRepository.save(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        Optional<User> userToUpdateOptional = userRepository.findById(user.getId());
        if(!userToUpdateOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User userToUpdate = updateUserDetails(user);

        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        validateUserName(userName);
        Optional<User> userToUpdateOptional = userRepository.findByUserName(userName);
        if(!userToUpdateOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userToUpdateOptional.get();
    }

    public User updateUserDetails(User user){
        User userToUpdate = new User();

        if(!ObjectUtils.isEmpty(user.getCity())){
            userToUpdate.setCity(user.getCity());
        }
        if(!ObjectUtils.isEmpty(user.getZipcode())){
            userToUpdate.setZipcode(user.getZipcode());
        }
        if(!ObjectUtils.isEmpty(user.getDairyAllergy())){
            userToUpdate.setDairyAllergy(user.getDairyAllergy());
        }
        if(!ObjectUtils.isEmpty(user.getEggAllergy())){
            userToUpdate.setEggAllergy(user.getEggAllergy());
        }
        if(!ObjectUtils.isEmpty(user.getPeanutAllergy())){
            userToUpdate.setPeanutAllergy(user.getPeanutAllergy());
        }

        return userToUpdate;
    }

    public void validateUser(String userName){
        validateUserName(userName);
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public void validateUserName(String userName){
        if(userName.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}

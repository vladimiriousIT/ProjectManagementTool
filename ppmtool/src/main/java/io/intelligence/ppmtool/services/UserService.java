package io.intelligence.ppmtool.services;

import io.intelligence.ppmtool.Exceptions.UserNameAlreadyExistsException;
import io.intelligence.ppmtool.domain.User;
import io.intelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User saveUser(User newUser) {
    try{
      //Username has to be unique (exception)
      newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
      newUser.setUsername(newUser.getUsername());
      // Make sure that password and confirm password match
      // We don't persist or show the confirmPassword
      newUser.setConfirmPassword("");
      return userRepository.save(newUser);
    }catch (Exception e){
      throw  new UserNameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
    }
  }
}

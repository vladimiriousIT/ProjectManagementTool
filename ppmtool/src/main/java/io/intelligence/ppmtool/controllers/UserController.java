package io.intelligence.ppmtool.controllers;

import io.intelligence.ppmtool.domain.User;
import io.intelligence.ppmtool.payload.JWTLoginSuccessResponse;
import io.intelligence.ppmtool.payload.LoginRequest;
import io.intelligence.ppmtool.security.JwtTokenProvider;
import io.intelligence.ppmtool.services.UserService;
import io.intelligence.ppmtool.services.ValidationErrorService;
import io.intelligence.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static io.intelligence.ppmtool.security.SecurityConstraints.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  ValidationErrorService validationErrorService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserValidator userValidator;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    ResponseEntity<?> errorMap = validationErrorService.mapValidationService(result);
    if(errorMap!= null)
      return errorMap;

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

    return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
    //Validate passwords match
    userValidator.validate(user, result);

    ResponseEntity<?> errorMap = validationErrorService.mapValidationService(result);
    if(errorMap != null) return errorMap;

    User newUser = userService.saveUser(user);

    return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
  }
}

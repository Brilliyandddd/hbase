package com.doyatama.university.controller;

import com.doyatama.university.exception.ResourceNotFoundException;
import com.doyatama.university.model.Subject;
import com.doyatama.university.model.User;
import com.doyatama.university.payload.*;
import com.doyatama.university.security.CurrentUser;
import com.doyatama.university.security.UserPrincipal;
import com.doyatama.university.service.DepartmentService;
import com.doyatama.university.repository.UserRepository;
import com.doyatama.university.service.SubjectService;
import com.doyatama.university.service.UserService;
import com.doyatama.university.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.io.IOException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private UserService userService = new UserService();

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getRoles().equalsIgnoreCase("1") ? "ROLE_ADMINISTRATOR" : currentUser.getRoles().equalsIgnoreCase("2") ? "ROLE_LECTURE" : "ROLE_ADMINISTRATOR","", "");
        return userSummary;
    }

    
    @GetMapping("/users/{userId}")
    public PagedResponse<User> getUserById(@PathVariable String userId) throws IOException {
        return userService.getUserById(userId);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) throws IOException {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) throws IOException {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) throws IOException {
        User user = userRepository.findByUsername(username);
        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
        return userProfile;
    }

    @GetMapping("/users")
    public PagedResponse<User> getUsers(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                              @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return userService.getAllUser(page, size);
    }

    @GetMapping("/users/not-used-account")
    public PagedResponse<User> getUserNotUses(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                        @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) throws IOException {
        return userService.getUserNotUsedAccount(page, size);
    }

    @PostMapping("/users")
public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) throws IOException {
    User savedUser = userService.saveUserFromRequest(userRequest);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
}

@DeleteMapping("/user/delete/{id}")
public ResponseEntity<?> deleteUser(@PathVariable String id) throws IOException {
    userRepository.deleteById(id);
    return ResponseEntity.ok("User deleted successfully");
}

}

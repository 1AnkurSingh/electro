package electro.controller;

import electro.model.User;
import electro.model.userDto.UserDto;
import electro.service.LoginResponse;
import electro.service.UserPortfolioResponse;
import electro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;



    // Add User
    @PostMapping("/add")
    public ResponseEntity<String> addSingleUser(@RequestBody UserDto user, @RequestParam(required = false) String inviteCode) {
        UserDto addedUser = userService.addSingleUser(user);
        if (addedUser == null) {
            return new ResponseEntity<>("User with the same phone number already exists", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        }
    }
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody UserDto userDto , @RequestParam(required = false) String inviteCode) {
        return userService.loginUser(userDto);
        //


    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getBonus/{userId}")
    public ResponseEntity<String> getBonus(@PathVariable String userId) {
        ResponseEntity<String> bonusResponse = userService.getBonus(userId);
        String bonus = bonusResponse.getBody();
        return new ResponseEntity<>(bonus, HttpStatus.OK);
    }



}


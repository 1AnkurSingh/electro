package electro.controller;

import electro.model.userDto.UserDto;
import electro.service.UserPortfolioResponse;
import electro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public UserPortfolioResponse loginUser(@RequestBody UserDto userDto , @RequestParam(required = false) String inviteCode) {
        return userService.loginUser(userDto);
        //
    }
}


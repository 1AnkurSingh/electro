package electro.controller;

import electro.model.ApiCallRecord;
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
import java.util.Optional;

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
        // Check if userId exists in the database
        if (!userService.isUserIdPresent(userId)) {
            return new ResponseEntity<>("userId not present", HttpStatus.NOT_FOUND);
        }

        ResponseEntity<String> bonusResponse = userService.getBonus(userId);
        String bonus = bonusResponse.getBody();
        return new ResponseEntity<>(bonus, HttpStatus.OK);
    }

//    @GetMapping("/bonusRecord/{userId}")
//    public ResponseEntity<ApiCallRecord> bonusRecord(@PathVariable String userId) {
//        Optional<ApiCallRecord> apiCallRecordOptional = userService.bonusRecord(userId);
//
//        if (apiCallRecordOptional.isPresent()) {
//            ApiCallRecord apiCallRecord = apiCallRecordOptional.get();
//            return new ResponseEntity<>(apiCallRecord, HttpStatus.OK);
//        } else {
//            // If the record is not present, you may want to return a different HTTP status code, such as NOT_FOUND
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/bonusRecord/{userId}")
    public ResponseEntity<?> bonusRecord(@PathVariable String userId) {
        Optional<ApiCallRecord> apiCallRecordOptional = userService.bonusRecord(userId);

        if (apiCallRecordOptional.isPresent()) {
            ApiCallRecord apiCallRecord = apiCallRecordOptional.get();
            return new ResponseEntity<>(apiCallRecord, HttpStatus.OK);
        } else {
            // If the record is not present, return a response indicating that the user ID is not found
            String errorMessage = "User ID not found: " + userId;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/totalAmount/{userId}")
    public Integer getTotalAmountByUserId(@PathVariable String userId) {
        return userService.getTotalAmountByUserId(userId);
    }






}


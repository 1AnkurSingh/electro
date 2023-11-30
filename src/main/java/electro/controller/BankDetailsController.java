package electro.controller;

import electro.model.BankDetails;
import electro.service.BankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bank")
//@CrossOrigin("*")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})

public class BankDetailsController {

    @Autowired
    BankDetailsService bankDetailsService;

    @PutMapping("/addd/{userId}")
    public ResponseEntity<String> addBankDetails(@PathVariable String userId, @RequestBody BankDetails bankDetails) {
        // Check if the userId already exists in the database
        if (bankDetailsService.doesUserExist(userId)) {
            return new ResponseEntity<>("User ID already exists", HttpStatus.BAD_REQUEST);
        }

        // Set the userId in the BankDetails object
        bankDetails.setUserId(userId);

        // Add bank details to the database
        BankDetails addedBankDetails = bankDetailsService.addBankDetails(bankDetails);

        return new ResponseEntity<>("Successfully added bank details", HttpStatus.CREATED);
    }



    @PutMapping("/a/{userId}")
    public ResponseEntity<String> addBankDetails2(@PathVariable String userId, @RequestBody BankDetails bankDetails) {
        // Check if the userId already exists in the database
        if (bankDetailsService.doesUserExist(userId)) {
            return new ResponseEntity<>("User ID already exists", HttpStatus.BAD_REQUEST);
        }

        // Set the userId in the BankDetails object
        bankDetails.setUserId(userId);

        // Add bank details to the database
        BankDetails addedBankDetails = bankDetailsService.addBankDetails(bankDetails);

        return new ResponseEntity<>("Successfully added bank details", HttpStatus.CREATED);
    }

        @GetMapping("/getAccountById/{userId}")
    public ResponseEntity<BankDetails>etAccountById(@PathVariable String userId){
            BankDetails bankDetailsByUserId = bankDetailsService.findBankDetailsByUserId(userId);
            return new ResponseEntity<>(bankDetailsByUserId,HttpStatus.OK);
        }

}





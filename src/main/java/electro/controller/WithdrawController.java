package electro.controller;

import electro.model.Withdraw;
import electro.repository.WithdrawRepository;
import electro.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController

public class WithdrawController {

        @Autowired
        WithdrawService withdrawService;

    @PostMapping("/withdrawById/{userId}")
    public Withdraw withdrawById(@RequestBody Withdraw withdraw){
        // The timestamp is automatically set in the constructor
        return withdrawService.withdrawById(withdraw);
    }

}




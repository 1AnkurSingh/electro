package electro.controller;

import electro.model.Withdraw;
import electro.repository.WithdrawRepository;
import electro.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController

public class WithdrawController {

        @Autowired
        WithdrawService withdrawService;

    @PostMapping("/withdrawById/{userId}")
    public Withdraw withdrawById(@PathVariable Long userId, @RequestBody Withdraw withdraw) {
        withdraw.setUserId(userId);  // Set userId from the path variable
        return withdrawService.withdrawById(withdraw);
    }

    @GetMapping("/getwithdrawdataByuserId/{userId}")
    public List<Withdraw> getWithdrawDataByUserId(@PathVariable Long userId) {
        return withdrawService.getWithdrawDataByUserId(userId);
    }

}




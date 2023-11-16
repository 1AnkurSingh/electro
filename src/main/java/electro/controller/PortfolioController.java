package electro.controller;

import electro.model.Portfolio;
import electro.model.User;
import electro.repository.PortfolioRepository;
import electro.repository.UserRepository;
import electro.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private PortfolioRepository  portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<Portfolio> getPortfolioByUserId(@PathVariable int userId) {
        Portfolio portfolio = portfolioService.getPortfolioByUserId(userId);
        if (portfolio == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

//    @PostMapping("/create")
//    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
//        // Save the Portfolio data to the database
//        return portfolioRepository.save(portfolio);
//    }

    @PostMapping("/create")
    public Portfolio createPortfolio(@RequestBody Portfolio request) {
        User user = new User();
        user.setPhoneNumber(request.getUser().getPhoneNumber());
        user.setPassword(request.getUser().getPassword());
        user = userRepository.save(user);

        Portfolio portfolio = new Portfolio();
        portfolio.setUser(user);
        portfolio.setTotalAssets(request.getTotalAssets());
        portfolio.setTotalIncome(request.getTeamIncome());
        portfolio.setTodaysIncome(request.getTodaysIncome());
        portfolio.setTotalIncome(request.getTotalIncome());
        portfolio.setCurrentBalance(request.getCurrentBalance());

        return portfolioRepository.save(portfolio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable int id, @RequestBody Portfolio updatedPortfolio) {
        Portfolio updated = portfolioService.updatePortfolio(id, updatedPortfolio);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }
}

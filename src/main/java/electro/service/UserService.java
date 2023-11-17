package electro.service;

import electro.model.BonusTransaction;
import electro.model.Portfolio;
import electro.model.User;
import electro.model.userDto.UserDto;
import electro.repository.BonusTransactionRepository;
import electro.repository.PortfolioRepository;
import electro.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Builder
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    BonusTransactionRepository bonusTransactionRepository;


    private static LocalDate lastClaimDate = null;
    private static boolean bonusClaimed = false;

//    public UserDto  addSingleUser( UserDto userDto){
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//        if(existingUser.isPresent()){
//        return null;
//
//
//    }else
//        {
//            User user = dtoToEntity(userDto);
//            User save1 = userRepository.save(user);
//
//            return entityToDto(save1);
//        }
//
//    }

//    public UserDto addSingleUser(UserDto userDto) {
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//        User inviteCode = userRepository.findByRandom5DiditNumber(Integer.parseInt(userDto.getInviteCode()));
//
//        if (existingUser.isPresent()) {
//            return null;
//        } else {
//            User user = dtoToEntity(userDto);
//            User savedUser = userRepository.save(user);
//
//            // Update referral status for all users with the given ID
//            int userId = inviteCode.getId();
//            List<User> referralUsers = userRepository.findAllById(Collections.singleton(userId));
//
//            for (User existingReferralUser : referralUsers) {
//                Portfolio existingReferralPortfolio = portfolioRepository.findByUser(existingReferralUser);
//                if (existingReferralPortfolio != null) {
//                    existingReferralPortfolio.setReferralStatus(userDto.getPhoneNumber());
//                    portfolioRepository.save(existingReferralPortfolio);
//                }
//            }
//
//            // Create a blank Portfolio for the new user
//            Portfolio portfolio = new Portfolio();
//            portfolio.setUser(savedUser);
//            portfolio.setTotalAssets(BigDecimal.ZERO);
//            portfolio.setTeamIncome(BigDecimal.ZERO);
//            portfolio.setTodaysIncome(BigDecimal.ZERO);
//            portfolio.setTotalIncome(BigDecimal.ZERO);
//            portfolio.setCurrentBalance(BigDecimal.ZERO);
//            portfolio.setReferralStatus("Referral Found"); // Set Referral Found for the new user
//            portfolio.setBonus(0);
//
//            portfolioRepository.save(portfolio);
//
//            return entityToDto(savedUser);
//        }
//    }

    public UserDto addSingleUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        User inviteCode = userRepository.findByRandom5DiditNumber(Integer.parseInt(userDto.getInviteCode()));

        if (existingUser.isPresent()) {
            return null;
        } else {
            User user = dtoToEntity(userDto);
            User savedUser = userRepository.save(user);

            // Update referral status for all users with the given ID
            int userId = inviteCode.getId();
            List<User> referralUsers = userRepository.findAllById(Collections.singleton(userId));

            for (User existingReferralUser : referralUsers) {
                Portfolio existingReferralPortfolio = portfolioRepository.findByUser(existingReferralUser);
                if (existingReferralPortfolio != null) {
                    String existingReferralStatus = existingReferralPortfolio.getReferralStatus();
                    if (existingReferralStatus == null || existingReferralStatus.isEmpty()) {
                        existingReferralPortfolio.setReferralStatus(userDto.getPhoneNumber());
                    } else {
                        // Append the new referral number to the existing ones
                        existingReferralPortfolio.setReferralStatus(existingReferralStatus + "," + userDto.getPhoneNumber());
                    }
                    portfolioRepository.save(existingReferralPortfolio);
                }
            }

            // Create a blank Portfolio for the new user
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(savedUser);
            portfolio.setTotalAssets(BigDecimal.ZERO);
            portfolio.setTeamIncome(BigDecimal.ZERO);
            portfolio.setTodaysIncome(BigDecimal.ZERO);
            portfolio.setTotalIncome(BigDecimal.ZERO);
            portfolio.setCurrentBalance(BigDecimal.ZERO);
            portfolio.setReferralStatus("Referral Found"); // Set Referral Found for the new user
            portfolio.setBonus(0);

            portfolioRepository.save(portfolio);

            return entityToDto(savedUser);
        }
    }











    private  User dtoToEntity(UserDto userDto){
        User user=User.builder()
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .inviteCode(userDto.getInviteCode()).build();
        return user;
    }
    private UserDto entityToDto(User savedUser){
        UserDto build= UserDto.builder()
                .phoneNumber(savedUser.getPhoneNumber())
                .password(savedUser.getPassword())
                .inviteCode(savedUser.getInviteCode()).build();
        return build;
    }

    // login user
//    public String loginUser(UserDto userDto){
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//        Optional<User> existingPassword = userRepository.findByPassword(userDto.getPassword());
//        System.out.println(existingPassword);
//        if(existingUser.isPresent()&& existingPassword.isPresent()){
//            return "User is present login successful";
//        } else if (existingUser.isPresent() && !existingPassword.isPresent()) {
//            return "incorrect password";
//
//        }else {
//            return "Create account";
//
//        }
//    }
     // Correct but try another
//    public String loginUser(UserDto userDto) {
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//
//        if (existingUser.isPresent() && existingUser.get().getPassword().equals(userDto.getPassword())) {
//            int id = existingUser.get().getId();
//
//
//
//            return "User is present, login successful";
//        } else if (existingUser.isPresent()) {
//            return "Incorrect password";
//        } else {
//            return "Create account";
//        }
//    }

//    public UserPortfolioResponse loginUser(UserDto userDto) {
//        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
//
//        if (existingUser.isPresent() && existingUser.get().getPassword().equals(userDto.getPassword())) {
//
//            int id = existingUser.get().getId();
//            Portfolio portfolio = portfolioRepository.findByUserId(id);
//            System.out.println("+++++"+portfolio);
//            if (portfolio != null) {
//                return new UserPortfolioResponse("User is present, login successful");
//            } else {
//                return new UserPortfolioResponse("User is present, but no portfolio found");
//            }
//        } else if (existingUser.isPresent()) {
//            return new UserPortfolioResponse("Incorrect password");
//        } else {
//            return new UserPortfolioResponse("Create account");
//        }
//    }

    public UserPortfolioResponse loginUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(userDto.getPassword())) {
            int id = existingUser.get().getId();
            Portfolio portfolio = portfolioRepository.findByUserId(id);

            if (portfolio != null) {
                UserPortfolioResponse response = new UserPortfolioResponse("User is present, login successful");
                response.setPortfolio(portfolio); // Set the portfolio in the response
                return response;
            } else {
                return new UserPortfolioResponse("User is present, but no portfolio found");
            }
        } else if (existingUser.isPresent()) {
            return new UserPortfolioResponse("Incorrect password");
        } else {
            return new UserPortfolioResponse("Create an account");
        }
    }

    // Method to retrieve all users from the database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public String getBonus() {
        LocalDate today = LocalDate.now();

        // Check if it's a new day
        if (lastClaimDate == null || !lastClaimDate.equals(today)) {
            lastClaimDate = today;
            bonusClaimed = false; // Reset bonusClaimed flag for a new day
        }

        // Check if bonus has not been claimed yet today
        if (!bonusClaimed) {
            bonusClaimed = true;

            // Check if there is an existing transaction for today
            BonusTransaction existingTransaction = bonusTransactionRepository.findFirstByOrderByIdDesc();

            if (existingTransaction == null) {
                // If no existing transaction, create a new one
                BonusTransaction bonusTransaction = new BonusTransaction();
                bonusTransaction.setTransactionTime(LocalDateTime.now());
                bonusTransaction.setAmount(2);
                bonusTransactionRepository.save(bonusTransaction);

                return "You have received a bonus of 2 rupees.";
            } else {
                // If an existing transaction is found, update the amount
                existingTransaction.setAmount(existingTransaction.getAmount() + 2);
                bonusTransactionRepository.save(existingTransaction);

                return "You have received a bonus of 2 rupees. Total bonus: " + existingTransaction.getAmount() + " rupees.";
            }
        } else {
            return "You have already collected the bonus of 2 rupees today.";
        }
    }
    }







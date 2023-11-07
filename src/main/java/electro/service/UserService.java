package electro.service;

import electro.model.Portfolio;
import electro.model.User;
import electro.model.userDto.UserDto;
import electro.repository.PortfolioRepository;
import electro.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Builder
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

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

    public UserDto addSingleUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (existingUser.isPresent()) {
            return null;
        } else {
            User user = dtoToEntity(userDto);
            User savedUser = userRepository.save(user);

            // Create a blank Portfolio for the user
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(savedUser);
            portfolio.setTotalAssets(BigDecimal.ZERO);
            portfolio.setTotalIncome(BigDecimal.ZERO);
            portfolio.setTodaysIncome(BigDecimal.ZERO);
            portfolio.setTotalExpenses(BigDecimal.ZERO);

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




}

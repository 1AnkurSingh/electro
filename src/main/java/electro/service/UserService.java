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

    public UserDto addSingleUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        User inviteCode = null;

        if (userDto.getInviteCode() != null) {
            inviteCode = userRepository.findByRandom5DiditNumber(Integer.parseInt(userDto.getInviteCode()));
        }

        if (existingUser.isPresent()) {
            return null;
        } else {
            User user = dtoToEntity(userDto);
            User savedUser = userRepository.save(user);

            if (inviteCode != null) {
                int userId = inviteCode.getId();
                List<User> referralUsers = userRepository.findAllById(Collections.singleton(userId));

                for (User existingReferralUser : referralUsers) {
                    Portfolio existingReferralPortfolio = portfolioRepository.findByUser(existingReferralUser);
                    if (existingReferralPortfolio != null) {
                        String existingReferralStatus = existingReferralPortfolio.getReferralStatus();
                        if ("No Referral Found".equals(existingReferralStatus)) {
                            existingReferralPortfolio.setReferralStatus(userDto.getPhoneNumber());
                        } else {
                            existingReferralPortfolio.setReferralStatus(existingReferralStatus + "," + userDto.getPhoneNumber());
                        }
                        portfolioRepository.save(existingReferralPortfolio);
                    }
                }
            }

            Portfolio portfolio = new Portfolio();
            portfolio.setUser(savedUser);
            initializePortfolio(portfolio);
            portfolioRepository.save(portfolio);

            return entityToDto(savedUser);
        }
    }

    private void initializePortfolio(Portfolio portfolio) {
        portfolio.setTotalAssets(BigDecimal.ZERO);
        portfolio.setTeamIncome(BigDecimal.ZERO);
        portfolio.setTodaysIncome(BigDecimal.ZERO);
        portfolio.setTotalIncome(BigDecimal.ZERO);
        portfolio.setCurrentBalance(BigDecimal.ZERO);
        portfolio.setReferralStatus("No Referral Found");
        portfolio.setBonus(0);
    }

    private User dtoToEntity(UserDto userDto) {
        return User.builder()
                .phoneNumber(userDto.getPhoneNumber())
                .password(userDto.getPassword())
                .inviteCode(userDto.getInviteCode())
                .build();
    }

    private UserDto entityToDto(User savedUser) {
        return UserDto.builder()
                .phoneNumber(savedUser.getPhoneNumber())
                .password(savedUser.getPassword())
                .inviteCode(savedUser.getInviteCode())
                .build();
    }

    public String loginUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(userDto.getPassword())) {
            int id = existingUser.get().getId();
            Portfolio portfolio = portfolioRepository.findByUserId(id);

            if (portfolio != null) {
                return "User is present, login successful";
            } else {
                return "User is present, but no portfolio found";
            }
        } else if (existingUser.isPresent()) {
            return "Incorrect password";
        } else {
            return "Create an account";
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String getBonus() {
        LocalDate today = LocalDate.now();

        if (lastClaimDate == null || !lastClaimDate.equals(today)) {
            lastClaimDate = today;
            bonusClaimed = false;
        }

        if (!bonusClaimed) {
            bonusClaimed = true;

            BonusTransaction existingTransaction = bonusTransactionRepository.findFirstByOrderByIdDesc();

            if (existingTransaction == null) {
                BonusTransaction bonusTransaction = new BonusTransaction();
                bonusTransaction.setTransactionTime(LocalDateTime.now());
                bonusTransaction.setAmount(2);
                bonusTransactionRepository.save(bonusTransaction);

                return "You have received a bonus of 2 rupees.";
            } else {
                existingTransaction.setAmount(existingTransaction.getAmount() + 2);
                bonusTransactionRepository.save(existingTransaction);

                return "You have received a bonus of 2 rupees. Total bonus: " + existingTransaction.getAmount() + " rupees.";
            }
        } else {
            return "You have already collected the bonus of 2 rupees today.";
        }
    }
}

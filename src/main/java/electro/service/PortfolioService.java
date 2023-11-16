package electro.service;

import electro.model.Portfolio;
import electro.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    public Portfolio getPortfolioByUserId(int userId) {
        return portfolioRepository.findByUserId(userId);
    }


    public Portfolio updatePortfolio(int id, Portfolio updatedPortfolio) {
        Portfolio existingPortfolio = portfolioRepository.findById(id).orElse(null);

        if (existingPortfolio == null) {
            return null;
        }

        // Update the fields you want to allow being updated
        existingPortfolio.setTotalAssets(updatedPortfolio.getTotalAssets());
        existingPortfolio.setTeamIncome(updatedPortfolio.getTeamIncome());
        existingPortfolio.setTodaysIncome(updatedPortfolio.getTodaysIncome());
        existingPortfolio.setTotalIncome(updatedPortfolio.getTotalIncome());
        existingPortfolio.setCurrentBalance(updatedPortfolio.getCurrentBalance());
        existingPortfolio.setReferralStatus(updatedPortfolio.getReferralStatus());
        existingPortfolio.setBonus(updatedPortfolio.getBonus());

        // Save the updated portfolio
        return portfolioRepository.save(existingPortfolio);
    }

}

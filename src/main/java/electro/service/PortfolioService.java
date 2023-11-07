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

//    public Portfolio addPortfolio(){
//
//    }
}

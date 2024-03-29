package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Repository.CompanyRepository;
import com.example.EStockMarketApplication.Repository.StockPriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StockPriceServiceImpl implements StockPriceService{

    CompanyService companyService;
    StockPriceRepository stockPriceRepository;
    CompanyRepository companyRepository;

    public StockPriceServiceImpl(CompanyService companyService, StockPriceRepository stockPriceRepository, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.stockPriceRepository = stockPriceRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void UpdateStockPrice(long companyCode,StockPrice newPrice) {
        Optional<Company> company=companyRepository.findById(companyCode);
        if(!company.isPresent())
        {
            throw new CompanyNotFound("Enter a Valid Company ID");
        }
        Company c = company.get();
        newPrice.setCompany(c); //just needed to set the company too
        List<StockPrice> stockPrices = c.getStockPrice();
        stockPrices.add(newPrice);
        companyRepository.save(c);
    }
}

package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
import com.example.EStockMarketApplication.Exceptions.LessTurnOverException;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Repository.CompanyRepository;
import com.example.EStockMarketApplication.Repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Override
    public Company RegisterCompany(Company company) {
        if(company.getCompanyTurnover().intValue()<100000000)
        {
            throw new LessTurnOverException("Turnover cannot be less 10CR");
        }
        List<StockPrice> stockPrice = company.getStockPrice();
        stockPriceRepository.saveAll(stockPrice);
        companyRepository.save(company);
        return company;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<Company> companies=companyRepository.findAll();
        return companies;
    }

    @Override
    public Optional<Company> getCompanyByID(Long CompanyCode) {
        Optional<Company> company=companyRepository.findById(CompanyCode);
        if(!company.isPresent())
        {
            throw new RuntimeException("Invalid Company Code");
        }
        return company;
    }

    @Override
    public void UpdateCompany(Long companyCode, Company company)
    {
        Optional<Company> company1=companyRepository.findById(companyCode);
        if(company1.isPresent()) {
            Company c = company1.get();
            c.setCompanyName(company.getCompanyName());
            c.setCompanyCEO(company.getCompanyCEO());
            c.setCompanyTurnover(company.getCompanyTurnover());
            c.setCompanyWebsite(company.getCompanyWebsite());
            c.setStockExchange(company.getStockExchange());
            companyRepository.save(c);
        }
        else
        {
            throw new CompanyNotFound("Invalid Company Code");
        }
    }

    @Override
    public boolean deleteCompany(Long CompanyCode) {
        Optional<Company> company = companyRepository.findById(CompanyCode);
        if (!company.isPresent())
        {
            throw new RuntimeException("Invalid Company Code");
        }
        companyRepository.deleteById(CompanyCode);
        return true;
    }
}
package com.example.EStockMarketApplication.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CompanyCode;
    @Column(nullable = false)
    private String CompanyName;
    @Column(nullable = false)
    private String CompanyCEO;
    @Column(nullable = false)
    private Integer CompanyTurnover;
    @Column(nullable = false)
    private String CompanyWebsite;
    @Column(nullable = false)
    private StockExchange stockExchange;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockPrice> stockPrice=new ArrayList<>();

    @Transient
    private StockPrice LatestStockPrice;

    public StockPrice getLatestStockPrice()
    {
        if(stockPrice!=null && !stockPrice.isEmpty())
        {
            return stockPrice.get(0);
        }
        return null;
    }
}

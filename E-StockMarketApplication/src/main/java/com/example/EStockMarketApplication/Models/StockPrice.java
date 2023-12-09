package com.example.EStockMarketApplication.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private BigDecimal price;
    private LocalDateTime timeStamp;
    @ManyToOne
    @JoinColumn(name = "CompanyCode" ,referencedColumnName = "CompanyCode")
    private Company company;
}

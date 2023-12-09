package com.example.EStockMarketApplication.Controllers;

import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1.0/market/company")
public class CompanyController {
    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/info/{companyCode}")
    public ResponseEntity<Optional<CompanyResponseDTO>> getCompanyInfo(@PathVariable Long companyCode) {
        Optional<CompanyResponseDTO> company = companyService.getCompanyByID(companyCode);
        return ResponseEntity.ok(company);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        List<CompanyResponseDTO> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @PostMapping("/register")
    public ResponseEntity<Company> RegisterCompany(@RequestBody Company company) {
        Company c = companyService.RegisterCompany(company);
        return ResponseEntity.ok(c);
    }

    @PutMapping("/put/{companyCode}")
    public ResponseEntity<String> updateCompany(@PathVariable long companyCode,@RequestBody Company company)
    {
        try
        {
            companyService.UpdateCompany(companyCode,company);
            return ResponseEntity.ok("Company Details Updated Successfully");
        }
        catch (CompanyNotFound e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found with ID:"+companyCode);
        }
    }

    @DeleteMapping("/delete/{companyCode}")
    public ResponseEntity<Boolean> DeleteCompany(@PathVariable long companyCode)
    {
        return ResponseEntity.ok(companyService.deleteCompany(companyCode));
    }
}

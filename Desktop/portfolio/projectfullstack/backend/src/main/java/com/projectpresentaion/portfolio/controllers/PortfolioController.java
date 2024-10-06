package com.projectpresentaion.portfolio.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.projectpresentaion.portfolio.entity.PortfolioEntity;
import com.projectpresentaion.portfolio.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Dear Visitor :) ";
    }

    @GetMapping("/portfolio")
    public List<PortfolioEntity> getPortfolio() {
        return portfolioRepository.findAll();
    }


    @PostMapping("/portfolio")
    public PortfolioEntity addPortfolio(@RequestBody PortfolioEntity portfolio) {
        return portfolioRepository.save(portfolio);
    }


    @PutMapping("/portfolio/{id}")
    public PortfolioEntity updatePortfolio(@PathVariable Long id, @RequestBody PortfolioEntity portfolioDetails) {
        PortfolioEntity portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found with id " + id));

        portfolio.setName(portfolioDetails.getName());
        portfolio.setDescription(portfolioDetails.getDescription());

        return portfolioRepository.save(portfolio);
    }

    @DeleteMapping("/portfolio/{id}")
    public String deletePortfolio(@PathVariable Long id) {
        PortfolioEntity portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio not found with id " + id));

        portfolioRepository.delete(portfolio);
        return "Portfolio deleted with id " + id;
    }




}

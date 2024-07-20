package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class BuyProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long theId, Model theModel) {
        Optional<Product> productToBuy = productRepository.findById(theId);

        if (productToBuy.isPresent()) {    // Check if product is in catalog
            Product product = productToBuy.get();

            if (product.getInv() > 0) {    // Check if product is still in stock
                product.setInv(product.getInv() - 1);   // Decrement stock
                productRepository.save(product);    // Save to product database

                return "confirmbuysuccess";   // Successful purchase
            } else {
                return "confirmbuyfailure";   // Purchase failed: out of stock
            }
        } else {
            return "confirmbuyfailure";  // Purchase failed: product not found
        }
    }
}

package com.example.landscapecalculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller  // Use @Controller for returning HTML pages
public class LandscapingController {

    // Serve the HTML form
    @GetMapping("/")
    public String showForm() {
        return "index";  // Returns the index.html file from the templates folder
    }

    // Handle form submission and calculate the landscaping cost
    @GetMapping("/landscape/calculate")
    @ResponseBody  // Return a plain string as the result (can change to a view later)
    public String calculateLandscapingCost(
            @RequestParam int houseNumber,
            @RequestParam int length,
            @RequestParam int width,
            @RequestParam String grassType,
            @RequestParam int numOfTrees
    ) {
        double baseCost = 1000;
        double area = length * width;
        double extraCost = (area > 5000) ? 500 : 0;

        double grassCost = 0;
        switch (grassType.toLowerCase()) {
            case "fescue":
                grassCost = area * 0.05;
                break;
            case "bentgrass":
                grassCost = area * 0.02;
                break;
            case "campus":
                grassCost = area * 0.01;
                break;
            default:
                return "Invalid grass type!";
        }

        double treeCost = numOfTrees * 100;
        double totalCost = baseCost + extraCost + grassCost + treeCost;

        return String.format("Total cost for house %d is: $%.2f", houseNumber, totalCost);
    }
}

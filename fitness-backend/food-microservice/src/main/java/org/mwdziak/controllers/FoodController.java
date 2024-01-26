package org.mwdziak.controllers;

import lombok.AllArgsConstructor;
import org.mwdziak.dto.FoodDTO;
import org.mwdziak.services.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController {
    private FoodService foodService;


    @GetMapping("/foods")
    public List<FoodDTO> fetchFoodsByKind(@RequestParam("foodKind") String foodKind){
        return foodService.getFoodToSend(foodKind);
    }
    @GetMapping("/kinds")
    public List<String> fetchAllFoodKinds(){
        return foodService.getAllFoodKinds();
    }
}

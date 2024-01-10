package org.mwdziak.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.FoodDTO;
import org.mwdziak.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController {
    private FoodService foodService;


    @GetMapping("/food/kinds")
    public List<FoodDTO> fetchFoods(@RequestParam("foodKind") String foodKind){
        return foodService.getFoodToSend(foodKind);
    }
}

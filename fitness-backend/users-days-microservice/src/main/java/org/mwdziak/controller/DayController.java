package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.service.DayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DayController {
    private final DayService dayService;
    @GetMapping("/days")
    public List<DayDTO> getUserDays(@RequestParam String email) {
        return dayService.getUserDays(email);
    }
}

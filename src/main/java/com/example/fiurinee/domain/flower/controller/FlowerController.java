package com.example.fiurinee.domain.flower.controller;
import com.example.fiurinee.domain.flower.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/main")
public class FlowerController {

    @Autowired
    private FlowerService flowerService;

    @GetMapping("/season")
    public List<Map<String, Object>> getSeasonFlowers() {
        return flowerService.getSeasonFlowers();
    }





}

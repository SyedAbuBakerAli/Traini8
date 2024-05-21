package com.demo.Traini8.controller;

import com.demo.Traini8.dto.CreateTrainingCenterDto;
import com.demo.Traini8.entity.TrainingCenterEntity;
import com.demo.Traini8.service.TrainingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppController {

    @Autowired
    private TrainingCenterService trainingCenterService;

    @PostMapping("/api/trainingcenters")
    public CreateTrainingCenterDto createTrainingCenter(@Valid @RequestBody TrainingCenterEntity trainingCenter) {
        CreateTrainingCenterDto createdTrainingCenter = trainingCenterService.createTrainingCenter(trainingCenter);
        return createdTrainingCenter;
    }

    @GetMapping("/api/getlistofcenter")
    public List<CreateTrainingCenterDto> listOfCenter(){
        return trainingCenterService.getlistOfCenter();
    }
}

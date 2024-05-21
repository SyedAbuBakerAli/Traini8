package com.demo.Traini8.service;

import com.demo.Traini8.dto.CreateTrainingCenterDto;
import com.demo.Traini8.entity.TrainingCenterEntity;

import java.util.List;

public interface TrainingCenterService {

    public CreateTrainingCenterDto createTrainingCenter(TrainingCenterEntity trainingCenter);

    public List<CreateTrainingCenterDto> getlistOfCenter();
}

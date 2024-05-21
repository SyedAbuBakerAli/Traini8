package com.demo.Traini8.repository;

import com.demo.Traini8.entity.TrainingCenterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenterEntity,Integer> {
}

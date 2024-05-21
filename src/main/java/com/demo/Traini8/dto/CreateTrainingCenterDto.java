package com.demo.Traini8.dto;

import com.demo.Traini8.entity.AddressEntity;
import com.demo.Traini8.entity.ListOfCourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrainingCenterDto {
   private String CenterName;
   private String CenterCode;
   private AddressEntity address;
   private int studentCapacity;
   List<String> coursesOffered;

   @CreationTimestamp
   private LocalDateTime CreatedOn;
   private String contactEmail;
   private String contactPhone;
}

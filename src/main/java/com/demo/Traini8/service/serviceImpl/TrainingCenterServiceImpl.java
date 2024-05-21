package com.demo.Traini8.service.serviceImpl;

import com.demo.Traini8.dto.CreateTrainingCenterDto;
import com.demo.Traini8.entity.ListOfCourseEntity;
import com.demo.Traini8.entity.TrainingCenterEntity;
import com.demo.Traini8.exceptionHandler.ValidationException;
import com.demo.Traini8.repository.TrainingCenterRepository;
import com.demo.Traini8.service.TrainingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TrainingCenterServiceImpl implements TrainingCenterService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");

    @Autowired
    private TrainingCenterRepository trainingCenterRepository;


    @Override
    public CreateTrainingCenterDto createTrainingCenter(TrainingCenterEntity trainingCenter) {
        if (trainingCenter.getCenterName() == null || trainingCenter.getCenterName().length() > 40) {
            throw new ValidationException("Center name must be less than 40 characters");
        }

        if (trainingCenter.getCenterCode() == null || trainingCenter.getCenterCode().length() != 12 || !trainingCenter.getCenterCode().matches("^[a-zA-Z0-9]*$")) {
            throw new ValidationException("Center code must be exactly 12 alphanumeric characters");
        }
        if (trainingCenter.getContactEmail() != null && !EMAIL_PATTERN.matcher(trainingCenter.getContactEmail()).matches()) {
            throw new ValidationException("Invalid email format");
        }

        if (trainingCenter.getContactPhone() == null || !PHONE_PATTERN.matcher(trainingCenter.getContactPhone()).matches()) {
            throw new ValidationException("Contact phone must be a 10 digit number");
        }

         TrainingCenterEntity trainingCenterEntity = new TrainingCenterEntity();
         trainingCenterEntity.setCenterName(trainingCenter.getCenterName());
         trainingCenterEntity.setCenterCode(trainingCenter.getCenterCode());
         trainingCenterEntity.setAddress(trainingCenter.getAddress());
         trainingCenterEntity.setContactEmail(trainingCenter.getContactEmail());
         trainingCenterEntity.setContactPhone(trainingCenter.getContactPhone());
         trainingCenterEntity.setStudentCapacity(trainingCenter.getStudentCapacity());
         List<ListOfCourseEntity> listOfCourseEntities = new ArrayList<>();
         for(ListOfCourseEntity listOfCourse: trainingCenter.getCoursesOffered()){
             ListOfCourseEntity listOfCourseEntity = new ListOfCourseEntity();
             listOfCourseEntity.setName(listOfCourse.getName());
             listOfCourseEntity.setTrainingCenter(trainingCenterEntity);
             System.out.println("courseList: "+trainingCenterEntity);
             listOfCourseEntities.add(listOfCourseEntity);
         }


         trainingCenterEntity.setCoursesOffered(listOfCourseEntities);
        CreateTrainingCenterDto createTrainingCenter = new CreateTrainingCenterDto();
        List<ListOfCourseEntity> listOfcourse = new ArrayList<>();
        createTrainingCenter.setCenterName(trainingCenter.getCenterName());
        createTrainingCenter.setCenterCode(trainingCenter.getCenterCode());
        createTrainingCenter.setAddress(trainingCenter.getAddress());
        createTrainingCenter.setContactEmail(trainingCenter.getContactEmail());
        createTrainingCenter.setContactPhone(trainingCenter.getContactPhone());
        createTrainingCenter.setStudentCapacity(trainingCenter.getStudentCapacity());
        createTrainingCenter.setCreatedOn(LocalDateTime.now());
        List<String> courseList = new ArrayList<>();
        for(ListOfCourseEntity course: trainingCenter.getCoursesOffered()){
            ListOfCourseEntity courseEntity = new ListOfCourseEntity();
            courseEntity.setTrainingCenter(trainingCenter);
            courseEntity.setName(course.getName());
            courseList.add(course.getName());
        }
        createTrainingCenter.setCoursesOffered(courseList);



       trainingCenterRepository.save(trainingCenterEntity);
       return createTrainingCenter;






    }

    @Override
    public List<CreateTrainingCenterDto> getlistOfCenter() {
        List<TrainingCenterEntity> centerEntityList =  trainingCenterRepository.findAll();
        List<CreateTrainingCenterDto> trainingCenterDtos = new ArrayList<>();
        for(TrainingCenterEntity trainingCenter: centerEntityList){
            CreateTrainingCenterDto trainingCenterDto = new CreateTrainingCenterDto();
            List<String> listOfCourse = new ArrayList<>();
            trainingCenterDto.setCenterName(trainingCenter.getCenterName());
            trainingCenterDto.setCenterCode(trainingCenter.getCenterCode());
            trainingCenterDto.setCreatedOn(trainingCenter.getCreatedOn());
            trainingCenterDto.setAddress(trainingCenter.getAddress());
            trainingCenterDto.setStudentCapacity(trainingCenter.getStudentCapacity());
            trainingCenterDto.setContactEmail(trainingCenter.getContactEmail());
            trainingCenterDto.setContactPhone(trainingCenter.getContactPhone());
            List<ListOfCourseEntity> listOfCourseEntities = trainingCenter.getCoursesOffered();

            for(ListOfCourseEntity course: listOfCourseEntities){
                listOfCourse.add(course.getName());

            }

            trainingCenterDto.setCoursesOffered(listOfCourse);

            trainingCenterDtos.add(trainingCenterDto);
        }

      return trainingCenterDtos;

    }
}

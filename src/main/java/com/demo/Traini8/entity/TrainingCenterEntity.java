package com.demo.Traini8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training_center")
@EntityListeners(AuditingEntityListener.class)
public class TrainingCenterEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Size(max = 40)
    private String centerName;

    @NotBlank
    @Size(min = 12, max = 12)
    private String centerCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    private Integer studentCapacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainingCenter", cascade = CascadeType.ALL)
    private List<ListOfCourseEntity> coursesOffered;



    @CreationTimestamp
    private LocalDateTime createdOn;

    @Email
    private String contactEmail;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String contactPhone;
}

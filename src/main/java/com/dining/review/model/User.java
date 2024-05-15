package com.dining.review.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private String city;
    private String state;
    private Integer zipcode;
    @Column(columnDefinition = "BOOLEAN", name = "peanut_allergy")
    private Boolean peanutAllergy;
    @Column(columnDefinition = "BOOLEAN", name = "egg_allergy")
    private Boolean eggAllergy;
    @Column(columnDefinition = "BOOLEAN", name = "dairy_allergy")
    private Boolean dairyAllergy;

}

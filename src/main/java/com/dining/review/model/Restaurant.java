package com.dining.review.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "zip_code")
    private Integer zipCode;
    @Column(name = "overall_score")
    private Float overallScore;
    @Column(name = "peanut_score")
    private Float peanutScore;
    @Column(name = "egg_score")
    private Float eggScore;
    @Column(name = "dairy_score")
    private Float dairyScore;
}

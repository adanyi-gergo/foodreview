package com.dining.review.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Entity
@Getter
@Setter
@ToString
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "peanut_score")
    private Float peanutScore;
    @Column(name = "egg_score")
    private Float eggScore;
    @Column(name = "dairy_score")
    private Float dairyScore;
    private String commentary;
    @Column(name = "review_status")
    @Enumerated(EnumType.STRING)
    private ReviewStatus reviewStatus;
}

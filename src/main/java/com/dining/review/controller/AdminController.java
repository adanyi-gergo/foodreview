package com.dining.review.controller;

import com.dining.review.model.AdminReview;
import com.dining.review.model.DiningReview;
import com.dining.review.model.Restaurant;
import com.dining.review.model.ReviewStatus;
import com.dining.review.repository.DiningReviewRepository;
import com.dining.review.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository){
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/reviews")
    public String getPendingReviews(Model model){
        List<DiningReview> byReviewStatus = diningReviewRepository.findByReviewStatus(ReviewStatus.PENDING);
        model.addAttribute("reviews", byReviewStatus);
        return "pending_reviews";
//        return diningReviewRepository.findByReviewStatus(ReviewStatus.PENDING);
    }

    @PostMapping("/review/{id}")
    public String updateReviewStatus(@PathVariable("id") Long id, @RequestParam("status") boolean status){
        System.out.println(status);
        System.out.println(id);
        Optional<DiningReview> reviewToUpdateOptional = diningReviewRepository.findById(id);
        if(reviewToUpdateOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DiningReview reviewToUpdate = reviewToUpdateOptional.get();
        if(status){
            reviewToUpdate.setReviewStatus(ReviewStatus.ACCEPTED);
            updateRestaurantScore(reviewToUpdate);
        } else{
            reviewToUpdate.setReviewStatus(ReviewStatus.DENIED);
        }

        diningReviewRepository.save(reviewToUpdate);
        return "redirect:/admin/reviews";
    }

    @GetMapping("/review/{id}")
    public String getReviewDetails(@PathVariable("id") Long id, Model model) {
        // Retrieve the review by ID from the database
        DiningReview review = diningReviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // Add the review object to the model
        model.addAttribute("review", review);

        // Return the name of the Thymeleaf template
        return "review_by_id";
    }

    private void updateRestaurantScore(DiningReview diningReview){
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(diningReview.getRestaurantId());
        if(restaurantOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found!");
        }

        int numberOfReviews = diningReviewRepository.findByReviewStatus(ReviewStatus.ACCEPTED).size();
        Restaurant restaurant = restaurantOptional.get();
        restaurant.setEggScore((restaurant.getEggScore() + diningReview.getEggScore()) / ++numberOfReviews);
        restaurant.setDairyScore((restaurant.getDairyScore() + diningReview.getDairyScore()) / ++numberOfReviews);
        restaurant.setPeanutScore((restaurant.getPeanutScore() + diningReview.getPeanutScore()) / ++numberOfReviews);

        float overallScore = (restaurant.getPeanutScore() + restaurant.getEggScore() + restaurant.getDairyScore()) / 3;
        restaurant.setEggScore((restaurant.getOverallScore() + overallScore) / ++numberOfReviews);

        restaurantRepository.save(restaurant);
    }
}

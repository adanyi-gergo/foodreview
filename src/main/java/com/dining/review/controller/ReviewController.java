package com.dining.review.controller;

import com.dining.review.model.DiningReview;
import com.dining.review.model.Restaurant;
import com.dining.review.model.ReviewStatus;
import com.dining.review.model.User;
import com.dining.review.repository.DiningReviewRepository;
import com.dining.review.repository.RestaurantRepository;
import com.dining.review.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private final DiningReviewRepository diningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(DiningReviewRepository diningReviewRepository, UserRepository userRepository, RestaurantRepository restaurantRepository){
        this.diningReviewRepository = diningReviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/")
    public String addReview(@ModelAttribute DiningReview diningReview){
        diningReview.setReviewStatus(ReviewStatus.PENDING);
        System.out.println(diningReview.toString());
        //validateUserReview(diningReview);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(diningReview.getRestaurantId());
        if(optionalRestaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        diningReviewRepository.save(diningReview);
        return "redirect:/restaurants/";
    }

    @GetMapping("/")
    public String getReview(@RequestParam("id") Long id, Model model){
        System.out.println(id);
        model.addAttribute(id);
        model.addAttribute("diningReview", new DiningReview());
        return "submit_review";
    }

    private void validateUserReview(DiningReview review) {
        if (ObjectUtils.isEmpty(review.getUserName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(review.getRestaurantId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(review.getPeanutScore()) &&
                ObjectUtils.isEmpty(review.getDairyScore()) &&
                ObjectUtils.isEmpty(review.getEggScore())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = userRepository.findByUserName(review.getUserName());
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}

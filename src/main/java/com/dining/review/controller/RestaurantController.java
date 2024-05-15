package com.dining.review.controller;

import com.dining.review.model.DiningReview;
import com.dining.review.model.Restaurant;
import com.dining.review.repository.DiningReviewRepository;
import com.dining.review.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final DiningReviewRepository diningReviewRepository;

    public RestaurantController(RestaurantRepository restaurantRepository, DiningReviewRepository diningReviewRepository){
        this.restaurantRepository = restaurantRepository;
        this.diningReviewRepository = diningReviewRepository;
    }

    @PostMapping("/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant){
        //validálni az éttermet néve és zip-code alapján
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/")
    public String getRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/{id}")
    public String getRestaurantById(@PathVariable("id") Long id, Model model) {
        System.out.println(id);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Restaurant restaurant = restaurantOptional.get();
        List<DiningReview> reviews = diningReviewRepository.findByRestaurantId(id);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("reviews", reviews);
        return "restaurant";  // Assuming you have a template to display individual restaurant details
    }
}

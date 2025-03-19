package vttp2022.csf.assessment.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.RestaurantService;
import vttp2022.csf.assessment.server.utils.ReplacerAndDeplacer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class CuisineController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/cuisines")
    public ResponseEntity<Object> getCuisineCat() {
        List<String> cuisines = restaurantService.getCuisines();
        List<String> newCuisines = ReplacerAndDeplacer.replace(cuisines);

        return ResponseEntity.ok().body(newCuisines);
        
    }

    @GetMapping("/{cuisine}/restaurant")
    public ResponseEntity<Object> getRestaurantsByCuisine(@PathVariable String cuisine) {

        String newCuisine = ReplacerAndDeplacer.deplace(cuisine);

        List<String> restaurants = restaurantService.getRestaurantsByCuisine(newCuisine);


        return ResponseEntity.ok().body(restaurants);
        
    }

    @GetMapping("/restaurant/{restaurantName}")
    public ResponseEntity<Object> getRestaurant(@PathVariable String restaurantName){

        System.out.println("fetching Restaurant Name");

        Optional<Restaurant> oRestaurant = restaurantService.getRestaurant(restaurantName);

        if (oRestaurant.isPresent()){
            Restaurant restaurant = oRestaurant.get();
            System.out.println(restaurant.toString());
            return ResponseEntity.ok().body(restaurant);
        } else {
            return ResponseEntity.badRequest().body("Cannot find the restaurant");
        }

    }
  
}

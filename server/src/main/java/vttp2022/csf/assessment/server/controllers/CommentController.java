package vttp2022.csf.assessment.server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.services.RestaurantService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/comment")
    public ResponseEntity<Object> postComments(@RequestBody Comment comment){
        System.out.println("Comment reached");

        Map<String, String> response = new HashMap<>();

        try {

        restaurantService.addComment(comment);
        response.put("message", "Comment posted");
        System.out.println(response);
        
        return ResponseEntity.ok().body(response);


        } catch (Exception e){
            response.put("message",e.getMessage());
            return ResponseEntity.badRequest().body(response);

        }

        
    }
    
}

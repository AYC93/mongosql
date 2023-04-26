package mongosqlprac.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mongosqlprac.app.model.Reviews;
import mongosqlprac.app.service.MongoGamesService;

@RestController
@RequestMapping
public class MongoRestController {

    @Autowired
    MongoGamesService mongoGSvc;
    
    // works, need to put in document form
    // http://localhost:8080/insert
    @PostMapping(path="/insert", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> createNewReviews(@ModelAttribute Reviews reviews){
        
        String id = mongoGSvc.createDocument(reviews);
        System.out.println(id);

        return ResponseEntity.status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(reviews.toJson().toString());
    }
}

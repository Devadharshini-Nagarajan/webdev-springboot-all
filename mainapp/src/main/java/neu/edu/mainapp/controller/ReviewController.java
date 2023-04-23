package neu.edu.mainapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.mainapp.dto.ReviewDTO;
import neu.edu.mainapp.entity.Review;
import neu.edu.mainapp.service.ReviewService;

@RestController
@RequestMapping("/main")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	
	@RequestMapping(value= "/getReviews", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Review>> getAllReviews() {
		ArrayList<Review> users = reviewService.getAllReviews();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getReviewsByProductId/{productid}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Review>> getProdcutReviewsById(@PathVariable String productid) {
		ArrayList<Review> users = reviewService.getProdcutReviewsById(productid);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/addReview", method= RequestMethod.POST)
	public ResponseEntity<Review> insertReview(@RequestBody ReviewDTO reviewDTO) {
		Review purchaseProducts = reviewService.insertReview(reviewDTO);
		return new ResponseEntity<>(purchaseProducts, HttpStatus.OK);
	}
}

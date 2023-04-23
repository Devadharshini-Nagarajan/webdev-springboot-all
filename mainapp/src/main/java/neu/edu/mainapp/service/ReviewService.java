package neu.edu.mainapp.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.mainapp.dto.ReviewDTO;
import neu.edu.mainapp.entity.Review;
import neu.edu.mainapp.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	public ArrayList<Review> getAllReviews() {
		ArrayList<Review> reviews = new ArrayList<>();
		reviewRepository.findAll().forEach(reviews::add);
		return reviews;	
	}
	
	public ArrayList<Review> getProdcutReviewsById(String productid) {
		ArrayList<Review> reviews = new ArrayList<>();
		reviewRepository.findByProductid(productid).forEach(reviews::add);
		return reviews;	
	}
	
	public String getReviewId() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	
	public Review insertReview(ReviewDTO reviewDTO) {
		Review rev = new Review();
		rev.setReviewid(getReviewId());
		rev.setReviewdate(reviewDTO.getReviewdate());
		rev.setModifieddate(reviewDTO.getModifieddate());
		rev.setHeadline(reviewDTO.getHeadline());
		rev.setContent(reviewDTO.getContent());
		rev.setRating(reviewDTO.getRating());
		rev.setUsername(reviewDTO.getUsername());
		rev.setProductid(reviewDTO.getProductid());
		rev.setPurchaseid(reviewDTO.getPurchaseid());
		
		Review savedReview = null;
		
		try {
			savedReview = reviewRepository.save(rev);
		} catch(Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedReview;
	}
	
}

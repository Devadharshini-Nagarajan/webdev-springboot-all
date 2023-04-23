package neu.edu.mainapp.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.mainapp.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	ArrayList<Review> findByReviewid(String role);
	ArrayList<Review> findByProductid(String productid);
	Optional<Review> findByUsername(String username);
}
package neu.edu.eskincare.repository;

import org.springframework.stereotype.Repository;



import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import neu.edu.eskincare.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	ArrayList<Product> findByCompany(String company);
	ArrayList<Product> findByCategory(String category);
	ArrayList<Product> findByBrand(String brand);
	Optional<Product> findByProductid(String productid);
	Optional<Product> findByName(String name);

}
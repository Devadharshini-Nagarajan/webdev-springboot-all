package neu.edu.mainapp.repository;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.mainapp.entity.PurchaseProduct;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Integer>{
	ArrayList<PurchaseProduct> findByProductid(String role);
	Optional<PurchaseProduct> findByUsername(String username);
}

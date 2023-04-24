package neu.edu.eskincare.repository;

import org.springframework.stereotype.Repository;



import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import neu.edu.eskincare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	ArrayList<User> findByRole(String role);
	ArrayList<User> findByEmail(String email);
	ArrayList<User> findByCompany(String company);
	Optional<User> findByUsername(String username);

}
package neu.edu.mainapp.repository;

import java.util.ArrayList;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neu.edu.mainapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	ArrayList<User> findByRole(String role);
	ArrayList<User> findByEmail(String email);
	Optional<User> findByUsername(String username);

}

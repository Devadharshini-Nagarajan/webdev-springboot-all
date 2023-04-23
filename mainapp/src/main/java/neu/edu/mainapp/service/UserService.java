package neu.edu.mainapp.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import neu.edu.mainapp.dto.UserDTO;
import neu.edu.mainapp.entity.User;
import neu.edu.mainapp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	static String companyName = "sephora";
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;	
	}

	
	public ArrayList<User> getAllUsersByRole(String role) {	
		ArrayList<User> users = new ArrayList<>();
		if(role == null) {
			userRepository.findAll().forEach(users::add);
		} else {
			userRepository.findByRole(role).forEach(users::add);
		}
		
		return users;
	}
	
	
	
	public User getUserByUsername(String username) {	
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			return user.get();
		} else {
			return new User();
		}
	}

	
	public String getUserId() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public User insertUser(UserDTO userDTO) {
		User user = new User();
		user.setUserid(getUserId());
		user.setUsername(userDTO.getUsername());
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setRole(userDTO.getRole());
		user.setEmail(userDTO.getEmail());
		user.setPhonenumber(userDTO.getPhonenumber());
		user.setPassword(userDTO.getPassword());
		user.setAddress1(userDTO.getAddress1());
		user.setAddress2(userDTO.getAddress2());
		user.setCity(userDTO.getCity());
		user.setProvince(userDTO.getProvince());
		user.setZipcode(userDTO.getZipcode());
		user.setCreatedat(userDTO.getCreatedat());
		
		User savedUser = null;
		
		try {
			savedUser = userRepository.save(user);
		} catch(Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedUser;
	}

	public boolean deleteUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		}else {
			return false;
		}
	}
	
	public User updateUser(UserDTO userModel) {
		Optional<User> user = userRepository.findByUsername(userModel.getUsername());
		if(user.isPresent()) {
			User _user = user.get();
			_user.setUsername(userModel.getUsername());
			_user.setFirstname(userModel.getFirstname());
			_user.setLastname(userModel.getLastname());
			_user.setRole(userModel.getRole());
			_user.setEmail(userModel.getEmail());
			_user.setPhonenumber(userModel.getPhonenumber());
			_user.setPassword(userModel.getPassword());
			_user.setAddress1(userModel.getAddress1());
			_user.setAddress2(userModel.getAddress2());
			_user.setCity(userModel.getCity());
			_user.setProvince(userModel.getProvince());
			_user.setZipcode(userModel.getZipcode());
			_user.setCreatedat(userModel.getCreatedat());
			
			_user = userRepository.save(_user);
			return _user;
		}
		return null;
		
	}
}

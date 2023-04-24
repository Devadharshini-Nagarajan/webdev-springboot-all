package neu.edu.eskincare.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import neu.edu.eskincare.dto.UserDTO;
import neu.edu.eskincare.entity.User;
import neu.edu.eskincare.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	static String companyName = "eskincare";
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepository.findByCompany(companyName).forEach(users::add);
		return users;	
	}
	
	public static ArrayList<User> filterPeopleByCompany(ArrayList<User> users) {
        ArrayList<User> filteredPeople = new ArrayList<>();
        for (User person : users) {
            if (person.getCompany().equals(companyName)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }
	
	public ArrayList<User> getAllUsersByCompany(String company) {	
		ArrayList<User> users = new ArrayList<>();
		userRepository.findByCompany(companyName).forEach(users::add);
		return users;
	}
	
	public ArrayList<User> getAllUsersByRole(String role) {	
		ArrayList<User> users = new ArrayList<>();
		if(role == null) {
			userRepository.findByCompany(companyName).forEach(users::add);
		} else {
			userRepository.findByRole(role).forEach(users::add);
		}
		
		users = filterPeopleByCompany(users);
		
		return users;
	}
	
	
	
	public User getUserByUsername(String username) {	
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent() && user.get().getCompany().equals(companyName)) {
			return user.get();
		} else {
			return new User();
		}
	}
	
//	public int getNextUserId() {
//		ArrayList<User> users = getAllUsers();	
//		int currentSize = users.size();
//		int finalId;
//		if(currentSize == 0) {
//			finalId = 1;
//		} else {
//			finalId = users.get(users.size()-1).getUserid()+1;
//		}
//		return finalId;
//	}
	
	public String getUserId() {
		String uuid = UUID.randomUUID().toString();
		uuid = "sephora-" + uuid;
		return uuid;
	}
	
	public User insertUser(UserDTO userDTO) {
		System.out.println(userDTO);
		System.out.println("innnn");
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
		user.setCompany(userDTO.getCompany());
		
		User savedUser = null;
		
		try {
			savedUser = userRepository.save(user);
			System.out.println(savedUser);
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
		if(user.isPresent() && user.get().getCompany().equals(companyName)) {
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
			_user.setCompany(userModel.getCompany());
			
			_user = userRepository.save(_user);
			return _user;
		}
		return null;
		
	}
}

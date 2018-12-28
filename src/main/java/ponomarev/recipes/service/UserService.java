package ponomarev.recipes.service;

import ponomarev.recipes.entity.RoleEntity;
import ponomarev.recipes.entity.UserEntity;
import ponomarev.recipes.model.Role;
import ponomarev.recipes.model.User;
import ponomarev.recipes.repository.RoleRepository;
import ponomarev.recipes.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	public UserService(UserRepository userRepository,
	                   RoleRepository roleRepository,
	                   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public UserEntity findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity newUser = new UserEntity(user);
		userRepository.save(newUser);
		return user;
	}


	public List<User> getAllUsers() {
		List<User> allUsers =new ArrayList<User>();
		for (UserEntity user:this.userRepository.findAll()) {
			allUsers.add(new User(user));
		}
		return allUsers;
	}

	public void deleteUser(String selectedUser) {
		userRepository.delete(findUserByEmail(selectedUser));
		userRepository.flush();
	}

	public void blockUser(String selectedUser) {
		UserEntity blockUser = findUserByEmail(selectedUser);
		blockUser.setActive(0);
		userRepository.save(blockUser);
	}

	public void unblockUser(String selectedUser) {
		UserEntity unblockUser = findUserByEmail(selectedUser);
		unblockUser.setActive(1);
		userRepository.save(unblockUser);
	}

	public boolean isAuth(Authentication auth) {
		if (this.findUserByEmail(auth.getName()) == null) {
			return true;
		}
		return false;
	}

	public boolean isBlock(Authentication auth) {
		if (this.findUserByEmail(auth.getName()).getActive() == 0) {
			return true;
		}
		return false;
	}


}
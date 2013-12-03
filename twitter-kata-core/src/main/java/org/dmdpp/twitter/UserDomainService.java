package org.dmdpp.twitter;

import java.util.Set;

public class UserDomainService {

	private UserRepository userRepository;

	public UserDomainService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void newFollowRelation(String userName, String userNameToFollow) {
		User user = userRepository.find(userName);
		User userToFollow = userRepository.find(userNameToFollow);
		
		user.follow(userToFollow);
		
		userRepository.save(user);
	}
	
	public void register(String name) {
		try {
			userRepository.find(name);
			throw new UserYetExitsException(name);
			
		} catch (UserNotExitsException e) {
			userRepository.save(new User(name));
		}
	}
	
	public User getBy(String name) {
		return userRepository.find(name);
	}
	
	public Set<User> followedBy(String name) {
		return userRepository.find(name).followins();
	}
	
}

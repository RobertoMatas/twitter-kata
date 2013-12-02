package org.dmdpp.twitter;

import java.util.Set;

public class UserDomainService {

	private UserRepository userRepository;

	public UserDomainService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void newFollowRelation(String user, String userToFollow) {
		User u = userRepository.find(user);
		User utf = userRepository.find(userToFollow);
		
		u.follow(utf);
		
		userRepository.commit();
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

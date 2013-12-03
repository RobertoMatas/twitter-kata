package org.dmdpp.twitter;

import java.util.Set;

public class UserDomainService {

	private UserRepository userRepository;

	public UserDomainService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void newFollowRelation(String userName, String userNameToFollow) throws UserNotExitsException {
		User user = userRepository.find(userName);
		User userToFollow = userRepository.find(userNameToFollow);

		user.follow(userToFollow);

		userRepository.save(user);
	}

	public void register(String name) throws UserYetExitsException {
		try {
			userRepository.find(name);
			throw new UserYetExitsException(name);

		} catch (UserNotExitsException e) {
			userRepository.save(new User(name));
		}
	}

	public User getBy(String name) throws UserNotExitsException {
		return userRepository.find(name);
	}

	public Set<User> followedBy(String name) throws UserNotExitsException {
		return userRepository.find(name).following();
	}

}

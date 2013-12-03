package org.dmdpp.twitter;

import java.util.HashSet;
import java.util.Set;

public class Users implements UserRepository {

	Set<User> users = new HashSet<User>();

	@Override
	public void save(User user) {
		users.add(user);

	}

	@Override
	public boolean hasUser(String name) {
		return users.contains(new User(name));
	}

	@Override
	public User find(String name) {
		for (User user : users) {
			if (user.name.equals(name))
				return user;
		}
		throw new UserNotExitsException(name);
	}
}

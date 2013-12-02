package org.dmdpp.twitter;

import java.util.ArrayList;
import java.util.List;

public class Users implements UserRepository {

	List<User> users = new ArrayList<User>();

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

	@Override
	public void commit() {	}

}

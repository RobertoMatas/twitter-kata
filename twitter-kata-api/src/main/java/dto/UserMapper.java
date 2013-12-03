package dto;

import java.util.ArrayList;
import java.util.Collection;

import api.RestUriBuilder;

public class UserMapper {
	
	private final RestUriBuilder uriBuilder;

	public UserMapper(RestUriBuilder uriBuilder) {
		this.uriBuilder = uriBuilder;
	}

	public User map(org.dmdpp.twitter.User user) {
		return new User(user.name(), uriBuilder);
	}

	public Collection<User> map(Collection<org.dmdpp.twitter.User> users) {
		Collection<User> usersTo = new ArrayList<User>(users.size());
		for (org.dmdpp.twitter.User user : users) {
			usersTo.add(map(user));
		}
		return usersTo;
	}
}

package dto;

import java.util.ArrayList;
import java.util.Collection;

public class UserMapper {

	public User map(org.dmdpp.twitter.User user) {
		return new User(user.name());
	}

	public Collection<User> map(Collection<org.dmdpp.twitter.User> users) {
		Collection<User> usersTo = new ArrayList<User>(users.size());
		for (org.dmdpp.twitter.User user : users) {
			usersTo.add(map(user));
		}
		return usersTo;
	}
}

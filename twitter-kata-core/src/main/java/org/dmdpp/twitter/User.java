package org.dmdpp.twitter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3201256639969196051L;
	
	String name;
	private Set<User> following;

	public User(String name) {
		this.name = name;
		this.following = new HashSet<User>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void follow(User userToFollow) {
		following.add(userToFollow);
		
	}

	public boolean followsUser(String name) {
		return following.contains(new User(name));
	}

	public Set<User> following() {
		return this.following;
	}
	
	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}

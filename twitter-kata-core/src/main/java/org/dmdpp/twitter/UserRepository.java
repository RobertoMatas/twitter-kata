package org.dmdpp.twitter;

public interface UserRepository {

	boolean hasUser(String name);

	User find(String name);

	void save(User user);
	
	void commit();

}

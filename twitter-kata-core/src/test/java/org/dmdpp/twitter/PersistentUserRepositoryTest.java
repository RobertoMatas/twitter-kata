package org.dmdpp.twitter;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistentUserRepositoryTest {

	private PersistentUserRepository userRepository;

	@Before
	public void setUp() {
		userRepository = new PersistentUserRepository("test-users.ser");
		User user = new User("@rober");		
		User user2 = new User("@pasku1");
		userRepository.save(user);
		userRepository.save(user2);
		user.follow(user2);
	}

	@Test
	public void userPersisted() {		
		assertTrue(userRepository.hasUser("@rober"));
	}
	
	@Test
	public void userFollowsPersisted() {		
		assertTrue(userRepository.find("@rober").followsUser("@pasku1"));
	}
	
	@After
	public void after() {
		userRepository.deleteAll();
	}

}

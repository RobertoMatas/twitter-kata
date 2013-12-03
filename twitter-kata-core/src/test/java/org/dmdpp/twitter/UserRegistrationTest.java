package org.dmdpp.twitter;

import static org.dmdpp.twitter.Fixtures.repositoryWithOneUser;
import static org.dmdpp.twitter.Fixtures.repositoryWithOneUserAndTwoFollowings;
import static org.dmdpp.twitter.Fixtures.repositoryWithTwoUser;
import static org.dmdpp.twitter.Fixtures.userWithTwoFollowings;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class UserRegistrationTest {

	private UserRepository userRepository;

	@Test
	public void registerUserThatNotExists() {
		userRepository = new Users();
		UserDomainService service = new UserDomainService(userRepository);

		service.register("@pasku1");

		assertTrue(userRepository.hasUser("@pasku1"));
	}

	@Test(expected = UserYetExitsException.class)
	public void registerUserThatYetExistsThrowException() {
		UserDomainService service = new UserDomainService(
				repositoryWithOneUser());

		service.register("@pasku1");
	}

	@Test
	public void guessUserFollowing() {
		User user = userWithTwoFollowings();

		assertTrue(user.followins().size() == 2);
	}

	@Test
	public void canGetUsersFollowedByOtherUser() {
		UserDomainService service = new UserDomainService(
				repositoryWithOneUserAndTwoFollowings());

		assertTrue(service.followedBy("@nick").containsAll(
				Arrays.asList(new User("@uno"), new User("@dos"))));
	}

	@Test
	public void existingUserFollowAnotherExistingUser() {
		userRepository = repositoryWithTwoUser();
		UserDomainService service = new UserDomainService(userRepository);

		service.newFollowRelation("@rober", "@pasku1");

		assertTrue(userRepository.find("@rober").followsUser("@pasku1"));
	}

	@Test(expected = UserNotExitsException.class)
	public void existingUserFollowANotExistingUser() {
		UserDomainService service = new UserDomainService(
				repositoryWithTwoUser());

		service.newFollowRelation("@pasku1", "@no-an-user");
	}

}

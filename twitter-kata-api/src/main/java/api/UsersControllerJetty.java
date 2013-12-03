package api;

import static spark.Spark.get;
import static spark.Spark.post;

import org.dmdpp.twitter.Factory;
import org.dmdpp.twitter.UserDomainService;
import org.dmdpp.twitter.UserNotExitsException;
import org.dmdpp.twitter.UserYetExitsException;

import spark.Request;
import spark.Response;

import com.google.gson.Gson;

import dto.Message;
import dto.User;
import dto.UserMapper;

public class UsersControllerJetty {

	public static void main(String args[]) {
		final UserDomainService service = Factory.userDomainService();
		final UserMapper userMapper = new UserMapper();
		final Gson gson = new Gson();

		get(new JsonTransformerRoute("/users/:name/following") {
			@Override
			public Object handle(Request request, Response response) {
				String name = request.params(":name");
				response.type("application/json");
				try {
					return userMapper.map(service.followedBy(name));

				} catch (UserNotExitsException e) {
					return userNotExists(response, e);
				}
			}
		});

		post(new JsonTransformerRoute("/users/:name/following") {

			@Override
			public Object handle(Request request, Response response) {
				User userToFollow = gson.fromJson(request.body(), User.class);
				User user = new User(request.params(":name"));
				try {
					service.newFollowRelation(user.name, userToFollow.name);
					response.status(201);
					response.header("Location", user.followingLink());
					return userMapper.map(service.followedBy(user.name));

				} catch (UserNotExitsException e) {
					return userNotExists(response, e);
				}
			}
		});

		get(new JsonTransformerRoute("/users/:name") {
			@Override
			public Object handle(Request request, Response response) {
				String name = request.params(":name");
				response.type("application/json");
				try {
					User user = userMapper.map(service.getBy(name));
					return user;

				} catch (UserNotExitsException e) {
					return userNotExists(response, e);
				}
			}
		});

		post(new JsonTransformerRoute("/users") {

			@Override
			public Object handle(Request request, Response response) {
				User user = gson.fromJson(request.body(), User.class);
				response.type("application/json");
				try {
					service.register(user.name);
					response.status(201);
					response.header("Location", user.id);
					return userMapper.map(service.getBy(user.name));

				} catch (UserYetExitsException e) {
					return userYetExists(response, e);
				}
			}

		});
	}

	private static Object userNotExists(Response response, UserNotExitsException e) {
		response.status(404);
		return new Message("User " + e.getUserName() + " not exists");
	}

	private static Object userYetExists(Response response, UserYetExitsException e) {
		response.status(412);
		return new Message("User " + e.getUserName() + " yet exists");
	}
}

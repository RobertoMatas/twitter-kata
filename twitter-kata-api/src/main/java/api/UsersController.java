package api;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

public class UsersController implements SparkApplication {

	@Override
	public void init() {

		UsersContollerDelegate delegate = new UsersContollerDelegate(
				"localhost:8080/api");

		get(delegate.getFollowingsByUser());

		post(delegate.postFollowing());

		get(delegate.getUserByName());

		post(delegate.newUser());
	}
}

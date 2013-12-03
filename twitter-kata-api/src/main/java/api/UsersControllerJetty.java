package api;

import static spark.Spark.get;
import static spark.Spark.post;

public class UsersControllerJetty {

	public static void main(String args[]) {

		UsersContollerDelegate delegate = new UsersContollerDelegate(
				"localhost:4567");

		get(delegate.getFollowingsByUser());

		post(delegate.postFollowing());

		get(delegate.getUserByName());

		post(delegate.newUser());
	}

}
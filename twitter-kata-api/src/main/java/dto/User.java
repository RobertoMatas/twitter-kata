package dto;

import java.util.ArrayList;
import java.util.List;

import api.RestUriBuilder;

public class User {

	public static final String USERS = "users";

	public final String id;

	public final String name;

	public final List<Link> links;

	protected User(final String name, final RestUriBuilder uriBuilder) {
		this.name = name;
		this.id = uriBuilder.entityUri(USERS, name);
		this.links = new ArrayList<Link>(1);
		this.links.add(uriBuilder.relationLink(id, "following"));
	}

	public String followingLink() {
		return this.links.get(0).link;
	}
}

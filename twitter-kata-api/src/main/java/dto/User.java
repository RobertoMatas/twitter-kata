package dto;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

public class User {

	public final String id;
	
	public final String name;

	public final List<Link> links;

	private transient final String followingLinkTemplate = "http://localhost:4567/users/%s/following";
	
	private transient final String uriLinkTemplate = "http://localhost:4567/users/%s";

	public User(final String name) {
		this.name = name;
		this.id = format(uriLinkTemplate, name);
		this.links = new ArrayList<Link>(1);
		this.links.add(new Link("following", format(followingLinkTemplate, name)));
	}
	
	public String followingLink() {
		return this.links.get(0).link;
	}
}

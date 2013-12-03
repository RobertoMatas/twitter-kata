package api;

import static java.lang.String.format;
import dto.Link;

public class RestUriBuilder {
	
	private final String pathBase;

	public RestUriBuilder(final String pathBase) {
		this.pathBase = pathBase;
	}
	
	public String entityUri(String entityName, String entityId) {
		final String uriLinkTemplate = "http://" + pathBase + "/" + entityName + "/%s";
		return format(uriLinkTemplate, entityId);
	}

	public Link relationLink(String entityUri, String nameOfRelation) {
		final String followingLinkTemplate = entityUri + "/%s";
		return new Link(nameOfRelation, format(followingLinkTemplate, nameOfRelation));
	}
}

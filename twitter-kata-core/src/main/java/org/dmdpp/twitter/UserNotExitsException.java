package org.dmdpp.twitter;

public class UserNotExitsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3702651628764310246L;
	private String userName;

	public UserNotExitsException(String name) {
		super(name);
		this.userName = name;
	}

	public String getUserName() {
		return userName;
	}

}

package org.dmdpp.twitter;

public class UserYetExitsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599333238312463654L;
	private String userName;

	public UserYetExitsException(String userName) {
		super(userName);
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

}

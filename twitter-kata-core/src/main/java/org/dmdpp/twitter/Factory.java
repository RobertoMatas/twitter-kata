package org.dmdpp.twitter;

public class Factory {

	public static UserDomainService userDomainService() {
		return new UserDomainService(new PersistentUserRepository());
	}

}

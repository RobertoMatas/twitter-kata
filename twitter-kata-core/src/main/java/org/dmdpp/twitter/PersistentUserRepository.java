package org.dmdpp.twitter;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class PersistentUserRepository implements UserRepository {

	HashSet<User> users;

	String store;

	public PersistentUserRepository(final String storageName) {
		store = System.getProperty("java.io.tmpdir") + storageName;
		initFromFileSystem();
	}
	
	public PersistentUserRepository() {
		store = System.getProperty("java.io.tmpdir") + "users.ser";
		initFromFileSystem();
	}

	@SuppressWarnings("unchecked")
	private void initFromFileSystem() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(openOrCreateFile());
			users = (HashSet<User>) ois.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			close(ois);
		}
	}

	private FileInputStream openOrCreateFile() throws IOException {	
		File file = new File(store);
		if (file.exists()) {
			return inputStreamForFile(file);
		} else {
			file.createNewFile();
			users = new HashSet<User>(0);
			persist();
			return inputStreamForFile(file);
		}
	
	}

	private FileInputStream inputStreamForFile(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}

	@Override
	public boolean hasUser(String name) {
		return users.contains(new User(name));
	}

	@Override
	public User find(String name) {
		for (User user : users) {
			if (user.name.equals(name))
				return user;
		}
		throw new UserNotExitsException(name);
	}

	@Override
	public void save(User user) {
		users.add(user);
		persist();

	}

	private void persist() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(store));
			oos.writeObject(users);
			oos.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			close(oos);
		}
	}

	private void close(Closeable oos) {
		if (oos != null)
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void deleteAll() {
		users.clear();
		persist();
	}
}

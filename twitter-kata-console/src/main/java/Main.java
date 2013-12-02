import static commands.Actions.exit;
import static commands.Actions.follow;
import static commands.Actions.followings;
import static commands.Actions.register;
import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Scanner;

import org.dmdpp.twitter.Factory;
import org.dmdpp.twitter.UserDomainService;
import org.dmdpp.twitter.UserNotExitsException;
import org.dmdpp.twitter.UserYetExitsException;

import commands.Actions;

public class Main {

	private static Scanner scanner;
	private static UserDomainService service;

	static {
		service = Factory.userDomainService();
		scanner = new Scanner(System.in);
	}

	public static void main(String[] args) {
		printActionsAllowed();
		String[] command = prompt();

		while (!exit.toString().equals(command[0])) {
			try {
				process(command);
				
			} catch (UserNotExitsException unee) {
				err.println(unee.getUserName() + " no es un usuario registrado");

			} catch (UserYetExitsException uyee) {
				err.println(uyee.getUserName() + " ya existe");

			} catch (ArrayIndexOutOfBoundsException e) {
				error();
			}
			command = prompt();
		}
		scanner.close();

	}

	private static void printActionsAllowed() {
		out.println("Comandos permitidos:");
		for (Actions action : Actions.values()) {
			out.println(action.desc);
		}

	}

	private static String[] prompt() {
		out.print("\n> ");
		String next = scanner.nextLine();
		return next.split("\\s");
	}

	private static void process(String[] command) {
		if (register.toString().equals(command[0])) {
			register(command);
		} else if (follow.toString().equals(command[0])) {
			followOp(command);
		} else if (followings.toString().equals(command[0])) {
			followings(command);
		} else {
			error();
		}
	}

	private static void register(String[] command) {
		service.register(command[1]);
		out.println("Registrado usuario: " + command[1]);
	}

	private static void followings(String[] command) {
		out.println(command[1] + " sigue a " + service.followedBy(command[1]));
	}

	private static void followOp(String[] command) {
		service.newFollowRelation(command[1], command[2]);
		out.println(command[1] + " ahora sigue a " + command[2]);
	}

	private static void error() {
		err.println("[Comando no reconocido]. Uso: ");
		printActionsAllowed();
	}

}

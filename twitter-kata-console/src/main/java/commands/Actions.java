package commands;

public enum Actions {
	exit("exit"), follow("follow <user> <userToFollow>"), following("following <user>"), register("register <user>");
	
	public final String desc;
	
	private Actions(String desc) {
		this.desc = desc;
	}
	
}

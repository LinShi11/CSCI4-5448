
public class Invoker {
	
	private CommandInterface command;
	
	public void setCommand(CommandInterface command) {
		this.command = command;
	}
	
	public void execute(Game game) {
		command.execute(game);
	}

	public void undo(Game game) {
		command.undo(game);
	}
}

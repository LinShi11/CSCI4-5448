import java.lang.module.Configuration;

public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public String execute(Game game){
        return command.execute(game);
    }
}

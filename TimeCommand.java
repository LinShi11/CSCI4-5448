import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCommand implements Command {

	@Override
	public void execute() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    System.out.println(formatter.format(new Date()));  
	}

}

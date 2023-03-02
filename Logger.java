import java.io.*;
import java.util.concurrent.Flow;

public class Logger extends Observer{
    private Flow.Subscription subscription;

    public Logger(){

    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

//    https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
    @Override
    public void onNext(String item, int date) {
        if(!item.equals("")){
            try
            {
                String filename= "Logger/Logger-" + (date) + ".txt";
                FileWriter fw = new FileWriter(filename,true);
                fw.write(item+"\n");
                fw.close();
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("End of the day");
    }
}

package project3;

import java.io.*;
import java.util.concurrent.Flow;

/**
 * This class extends from observer (part of the Observer Pattern).
 * It takes the text for each time and write it to a text file.
 */
public class Logger extends Observer{
    private Flow.Subscription subscription;

    public Logger(){

    }

    /**
     * required for Subscriber
     * @param subscription a new subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * The function takes in each text and write it to the correct file.
     * The code was inspired by https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it
     * @param item: the text to write
     * @param date: the date, to keep the structure
     */
    @Override
    public void onNext(String item, int date) {
        // to ensure the text is not empty
        if(!item.equals("")){
            try
            {
                // stored it in a file so things are not as messy
                String filename= "Project3/Logger/Logger-" + (date) + ".txt";
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

    /**
     * part of override
     * @param throwable the exception
     */
    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    /**
     * stops the observers, I just printed end of day since I cannot think of anything else to do
     */
    @Override
    public void onComplete() {
        System.out.println("End of the day");
    }
}

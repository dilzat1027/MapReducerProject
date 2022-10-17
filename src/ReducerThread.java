import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class ReducerThread extends Thread
{
    Socket reducerSocket;
    int threadCount;
    HashMap<String, Integer> wordCount = new HashMap<>();

    public ReducerThread(Socket reducerSocket, int threadCount)
    {
        this.reducerSocket = reducerSocket;
        this.threadCount = threadCount;
    }


    @Override
    public void run()
    {
        try
        {
            InputStreamReader in = new InputStreamReader(this.reducerSocket.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String word = br.readLine();
            while(word != null)
            {
                storeWord(word.toLowerCase());
                word = br.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void storeWord(String word)
    {
        if(wordCount.containsKey(word))
        {
            wordCount.replace(word, wordCount.get(word), wordCount.get(word)+1);
        }
        else if(!wordCount.containsKey(word))
        {
            wordCount.put(word, 1);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MapperThread extends Thread
{
    Socket mapperSocket;
    int threadCountl;
    int reducerCount = 10;

    public MapperThread(Socket mapperSocket, int threadCountl)
    {
        this.mapperSocket = mapperSocket;
        this.threadCountl = threadCountl;
    }

    @Override
    public void run()
    {
        try
        {
            InputStreamReader in = new InputStreamReader(this.mapperSocket.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String line = br.readLine();
            while(line != null)
            {
                readWords(line);
                line = br.readLine();
            }

        }
        catch (Exception e)
        {
            System.out.println("Error MapperThread: " + e.getMessage());
        }
    }


    public void readWords(String line) throws IOException
    {
        String words[] = line.split("\s+");
        for(int i = 0; i< words.length; i++)
        {

            sendWord(words[i]);
        }

    }

    public void sendWord(String word) throws IOException
    {
        int reducerNumber = word.length() % this.reducerCount;
        int reducerPort = 6000 + reducerNumber;

        Socket server = new Socket("localhost", reducerPort);
        PrintWriter pr = new PrintWriter(server.getOutputStream());
        pr.println(word);
        pr.flush();
    }
}

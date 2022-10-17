import java.net.ServerSocket;
import java.net.Socket;

public class Reducer extends Thread
{
    int threadCount;

    public Reducer(int threadCount)
    {
        this.threadCount = threadCount;
    }

    public static void main(String[] args)
    {
        for(int i = 0; i < 10; i++)
        {
            Reducer reducer = new Reducer(i);
            reducer.start();
        }
    }

    @Override
    public void run()
    {
        try
        {
            int portNumber = 6000 +this.threadCount;
            ServerSocket server = new ServerSocket(portNumber);
            while(true)
            {
                Socket reducerServer = server.accept();
                ReducerThread reducer = new ReducerThread(reducerServer, this.threadCount);
                reducer.start();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

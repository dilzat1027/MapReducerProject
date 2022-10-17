import java.net.ServerSocket;
import java.net.Socket;

public class Mapper extends Thread
{
    int threadCount;

    public Mapper(int threadCount)
    {
        this.threadCount = threadCount;
    }

    public static void main(String[] args)
    {
        for(int i = 0; i < 5; i++)
        {
            Mapper mapper = new Mapper(i);
            mapper.start();
        }
    }

    @Override
    public void run()
    {
        try
        {
            int portNumber = 4000 + this.threadCount;
            ServerSocket server = new ServerSocket(portNumber);
            while(true)
            {
                Socket mapperServer = server.accept();
                MapperThread mapper = new MapperThread(mapperServer, this.threadCount);
                mapper.start();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error Mapper: " + e.getMessage());
        }
    }
}

import java.io.*;
import java.net.Socket;


public class MasterNode
{
    static int lineCount = 1;
    static int mapperCount = 5;

    public static void main(String[] args) throws IOException
    {
        readFile();
    }

    public static void readFile() throws IOException
    {
        FileReader source = new FileReader("src/source.txt");
        BufferedReader br = new BufferedReader(source);
        String line = br.readLine();

        while (line != null)
        {
            if(line.length() > 0)
            {
                removePunct(line);
            }
            line = br.readLine();

        }
        br.close();
    }


    public static void removePunct(String line) throws IOException
    {
        String lineAfter = line.replaceAll("\\p{IsPunctuation}", "");
        sendLine(lineAfter);
    }


    public static void sendLine(String line) throws IOException
    {
        int threadNumber = lineCount % mapperCount;
        int portNumber = 4000 + threadNumber;
        Socket server = new Socket("localhost", portNumber);
        PrintWriter pr = new PrintWriter(server.getOutputStream());
        pr.println(line);
        pr.flush();
        lineCount++;
    }

}

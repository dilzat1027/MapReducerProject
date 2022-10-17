import java.util.HashMap;

public class Testing
{
    static HashMap<String, Integer> wordCount = new HashMap<>();

    public static void main(String[] args)
    {

        checking("testing");
        checking("mapper");
        checking("Reducer");
        print();
        System.out.println("================");
        checking("Reducer");
        checking("Reducer");
        print();
    }

    public static void print()
    {
        for(String name: wordCount.keySet())
        {
            String key = name;
            String value = wordCount.get(name).toString();
            System.out.println(key + " : " + value);

        }
    }

    public static void checking(String word)
    {
        String finalWord = word.toLowerCase();
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

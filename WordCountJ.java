import java.io.*;
import java.util.*; 

public class WordCountJ{

    public static void main(String args[]){
        Map<String, Integer>wordCount;

        ThreadCountOneFile t1 = new ThreadCountOneFile("text.txt");
        t1.start();

        t1.join();

        wordCount = t1.getWordCount();
        System.out.println("==========");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
}

class ThreadCountOneFile extends Thread
{
    private String _filename;
    private Map<String, Integer> _wordCount;

    public ThreadCountOneFile(String fname)
    {
        this._filename = fname;
        this._wordCount = new HashMap<String, Integer>();
    }
    
    public Map<String, Integer> getWordCount()
    {
        return this._wordCount;
    }

    @Override
    public void run()
    {
         try{
            BufferedReader br = new BufferedReader(new FileReader(_filename));
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                System.out.println("---");
                for (String word: line.split("\\s+")){
                    // trim prefixing nonalphameric
                    word = word.replaceFirst("[^a-zA-Z0-9\\s]*", "");
                    word = new StringBuffer(word).reverse().toString();
                    word = word.replaceFirst("[^a-zA-Z0-9\\s]*", "");
                    word = new StringBuffer(word).reverse().toString();
                    System.out.println(word);

                    Integer count = _wordCount.get(word);
                    _wordCount.put(word, count == null ? 1 : count + 1);
                }
            }
            br.close();

            // Display map
            for (Map.Entry<String, Integer> entry : _wordCount.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }

            } catch(IOException e) {
                System.out.print("Exception");
            }

    }
}


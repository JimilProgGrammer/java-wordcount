import java.io.*;
import java.util.*; 

public class WordCountJ{

    public static void main(String args[]){
        try{
            Map<String, Integer>wordCount = new HashMap<String, Integer>();
            BufferedReader br = new BufferedReader(new FileReader("text.txt"));
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

                    Integer count = wordCount.get(word);
                    wordCount.put(word, count == null ? 1 : count + 1);
                }
            }
            br.close();

            // Display map
            for (Map.Entry<String, Integer> entry : wordCount.entrySet())
            {
                System.out.println(entry.getKey() + "/" + entry.getValue());
            }

            } catch(IOException e) {
                System.out.print("Exception");
            }	
    }
}

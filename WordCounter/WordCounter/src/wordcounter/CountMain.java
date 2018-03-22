/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcounter;

/**
 *
 * @author Praneetha
 */
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CountMain {
    private static ArrayList<String> content;
    private static final int THREADS = 3;
    private static HashMap<String, Integer> result = new HashMap<String, Integer>();

    public static class Counter extends Thread{
        private int index;
        public Counter(int index){
            this.index = index;
        }

        @Override
        public void run() {
            for(int i = 0; i<content.size(); i++){
                char letter = content.get(i).charAt(0);
                if((letter - 'a')%THREADS == index){
                    String word = content.get(i);
                    if(result.containsKey(word)){
                        result.put(word, result.get(word)+1);
                    }
                    else{
                        result.put(word, 1);
                    }
                }
            }
        }
    }

    public static ArrayList<Pair<String, Integer>> count() throws InterruptedException{
        result = new HashMap<String, Integer>();
        for(int i = 0; i<THREADS; i++){
            Counter counter = new Counter(i);
            counter.run();
            counter.join();
        }


        ArrayList<Pair<String, Integer>> res = new ArrayList<Pair<String, Integer>>();
        for(String key : result.keySet()) {
            res.add(new Pair<String, Integer>(key, result.get(key)));
        }

        Collections.sort(res, new FrequencyComparator());
        return res;
    }

    public static void toFile(String string, String file) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter(file);
        writer.print(string);
        writer.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println("File Name: ");
        
        Scanner scanner = new Scanner(System.in);
        String file = scanner.next();
        content = WordExtracter.extractWords(file);

        ArrayList<Pair<String, Integer>> frequencyList = count();
        String outString = "";
        for(Pair<String, Integer> word : frequencyList){
            outString += word.getKey()+" : "+word.getValue()+"\n";
        }
        System.out.println(outString);
        toFile(outString, file+"_"+System.currentTimeMillis()+".txt");
    }
}

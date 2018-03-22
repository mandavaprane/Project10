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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordExtracter {
    public static ArrayList<String> extractWords(String file) throws IOException {
        ArrayList<String> res = new ArrayList<String>();
        FileReader reader = new FileReader(file);

        StringBuilder word = new StringBuilder();
        int code;
        while(true){
            code = reader.read();
            if(code == -1){
                break;
            }

            char character = (char)code;
            if(character == '\r' || character == '\n'){
                continue;
            }
            if(character != ' ') {
                word.append(character);
            }
            else{
                if(word.length() > 0) {
                    res.add(word.toString());
                    word = new StringBuilder();
                }
            }
        }

        if(word.length()>0) {
            res.add(word.toString());
        }
        return res;
    }
}

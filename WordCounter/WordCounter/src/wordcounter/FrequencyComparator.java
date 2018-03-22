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

import java.util.Comparator;

public class FrequencyComparator implements Comparator<Pair<String, Integer>>{
    @Override
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        if (o1.getValue().equals(o2.getValue())) {
            return o1.getKey().compareTo(o2.getKey());
        }
        return -o1.getValue().compareTo(o2.getValue());
    }
}

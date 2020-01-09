package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kenneth Koepcke
 * An NGRepoReader is the output interface for the data stored on disc
 */
public abstract class NGRepoReader implements Iterator<NGram> {
    /**
     * Finds and returns all ngrams that match the given input
     * @precondition |input| = |this.File.NGram| - 1
     * @param input array of strings used to find NGram where NGram[0, |input|) = input
     * @return a list of NGrams that match the input
     */
    public List<NGram> findNGrams(String ... input){
         List<NGram> ngrams = new ArrayList<>();
         forEachRemaining(ngram -> {
             String[] ngWords = ngram.getWords();
             boolean match = true;
             for(int i = 0; i < input.length && match; i ++){
                 if(!ngWords[i].equals(input[i])){
                     match = false;
                 }
             }
             if(match){
                 ngrams.add(ngram);
             }
         });
         return ngrams;
     }
}

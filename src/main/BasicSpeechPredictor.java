package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * @author Kenneth Koepcke
 * Predicts speech in a simplistic manner, where
 *  only memory is used to store data.
 */
public class BasicSpeechPredictor extends SpeechPredictor {

    /**
     * Constructs a main.SpeechPredictor where the repository of ngrams will be stored in a specific location,
     * and the length of the ngram will be as specified.
     *
     * @param ngRepo the location of the ngram repository
     * @param L      the length of the ngrams
     * @precondition ngRepo is a directory
     * n > 0
     */
    public BasicSpeechPredictor(NGRepo ngRepo, int L) {
        super(ngRepo, L);
    }

    @Override
    void train(File f) {
        try {
            Scanner reader = new Scanner(f);
            String[] ngram = new String[this.L];
            Consumer<String[]> shiftLeft = strings -> {
                if (strings.length > 1) {
                    System.arraycopy(strings, 1, strings, 0, strings.length - 1);
                }
                strings[strings.length - 1] = null;
            };
            for(int i = 0; i < ngram.length; i ++){
                ngram[i] = reader.next();
            }
            this.ngRepo.add(new NGram(reader.next(), ngram));
            while(reader.hasNext()){
                String fin = ngram[ngram.length - 1];
                shiftLeft.accept(ngram);
                ngram[ngram.length - 1] = reader.next();
                this.ngRepo.add(new NGram(fin, ngram));
            }
        }
        catch(java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    String predict(String... input) {
        if(input.length > this.L){
            throw new IllegalArgumentException("length of input greater than ngram");
        }
        return null;
    }
}

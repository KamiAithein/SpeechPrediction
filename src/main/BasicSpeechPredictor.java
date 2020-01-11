package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

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
     * @precondition ngRepo is a directory
     * n > 0
     */
    public BasicSpeechPredictor(NGRepo ngRepo) {
        super(ngRepo, ngRepo.getLength());
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
            Function<Scanner, String> next = (scanner) -> scanner.next().replaceAll("[^A-Za-z0-9]", "").toLowerCase();
            for(int i = 0; i < ngram.length; i ++){
                ngram[i] = next.apply(reader);
            }
            String fin = next.apply(reader);
            this.ngRepo.add(new NGram(fin, Arrays.copyOf(ngram, ngram.length)));
            while(reader.hasNext()){
                shiftLeft.accept(ngram);
                ngram[ngram.length - 1] = fin;
                fin = next.apply(reader);

                this.ngRepo.add(new NGram(fin, Arrays.copyOf(ngram, ngram.length)));
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

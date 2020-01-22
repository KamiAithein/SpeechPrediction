package javacode.basic;

import javacode.basic.NGram.NGram;
import javacode.basic.NGRepo.NGRepo;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class SpeechPredictor<L, R> {

    /**
     * The directory in which the ngram database will be stored
     */
    protected final NGRepo<L, R> ngRepo;

    /**
     * Constructs a main.SpeechPredictor where the repository of ngrams will be stored in a specific location,
     *  and the length of the ngram will be as specified.
     * @precondition ngRepo is a directory
     *                  n > 0
     * @param ngRepo the ngRepo
     */
    public SpeechPredictor(NGRepo<L, R> ngRepo){
        this.ngRepo = ngRepo;
    }


    public void train(File f, int l) {
        try {
            Scanner reader = new Scanner(f);
            String[] ngram = new String[l];
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

}

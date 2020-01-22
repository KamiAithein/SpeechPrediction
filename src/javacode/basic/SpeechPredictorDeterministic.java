package javacode.basic;

import javacode.basic.NGRepo.NGRepo;

import java.io.File;

/**
 * @author Kenneth Koepcke
 *
 * A speech predictor will collect from a set of data a number of ngrams and use ngrams to predict
 *  what is most likely the next word to occur in a sentence. In order to maintain a large number of ngrams the ngrams will
 *  be stored in an external file
 */
public abstract class SpeechPredictorDeterministic<L, R> extends SpeechPredictor<L, R> {



    /**
     * The length of the ngram
     */
    protected final int L;

    /**
     * Constructs a main.SpeechPredictor where the repository of ngrams will be stored in a specific location,
     *  and the length of the ngram will be as specified.
     * @precondition ngRepo is a directory
     *                  n > 0
     * @param ngRepo the ngRepo
     * @param L the length of the ngrams
     */
    public SpeechPredictorDeterministic(NGRepo ngRepo, int L){
        super(ngRepo);
        assert L > 0;
        this.L = L;
    }

    /**
     * @return the directory containing the ngrams
     */
    public NGRepo getNgRepo(){
        return this.ngRepo;
    }

    /**
     * @return the number of grams in the ngram
     */
    public int getLength(){
        return this.L;
    }

    /**
     * Trains this speech predictor on the given file, adding all ngrams to the ngRepo
     * @precondition f is accessible file
     * @param f the file to train from
     */
    abstract void train(File f);

    /**
     * Predicts next word from the given input
     * @precondition |input| = this.n
     * @param input the words from which the next word is generated.
     * @return the next word most likely to occur after the input
     */
    abstract String predict(String ... input);
}

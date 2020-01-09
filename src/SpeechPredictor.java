import java.io.File;

/**
 * @author Kenneth Koepcke
 *
 * A speech predictor will collect from a set of data a number of ngrams and use ngrams to predict
 *  what is most likely the next word to occur in a sentence. In order to maintain a large number of ngrams the ngrams will
 *  be stored in an external file
 */
public abstract class SpeechPredictor {
    /**
     * The directory in which the ngram database will be stored
     */
    protected File ngRepo;

    /**
     * The length of the ngram
     */
    protected int n;

    /**
     * Constructs a SpeechPredictor where the repository of ngrams will be stored in a specific location,
     *  and the length of the ngram will be as specified.
     * @param ngRepo the location of the ngram repository
     * @param n the length of the ngrams
     */
    public SpeechPredictor(File ngRepo, int n){
        assert ngRepo.isDirectory();
        this.ngRepo = ngRepo;
        this.n = n;
    }

    abstract void train(File f);

    abstract String predict(String input);
}

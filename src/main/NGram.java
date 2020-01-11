package main;

/**
 * @author Kenneth Koepcke
 * An NGram is a string of words of n length with a frequency p
 *  representing the proportion of times that the last word occurs after
 *  the words preceding it in the data set
 */
public class NGram {
    /**
     * Words stored in this NGram.
     */
    private String[] words;

    /**
     * The final word of the NGram, which is the prediction.
     */
    private String finalWord;

    public NGram(String finalWord, String ... words){
        this.words = words;
        this.finalWord = finalWord;
    }

    /**
     * @return the words in this NGram
     */
    public String[] getWords(){
        return this.words;
    }

    /**
     * @return the final word, which is used for prediction.
     */
    public String getFinal(){
        return this.finalWord;
    }
}

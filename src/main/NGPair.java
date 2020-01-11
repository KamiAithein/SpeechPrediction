package main;

/**
 * @author Kenneth Koepcke
 * An immutable pair of NGram and the frequency which that NGram to occurs.
 */
public class NGPair {
    private final NGram NG;
    private float freq;
    public NGPair(NGram n, float f){
        this.NG = n;
        this.freq = f;
    }
    NGram getNG(){
        return this.NG;
    }
    float getFrequency(){
        return this.freq;
    }
    void setFrequency(float f){
        this.freq = f;
    }

}

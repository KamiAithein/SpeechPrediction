package javacode.basic.NGram;


/**
 * @author Kenneth Koepcke
 * An immutable pair of java.basic.NGram and the frequency which that java.basic.NGram to occurs.
 */
public class NGPair {
    /**
     * The actual java.basic.NGram being stored
     */
    private final NGram NG;
    /**
     * The frequency of occurrence of this java.basic.NGram
     */
    private float freq;
    public NGPair(NGram n, float f){
        this.NG = n;
        this.freq = f;
    }

    /**
     * @return the java.basic.NGram stored
     */
    public NGram getNG(){
        return this.NG;
    }

    /**
     * @return the frequency stored
     */
    public float getFrequency(){
        return this.freq;
    }

    /**
     * Sets the current frequency to incoming value. Unsure if this should be
     *  the interface to do this action
     * @param f the frequency
     */
    public void setFrequency(float f){
        this.freq = f;
    }

    /**
     * This pair's hashcode is defined by the java.basic.NGram it contains. The frequency does not affect the
     *  equivalency value of pairs because ultimately frequencies should be equal.
     * @return the hashcode of this object
     */
    public int hashCode(){
        return this.getNG().hashCode();
    }

    /**
     * @return a legible string representation of the data held in this object
     */
    public String toString(){
        return this.NG.toString() + ", " + this.freq;
    }

}

package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
/**
 * @author Kenneth Koepcke
 *
 * Represents an NGRepo where data is stored in memory as a HashMap.
 *
 */
public class HashMapNGRepo implements NGRepo<Integer, List<NGram>> {
    /**
     * The NGRepo in which data is stored
     * Integer represents a hashcode of an array using
     *  java.util.Arrays.hashCode
     */
    protected HashMap<Integer, List<NGram>> repo;

    private final byte L;

    /**
     * Constructs the repo with
     * @param L the length
     * @precondition L > 0
     */
    public HashMapNGRepo(byte L){
        if(!(L > 0)){
            throw new IllegalArgumentException();
        }
        this.repo = new HashMap<>();
        this.L = L;
    }

    @Override
    public void add(NGram data) {
        if(data.getWords().length != this.L){
            throw new IllegalArgumentException("Invalid length");
        }
        if(this.repo.containsKey(Arrays.hashCode(data.getWords()))) {
            List<NGram> ng = this.repo.get(Arrays.hashCode(data.getWords()));
            ng.add(data);
        }
        else {
            List<NGram> ng = new LinkedList<>();
            ng.add(data);
            this.repo.put(Arrays.hashCode(data.getWords()), ng);
        }
    }

    @Override
    public void retrieve(BiConsumer<Integer, List<NGram>> filter) {
        this.repo.forEach(filter);
    }

    @Override
    public byte getLength() {
        return this.L;
    }


}

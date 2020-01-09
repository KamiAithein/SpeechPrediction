package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Kenneth Koepcke
 *
 * Represents an NGRepo where data is stored in memory as a HashMap.
 *
 * @param <T> the data used to look up an NGram.
 *            T will be stored in an array where
 *           [T, T, T, Tfinal]
 *           Tfinal is not checked for equality as to allow for different
 *              outcomes of the same input based on probability
 */
public class HashMapNGRepo<T> implements NGRepo<T> {
    /**
     * The NGRepo in which data is stored
     * Integer represents a hashcode of an array using
     *  java.util.Arrays.hashCode
     */
    HashMap<Integer, NGram[]> repo;
    
    @Override
    public void add(NGram data) {
        this.repo.put(Arrays.hashCode(data.getWords()), data);
    }

    @Override
    public NGram[] retrieve(BiConsumer<Integer, NGram[]> filter) {
        this.repo.forEach(filter);
        return new NGram[0];
    }

}

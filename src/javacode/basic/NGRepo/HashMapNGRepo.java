package javacode.basic.NGRepo;

import javacode.basic.NGram.NGPair;
import javacode.basic.NGram.NGram;

import java.util.*;
import java.util.function.BiConsumer;
/**
 * @author Kenneth Koepcke
 *
 * Represents an java.basic.NGRepo.java.basic.NGRepo where data is stored in memory as a HashMap.
 *
 */
public class HashMapNGRepo implements NGRepo<Integer, List<NGPair>> {
    /**
     * The java.basic.NGRepo.java.basic.NGRepo in which data is stored
     * Integer represents a hashcode of an array using
     *  java.util.Arrays.hashCode
     */
    protected HashMap<Integer, List<NGPair>> repo;

    /**
     * The count of each occurrence of an NG in this repo
     */
    private Map<Integer, Integer> count;

    /**
     * The total number of entries in count per bucket
     */
    private Map<Integer, Integer> totalCountPerBucket;
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
        this.totalCountPerBucket = new HashMap<>();
        this.count = new HashMap<>();
    }

    static class Container<T>{
        T data;
        Container(T data){
            this.data = data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
    @Override
    public void add(NGram data) {
        if(data.getWords().length != this.L){
            throw new IllegalArgumentException("Invalid length");
        }
        final int bucketHash = Arrays.hashCode(data.getWords());
        final int dataHash = data.hashCode();
        this.totalCountPerBucket.put(bucketHash, this.totalCountPerBucket.containsKey(bucketHash) ? this.totalCountPerBucket.remove(bucketHash) + 1 : 1);
        //If there is not an entry, make a new one
        this.count.put(dataHash, this.count.containsKey(dataHash) ? this.count.remove(dataHash) + 1 : 1);
        if(this.repo.containsKey(bucketHash)) {
            List<NGPair> bucket = this.repo.get(bucketHash);
            //update the freq for each ng in this bucket while checking to see if data is already in bucket
            int bucketCount = this.totalCountPerBucket.get(bucketHash);
            Container<Boolean> contains = new Container<>(false);

            bucket.forEach((ngp) -> {
                ngp.setFrequency((float)count.get(ngp.hashCode())/(bucketCount));
                if(ngp.hashCode() == dataHash)
                    contains.setData(true);
            });
            if(!contains.data)
                bucket.add(new NGPair(data, (float)count.get(dataHash)/(bucketCount)));
        }
        else {
            List<NGPair> bucket = new LinkedList<>();
            bucket.add(new NGPair(data, 1));
            this.repo.put(Arrays.hashCode(data.getWords()), bucket);
        }
    }

    @Override
    public void retrieve(BiConsumer<Integer, List<NGPair>> filter) {
        this.repo.forEach(filter);
    }

    @Override
    public byte getLength() {
        return this.L;
    }


}

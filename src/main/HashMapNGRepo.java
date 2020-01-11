package main;

import java.util.*;
import java.util.function.BiConsumer;
/**
 * @author Kenneth Koepcke
 *
 * Represents an NGRepo where data is stored in memory as a HashMap.
 *
 */
public class HashMapNGRepo implements NGRepo<Integer, List<NGPair>> {
    /**
     * The NGRepo in which data is stored
     * Integer represents a hashcode of an array using
     *  java.util.Arrays.hashCode
     */
    protected HashMap<Integer, List<NGPair>> repo;

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
            List<NGPair> ngps = this.repo.get(Arrays.hashCode(data.getWords()));
            int totalCount = ngps.size();
            Map<Integer, Integer> eachCount = new HashMap<>();
            eachCount.put(data.getFinal().hashCode(), 1);
            //Get the count of each ng
            ngps.forEach((ngp) ->{
                int ngHash = ngp.getNG().getFinal().hashCode();
                if(eachCount.containsKey(ngHash)){
                    eachCount.put(ngHash, eachCount.remove(ngHash) + 1);
                }
                else{
                    eachCount.put(ngHash, 1);
                }
            });
            //update the freq for each ng
            ngps.forEach((ngp) ->{
                ngp.setFrequency(eachCount.get(ngp.getNG().getFinal().hashCode())/(float)(totalCount));
            });
            //If data not in set then add w correct frequency
            if(eachCount.get(data.getFinal().hashCode()) == 1){
                ngps.add(new NGPair(data, 1F/totalCount));
            }
        }
        else {
            List<NGPair> ng = new LinkedList<>();
            ng.add(new NGPair(data, 1));
            this.repo.put(Arrays.hashCode(data.getWords()), ng);
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

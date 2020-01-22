package javacode.basic.NGRepo;

import javacode.basic.NGram.NGram;

import java.util.function.BiConsumer;

/**
 * @author Kenneth Koepcke
 *
 * @param <L> the lookup data type
 * @param <R> the retrieval storage type
 *
 * Represents the actual data being stored, referred to as the java.basic.NGRepo.java.basic.NGRepo
 *  Must be stored in a retrieveable manner
 */
public interface NGRepo<L, R> {

    /**
     * Adds data to the java.basic.NGRepo.java.basic.NGRepo
     * @precondition |data.words| = this.getLength()
     *                  data.words = [String ... words]
     * @param data the data being added
     */
    void add(NGram data);

    /**
     * Retrieves data from java.basic.NGRepo.java.basic.NGRepo, using L as lookup data and R as
     *      storage
     * @param filter filters the data from the java.basic.NGRepo.java.basic.NGRepo. As the
             Consumer interface does not return a value, it is the duty
             Of the filter to store the data it finds
     */
    void retrieve(BiConsumer<L, R> filter);

    /**
     * @return the length of NGrams stored in this repo
     */
    byte getLength();
}

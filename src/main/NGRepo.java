package main;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Kenneth Koepcke
 *
 * @param <L> the lookup data type
 * @param <R> the retrieval storage type
 *
 * Represents the actual data being stored, referred to as the NGRepo
 *  Must be stored in a retrieveable manner
 */
public interface NGRepo<L, R> {

    /**
     * Adds data to the NGRepo
     * @precondition |data.words| = this.getLength()
     *                  data.words = [String ... words]
     * @param data the data being added
     */
    void add(NGram data);

    /**
     * Retrieves data from NGRepo, using L as lookup data and R as
     *      storage
     * @param filter filters the data from the NGRepo. The
     *              data found will be stored in R
     */
    void retrieve(BiConsumer<L, R> filter);

    /**
     * @return the length of NGrams stored in this repo
     */
    byte getLength();
}

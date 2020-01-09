package main;

import java.util.function.Function;

/**
 * @author Kenneth Koepcke
 *
 * Represents the actual data being stored, referred to as the NGRepo
 *  Must be stored in a retrieveable manner
 */
public interface NGRepo<T> {

    /**
     * Adds data to the NGRepo
     * @param data the data being added
     */
    void add(NGram data);

    /**
     * Retrieves data from NGRepo.
     * @param filter filters the data from the NGRepo
     * @return the NGrams found from the function given.
     */
    NGram[] retrieve(Function<T, NGram[]> filter);
}

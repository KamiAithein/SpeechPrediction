package main;

/**
 * @author Kenneth Koepcke
 * NGRepoWriter is the writing interface for the NGRepo.
 *  It's only function is to add NGrams to the repo.
 */
public interface NGRepoWriter {
    /**
     * Adds NGram to the repo in a way accessible to an NGRepoReader
     * @param ng the NGram to add
     */
    void add(NGram ng);
}

package main;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kenneth Koepcke
 * Test NGRepo
 */
class HashMapNGRepoTest {

    /**
     * Tests to make sure NGRepo has valid error checking for arguments
     *  Tests:
     *      length
     *          = -1 catch
     *          = 0 catch
     *          = 1 valid
     */
    @Test
    void NGramLengthValidity(){
        byte length = -1;
        try {
            NGRepo ngr = new HashMapNGRepo(length);
            fail("created negative length accepting hashmap");
        }
        catch(IllegalArgumentException e){
            //pass
        }

        length = 0;
        try {
            NGRepo ngr = new HashMapNGRepo(length);
            fail("created negative length accepting hashmap");
        }
        catch(IllegalArgumentException e){
            //pass
        }

        length = 1;
        try {
            NGRepo ngr = new HashMapNGRepo(length);
        }
        catch(IllegalArgumentException e){
            fail("failed to create 1 length accepting hashmap");
        }
    }

    /**
     * Test that getLength function gets the correct length
     *      tests getLength()
     *          length = 3
     */
    @Test
    void getLength(){
        byte length = 3;
        NGRepo ngr = new HashMapNGRepo(length);
        assertEquals(length, ngr.getLength());
    }
class TestHashMapNGRepo extends HashMapNGRepo{
    public TestHashMapNGRepo(byte L){
        super(L);
    }
    Map<Integer, List<NGPair>> getList(){
        return this.repo;
    }
}

    /**
     * Tests add ng of length 3
     */
    @Test
    void add() {
        byte length = 3;
        TestHashMapNGRepo ngr = new TestHashMapNGRepo(length);
        NGram ng = new NGram("you", "today", "how", "are");
        ngr.add(ng);

        Map<Integer, List<NGPair>> repo = ngr.getList();
        assertTrue(repo.containsKey(Arrays.hashCode(ng.getWords())));
        assertSame(repo.get(Arrays.hashCode(ng.getWords())).get(0).getNG(), ng);

    }

    /**
     * tests retrieve of ng of length 3
     */
    @Test
    void retrieve() {
        byte length = 3;
        TestHashMapNGRepo ngr = new TestHashMapNGRepo(length);
        NGram ng = new NGram("you", "today", "how", "are");

        Map<Integer, List<NGPair>> repo = ngr.getList();

        NGPair ngp = new NGPair(ng, 1);
        repo.put(Arrays.hashCode(ng.getWords()), new LinkedList<>(Collections.singletonList(ngp)));
        List<List<NGPair>> output = new LinkedList<>();
        ngr.retrieve((hash, list) -> {
            if(hash.equals(Arrays.hashCode(ng.getWords()))){
                output.add(list);
            }
        });

        assertTrue(output.get(0).contains(ngp));
    }
}
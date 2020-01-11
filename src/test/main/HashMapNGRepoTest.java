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

    class BooleanContainer{
        boolean b;
        public BooleanContainer(){
            this.b = false;
        }
        public void set(boolean b){
            this.b = b;
        }
    }

    @Test
    void add2NGDiff(){
        byte length = 3;
        TestHashMapNGRepo ngr = new TestHashMapNGRepo(length);
        NGram ng1 = new NGram("you", "today", "how", "are");
        NGram ng2 = new NGram("fuck", "work", "damn", "you");
        ngr.add(ng1);
        ngr.add(ng2);

        Map<Integer, List<NGPair>> repo = ngr.getList();
        assertTrue(repo.containsKey(Arrays.hashCode(ng1.getWords())));
        assertTrue(repo.containsKey(Arrays.hashCode(ng2.getWords())));
        BooleanContainer containsng1 = new BooleanContainer(), containsng2 = new BooleanContainer();
        repo.forEach((i, ngpl) -> {
            System.out.println(Arrays.toString(ngpl.toArray()));
            ngpl.forEach(ngp -> {
                if(Arrays.hashCode(ngp.getNG().getWords()) == Arrays.hashCode(ng1.getWords())) {
                    containsng1.set(true);
                }
                if(Arrays.hashCode(ngp.getNG().getWords()) == Arrays.hashCode(ng2.getWords()))
                    containsng2.set(true);
            });

        });
        assertTrue(containsng1.b && containsng2.b);
    }

    @Test
    void add2NGSame(){
        byte length = 3;
        TestHashMapNGRepo ngr = new TestHashMapNGRepo(length);
        NGram ng1 = new NGram("you", "today", "how", "are");
        NGram ng2 = new NGram("fuck", "today", "how", "are");
        ngr.add(ng1);
        ngr.add(ng2);

        Map<Integer, List<NGPair>> repo = ngr.getList();
        assertTrue(repo.containsKey(Arrays.hashCode(ng1.getWords())));
        assertTrue(repo.containsKey(Arrays.hashCode(ng2.getWords())));
        BooleanContainer containsng1 = new BooleanContainer(), containsng2 = new BooleanContainer();
        repo.get(Arrays.hashCode(ng1.getWords())).forEach(ngp -> {
            if(ngp.getNG().getFinal().equals(ng1.getFinal())) {
                containsng1.set(true);
            }
            if(ngp.getNG().getFinal().equals(ng2.getFinal())) {
                containsng2.set(true);
            }
            assertTrue(ngp.getFrequency() - 0.5 < 0.001);
        });
        assertTrue(containsng1.b && containsng2.b);
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
package main;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasicSpeechPredictorTest {

    class TestHashMapNGRepo extends HashMapNGRepo{
        public TestHashMapNGRepo(byte L){
            super(L);
        }
        Map<Integer, List<NGPair>> getList(){
            return this.repo;
        }
    }
    @Test
    void trainDT1() {
        NGRepo ngp = new TestHashMapNGRepo((byte)3);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        bsp.train(new File("src/test/testdata/dt1.txt"));
        ngpTest.repo.forEach((i, p) -> {
            System.out.println(p);
        });
        System.out.println(ngp.getLength());
    }
    @Test
    void trainGetty() {
        NGRepo ngp = new TestHashMapNGRepo((byte)2);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        bsp.train(new File("src/test/testdata/getty.txt"));
        ngpTest.repo.forEach((i, p) -> {
            System.out.println(p);
        });
        System.out.println(ngp.getLength());
    }
    @Test
    void trainRepetition() {
        NGRepo ngp = new TestHashMapNGRepo((byte)2);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        bsp.train(new File("src/test/testdata/repetition.txt"));
        ngpTest.repo.forEach((i, p) -> {
            System.out.println(p);
        });
        System.out.println(ngp.getLength());
    }

    @Test
    void predict() {
    }
}
package main;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
    void trainDTMain() {
        NGRepo ngp = new TestHashMapNGRepo((byte)3);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        for(int i = 0; i <= 32; i ++){
            bsp.train(new File(String.format("data/djt/DJTspeeches%d.txt", i)));
        }
        ngpTest.repo.forEach((i, p) -> {
            System.out.println(p);
        });
        System.out.println(ngp.getLength());
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
    void predictDJT() {
        String[] input = {"as", "long", "as", "i"};
        for(int i = 0; i < input.length; i ++){
            System.out.print(input[i]);
            System.out.print(" ");

        }
        NGRepo ngp = new TestHashMapNGRepo((byte)4);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        for(int i = 0; i <= 32; i ++){
            bsp.train(new File(String.format("data/djt/DJTspeeches%d.txt", i)));
        }
        Consumer<String[]> shiftLeft = strings -> {
            if (strings.length > 1) {
                System.arraycopy(strings, 1, strings, 0, strings.length - 1);
            }
            strings[strings.length - 1] = null;
        };
        for(int i = 0; i < 500; i ++){
            if(i % 10 == 0){
                System.out.println();
            }
            String prediction = bsp.predict(input);
            System.out.print(prediction);
            System.out.print(" ");
            shiftLeft.accept(input);
            input[input.length - 1] = prediction;

        }

    }
    @Test
    void predictObama() {
        String[] input = {"as", "long", "as", "i"};
        for(int i = 0; i < input.length; i ++){
            System.out.print(input[i]);
            System.out.print(" ");

        }
        NGRepo ngp = new TestHashMapNGRepo((byte)4);
        TestHashMapNGRepo ngpTest = (TestHashMapNGRepo)ngp;
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        bsp.train(new File("data/all_obama_speeches.txt"));
        Consumer<String[]> shiftLeft = strings -> {
            if (strings.length > 1) {
                System.arraycopy(strings, 1, strings, 0, strings.length - 1);
            }
            strings[strings.length - 1] = null;
        };
        for(int i = 0; i < 500; i ++){
            if(i % 10 == 0){
                System.out.println();
            }
            String prediction = bsp.predict(input);
            System.out.print(prediction);
            System.out.print(" ");
            shiftLeft.accept(input);
            input[input.length - 1] = prediction;

        }

    }
}
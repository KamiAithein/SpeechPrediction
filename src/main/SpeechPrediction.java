package main;

import java.io.File;
import java.util.function.Consumer;

public class SpeechPrediction {

    /**
     * main
     * @param args not used
     */
    public static void main(String[] args){
        String[] input = {"i", "can", "make", "it"/*, "5428", "on"*/};
        for(int i = 0; i < input.length; i ++){
            System.out.print(input[i]);
            System.out.print(" ");

        }
        NGRepo ngp = new HashMapNGRepo((byte)4);
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        bsp.train(new File("data/test_ron.txt"));
        Consumer<String[]> shiftLeft = strings -> {
            if (strings.length > 1) {
                System.arraycopy(strings, 1, strings, 0, strings.length - 1);
            }
            strings[strings.length - 1] = null;
        };
        for(int i = 0; i < 500; i ++){
            if(i % 6 == 0)
            System.out.println("");
            String prediction = bsp.predict(input);
            System.out.print(prediction.replaceAll("period", "\n"));
            System.out.print(" ");
            shiftLeft.accept(input);
            input[input.length - 1] = prediction;

        }
    }
}

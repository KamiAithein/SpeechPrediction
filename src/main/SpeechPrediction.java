package main;

import java.io.File;
import java.util.function.Consumer;

public class SpeechPrediction {

    /**
     * Shifts a string left one. Used to iterate through text in the training data.
     */
    private static Consumer<String[]> shiftLeft = strings -> {
        if (strings.length > 1) {
            System.arraycopy(strings, 1, strings, 0, strings.length - 1);
        }
        strings[strings.length - 1] = null;
    };
    /**
     * The starting seed: The phrase that will be built off of. MUST exist within the training data.
     */
    private static String[] input = {"and", "people", "of", "the"};
    /**
     * The length of the ngram. Stored in byte because it will probably never really be longer than 100...
     */
    private static final byte ngramLength = 4;
    /**
     * All of the data files to be trained on
     */
    private static final File[] trainingData = {new File("data/djt/DJTspeeches0.txt")};
    /**
     * main
     * @param args not used
     */
    public static void main(String[] args){
        //Prints the starting seed before prediction
        for (String s : input) {
            System.out.print(s);
            System.out.print(" ");

        }
        NGRepo ngp = new HashMapNGRepo(ngramLength);
        BasicSpeechPredictor bsp = new BasicSpeechPredictor(ngp);
        for(File f : trainingData){
            bsp.train(f);
        }
        for(int i = 0; i < 500; i ++){
            if(i % 6 == 0)
                System.out.println("");
            String prediction = bsp.predict(input);
            System.out.print(" ");
            shiftLeft.accept(input);
            input[input.length - 1] = prediction;
            System.out.print(prediction);
        }
    }
}

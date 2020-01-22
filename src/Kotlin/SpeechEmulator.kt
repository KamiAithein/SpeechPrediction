package Kotlin

import javacode.basic.NGRepo.HashMapNGRepo
import javacode.basic.NGram.NGPair
import java.io.File
import java.io.FileOutputStream

public class SpeechEmulator(input: List<File>) {
    val ndsp: NonDeterministicSpeechPredictor<Int, List<NGPair>> = NDSPBasic(HashMapNGRepo(3), 3, 3)
    init{
        for(file in input){
            ndsp.train(file, 3)
        }
    }
    fun generateSpeech(output: File, wordCount: Int){
        val init = mutableListOf("and", "the", "people")
        for(i in 0..wordCount){
            val outcomesInit = ndsp.predict(init)
            val outcomesUnPruned = mutableListOf<NGPair>()
            outcomesUnPruned.addAll(outcomesInit)
            outcomesUnPruned.sortBy{it.frequency}
            init.removeAt(0)
            val finalWord = outcomesUnPruned[outcomesUnPruned.size - 1].ng.final
            init.add(finalWord)
            FileOutputStream(output, true).bufferedWriter().use{writer -> writer.write("$finalWord ")}
            if(i%6 == 0){
                FileOutputStream(output, true).bufferedWriter().use{writer -> writer.write("\n")}
            }
        }
    }
}
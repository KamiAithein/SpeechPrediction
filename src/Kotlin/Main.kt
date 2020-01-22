package Kotlin

import java.io.File

fun main(){
    val trainingFiles = mutableListOf<File>()
    for(i in 0..32)
        trainingFiles.add(File("data/djt/DJTspeeches$i.txt"))
    val output = File("examples/kotlintest.txt")
    var speechEmulator: SpeechEmulator = SpeechEmulator(trainingFiles)
    speechEmulator.generateSpeech(output, 100)
}
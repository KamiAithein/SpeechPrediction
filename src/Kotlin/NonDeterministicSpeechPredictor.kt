package Kotlin

import javacode.basic.NGRepo.NGRepo
import javacode.basic.NGram.NGPair
import javacode.basic.NGRepo.*
import javacode.basic.NGram.*
import javacode.basic.SpeechPredictor;

/**
 * @author Kenneth Koepcke
 * A speech prediction model that is nondeterministic:
 *  It will return the n most likely predictions, where n = this.width
 */
abstract class NonDeterministicSpeechPredictor<L, R>(private val repo: NGRepo<L, R>, val width: Int, val depth: Int): SpeechPredictor<L, R>(repo) {
    abstract fun predict(seed: List<String>): List<NGPair>
}
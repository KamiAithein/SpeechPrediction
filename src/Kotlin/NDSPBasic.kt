package Kotlin

import javacode.basic.NGRepo.NGRepo
import javacode.basic.NGram.NGPair
import java.util.*
/**
 * @author Kenneth Koepcke
 * A non deterministic speech prediction model
 */
class NDSPBasic(repo: NGRepo<Int, List<NGPair>>, width: Int, depth: Int) : NonDeterministicSpeechPredictor<Int, List<NGPair>>(repo, width, depth) {

    /**
     * @precondition |seed| <= |this.repo|
     * @param seed the entire phrase up until the predicted word.
     * @return the most likely phrases given the seed
     */
    override fun predict(seed: List<String>): List<NGPair> {
        val outUnPrune = mutableListOf<NGPair>()
        //For each bucket, find the most likely
        this.ngRepo.retrieve { t: Int, ls: List<NGPair> ->
            if(t == seed.hashCode()) {
                outUnPrune.addAll(ls)
            }
        }
        //prune
        val outPrune = mutableListOf<NGPair>()
        for(toPrune in outUnPrune){
            if(outPrune.size < width){
                outPrune.add(toPrune)
            }
            else{
                var minGreaterThan = outPrune[0]
                for(prune in outPrune){
                    if(minGreaterThan.frequency < prune.frequency){
                        minGreaterThan = prune
                    }
                }
                if(toPrune.frequency > minGreaterThan.frequency){
                    outPrune.remove(minGreaterThan)
                    outPrune.add(toPrune)
                }
            }
        }
        return outPrune
        //TODO Implement depth based check for beam search to actually follow path n depth instead of just giving the k most likely results
    }
}
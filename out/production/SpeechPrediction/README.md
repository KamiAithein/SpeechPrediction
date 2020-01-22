# basic.SpeechPrediction
Predict speech when given text based on ngram method

Example speech found in examples directory

"Learns" by collecting n length phrases and the frequency of those phrases in relation to other phrases based on the next word.
i.e. if length of 3:

  "I want to" is the phrase recorded, and then the next word has a frequency
  
  "I want to" + "Play" has a higher frequency than "I want to" + "bingbingbong" in most scenarios, therefore it will be chosen more often.
  
  1/12/2020
  
  Using BasicSpeechPredictor and HashMapNGRepo
  
  Works by running SpeechPrediction.java
  
    input needs to be the same length as the parameters for constructing HashMapNGRepo
    
    output will be the input of the speech predictor
  
  This algorithm works best when emulating someone with a short attention span.

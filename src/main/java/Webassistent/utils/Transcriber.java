package Webassistent.utils;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.io.*;

public class Transcriber {

    public Configuration configuration;
    public StreamSpeechRecognizer recognizer;

    public Transcriber() {
        configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        try {
            recognizer = new StreamSpeechRecognizer(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String testing(byte[] file) {
        String rVal = "";
        try {

            InputStream stream = new ByteArrayInputStream(file);
//            InputStream stream = new FileInputStream(new File("/home/filcasidy/Downloads/myRecording00.wav"));

            recognizer.startRecognition(stream);
            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {
                rVal += result.getHypothesis();
            }
            recognizer.stopRecognition();
        } catch (Exception e) {
            recognizer.stopRecognition();
            System.err.println("Something went wrong");
            return rVal;
        }
        return rVal;
    }


}

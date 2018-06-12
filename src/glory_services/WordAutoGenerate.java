/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

/**
 *
 * @author Pk
 */
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang3.StringUtils;

public class WordAutoGenerate {

  /**
     * Filename of the dictionary.
     */
    private static final String WORD_FILE_NAME = "words.txt";
    /**
     * Max time to spend searching for a word.
     */
    private static final long MAX_TIME_FOR_WORD_SEARCH = 5000;
    /**
     * Array of letters.
     */
    private String[] letters;

    /**
     * Pattern used for matching.
     */
    private String pattern = "";

    /**
     * HashMAp to Store frequencies
     */
    HashMap<String,Integer> frequency = new HashMap<String,Integer>();

    /**
     * Longest word
     */
    private String longestWord = "";

  public WordAutoGenerate(String[] letters){
      this.letters = letters;
        calculateHashMap(getString(letters));
        Autogenerator();
  }

  String getString(String[] letters) {
        StringBuilder word = new StringBuilder();
        for (String l : letters) {
            word.append(l);
        }
        return word.toString();
    }
    /**
     * set frequencies
     */
    public void calculateHashMap(String word) {
        for (String l : letters) {
            this.frequency.put(l, StringUtils.countMatches(word, l));
        }
    }
    /**
     * Set pattern
     */
    protected void setPattern() {
        // Build the pattern only for one letter.
        StringBuilder patternLetter = new StringBuilder();
        patternLetter.append("([");
        for (int i = 0; i < letters.length; i++) {
            patternLetter.append(letters[i]);
        }
        patternLetter.append("])");
        patternLetter.append("{0,1}");

        // Build the pattern for whole word.
        StringBuilder fullPattern = new StringBuilder();
        fullPattern.append("^");
        for (int i = 0; i < letters.length; i++) {
            fullPattern.append(patternLetter.toString());
        }
        fullPattern.append("$");
        this.pattern = fullPattern.toString();
    }
    /**
     * Check Frequencies return false if word have wrong frequencies
     */
    public boolean checkFrequency(String word) {
        for (String key:this.frequency.keySet()){
           if(!(StringUtils.countMatches(word, key)<=this.frequency.get(key))){
               return false;
           } 
        }
        return true;
    }

    public void Autogenerator() {
        setPattern();
        Pattern r = Pattern.compile(this.pattern);
        Matcher m;
        long startTime = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null){
                
                m = r.matcher(line);
                if (m.find()) {
                if (this.longestWord.equals("")&& checkFrequency(line)) {
                    this.longestWord = line;
                }
                else if((this.longestWord.length() <= line.length())&&checkFrequency(line)){
                    this.longestWord = line;
                }
                }
                if ((System.currentTimeMillis() - startTime)
                        > MAX_TIME_FOR_WORD_SEARCH) {
                    break;
                }
            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public String getLongestWord() {
    if (longestWord.equals("")) {
      longestWord = "There are no words from these letters";
    }
    return longestWord;
  }
}

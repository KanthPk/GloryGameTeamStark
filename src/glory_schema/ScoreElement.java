/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_schema;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author AshanPerera
 */

public class ScoreElement {

    private String word;
    
    public ScoreElement() {
        
    }   

    public ScoreElement(String word) {
        this.word = word;
    }
    
    public int getScore(){
    
        Map<Character,Integer> letterMap = new HashMap<>();
        String lettersAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int i = 0; i < lettersAlphabet.length(); i++) {
            if (lettersAlphabet.charAt(i) == 'N' || lettersAlphabet.charAt(i) == 'O' || lettersAlphabet.charAt(i) == 'A' || 
                lettersAlphabet.charAt(i) == 'I' || lettersAlphabet.charAt(i) == 'E'){
                
                letterMap.put(lettersAlphabet.charAt(i),2);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 2);
            }
            
             if (lettersAlphabet.charAt(i) == 'L' || lettersAlphabet.charAt(i) == 'S' ||
             lettersAlphabet.charAt(i) == 'U' || lettersAlphabet.charAt(i) == 'R' || lettersAlphabet.charAt(i) == 'T') {
                letterMap.put(lettersAlphabet.charAt(i), 4);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 4);
            }

            if (lettersAlphabet.charAt(i) == 'B' || lettersAlphabet.charAt(i) == 'C' ||
            lettersAlphabet.charAt(i) == 'M' ||lettersAlphabet.charAt(i) == 'P') {
                letterMap.put(lettersAlphabet.charAt(i), 6);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 6);
            }

            if (lettersAlphabet.charAt(i) == 'K' ||lettersAlphabet.charAt(i) == 'G' ||lettersAlphabet.charAt(i) == 'D' ||lettersAlphabet.charAt(i) == 'Y'
                    ){
                letterMap.put(lettersAlphabet.charAt(i), 8);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 8);
            }

            if (lettersAlphabet.charAt(i) == 'F' || lettersAlphabet.charAt(i) == 'H' || lettersAlphabet.charAt(i) == 'V' || lettersAlphabet.charAt(i) == 'W') {
                letterMap.put(lettersAlphabet.charAt(i), 10);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 10);
            }

            if (lettersAlphabet.charAt(i) == 'Z' || lettersAlphabet.charAt(i) == 'X' || lettersAlphabet.charAt(i) == 'Q' || lettersAlphabet.charAt(i) == 'J') {
                letterMap.put(lettersAlphabet.charAt(i), 12);
                letterMap.put(lettersAlphabet.toLowerCase().charAt(i), 12);
            }
        }
        
        int totalValue = 0;
        
        for (int x = 0; x < word.length(); x++) {

            totalValue += letterMap.get(word.charAt(x));
        }
        
        return totalValue;
    }
    
}

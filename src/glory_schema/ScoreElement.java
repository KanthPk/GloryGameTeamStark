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
 * @author TeamStark
 */
public class ScoreElement {

    private String word;

    public ScoreElement() {

    }

    public ScoreElement(String word) {
        this.word = word;
    }

    public int getScore() {

        Map<Character, Integer> lettersMap = new HashMap<>();
        String lettersCap = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < lettersCap.length(); i++) {
            if (lettersCap.charAt(i) == 'A' || lettersCap.charAt(i) == 'E'
                    || lettersCap.charAt(i) == 'I' || lettersCap.charAt(i) == 'O'
                    || lettersCap.charAt(i) == 'O' || lettersCap.charAt(i) == 'U'
                    || lettersCap.charAt(i) == 'L' || lettersCap.charAt(i) == 'N'
                    || lettersCap.charAt(i) == 'R' || lettersCap.charAt(i) == 'S'
                    || lettersCap.charAt(i) == 'T') {

                lettersMap.put(lettersCap.charAt(i), 1);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 1);
            }

            if (lettersCap.charAt(i) == 'D' || lettersCap.charAt(i) == 'G') {
                lettersMap.put(lettersCap.charAt(i), 2);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 2);
            }

            if (lettersCap.charAt(i) == 'B' || lettersCap.charAt(i) == 'C'
                    || lettersCap.charAt(i) == 'M' || lettersCap.charAt(i) == 'P') {
                lettersMap.put(lettersCap.charAt(i), 3);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 3);
            }

            if (lettersCap.charAt(i) == 'F' || lettersCap.charAt(i) == 'H'
                    || lettersCap.charAt(i) == 'V' || lettersCap.charAt(i) == 'W'
                    || lettersCap.charAt(i) == 'Y') {
                lettersMap.put(lettersCap.charAt(i), 4);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 4);
            }

            if (lettersCap.charAt(i) == 'K') {
                lettersMap.put(lettersCap.charAt(i), 5);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 5);
            }

            if (lettersCap.charAt(i) == 'J' || lettersCap.charAt(i) == 'X') {
                lettersMap.put(lettersCap.charAt(i), 8);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 8);
            }

            if (lettersCap.charAt(i) == 'Q' || lettersCap.charAt(i) == 'Z') {
                lettersMap.put(lettersCap.charAt(i), 10);
                lettersMap.put(lettersCap.toLowerCase().charAt(i), 10);
            }

        }

        int totalValue = 0;

        for (int j = 0; j < word.length(); j++) {

            totalValue += lettersMap.get(word.charAt(j));
        }

        return totalValue;
    }
}

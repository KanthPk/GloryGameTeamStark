package glory_schema;

import java.util.Hashtable;

/**
 *
 * @author AshanPerera
 */
public class Bag {

    public Hashtable<String, Integer> letters = new Hashtable<String, Integer>();

    public Bag() {
        letters.put("A", 9);//vovel
        letters.put("B", 2);
        letters.put("C", 2);
        letters.put("D", 4);
        letters.put("E", 12);//vovel
        letters.put("F", 2);
        letters.put("G", 3);
        letters.put("H", 2);
        letters.put("I", 9);//vovel
        letters.put("J", 1);
        letters.put("K", 1);
        letters.put("L", 4);
        letters.put("M", 2);
        letters.put("N", 6);
        letters.put("O", 8);//vovel
        letters.put("P", 2);
        letters.put("Q", 1);
        letters.put("R", 6);
        letters.put("S", 4);
        letters.put("T", 6);
        letters.put("U", 4);//vovel
        letters.put("V", 2);
        letters.put("W", 2);
        letters.put("X", 1);
        letters.put("Y", 2);
        letters.put("Z", 1);
    }

    public void addLetter(String letter) {
        int number = letters.get(letter);
        letters.put(letter, number + 1);
    }

    public boolean removeLetter(String letter) {
        int number = letters.get(letter);
        number -= 1;
        if (number == 0) {
            return false;
        } else {
            letters.put(letter, number);
            return true;
        }
    }

    public char randomGen() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int character = (int) (Math.random() * 26);
        String s = alphabet.substring(character, character + 1);
        boolean none = removeLetter(s);
        if (!none) {
            randomGen();
        }
        return s.charAt(0);
    }

    public char takeVowelsRandomically() {
        String Vowels = "AEIOU";
        int character = (int) (Math.random() * 5);
        String s = Vowels.substring(character, character + 1);
        boolean none = removeLetter(s);
        if (!none) {
            randomGen();
        }
        return s.charAt(0);
    }

    public char takeConsonentsRandmoically() {
        String alphabet = "BCDFGHJKLMNPQRSTVWXYZ";
        int character = (int) (Math.random() * 26);
        String s = alphabet.substring(character, character + 1);
        boolean none = removeLetter(s);
        if (!none) {
            randomGen();
        }
        return s.charAt(0);
    }

}

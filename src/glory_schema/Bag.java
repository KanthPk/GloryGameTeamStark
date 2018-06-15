package glory_schema;

import java.util.Hashtable;

/**
 *
 * @author AshanPerera
 */
public class Bag {

    public Hashtable<String, Integer> letters = new Hashtable<String, Integer>();
    private String vowels = "AEIOU";
    private String vowelValidationPerpose = "AEIOU";
    private int vowelLength = 5;
    private String consonents = "BCDFGHJKLMNPQRSTVWXYZ";
    private String consValidationPurpose = "BCDFGHJKLMNPQRSTVWXYZ";
    private String consonentsValidationPerpose = "BCDFGHJKLMNPQRSTVWXYZ";
    private int consonentLength = 21;

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
        int character = (int) (Math.random() * vowelLength);
        String s = vowels.substring(character, character + 1);
        boolean none = removeLetter(s);
        if (!none) {
            takeVowelsRandomically();
        }
        return s.charAt(0);
    }

    public char takeConsonentsDinamiically() {
        int character = (int) (Math.random() * consonentLength);
        String s = consonents.substring(character, character + 1);
        boolean none = removeLetter(s);
        if (!none) {
            takeConsonentsDinamiically();
        }
        return s.charAt(0);
    }

    public String takeVowelString() {
        return vowels;
    }

    public void setNewVowelString(String characterToBeRemoved) {
        vowelLength -= 1;
        vowels = vowels.replace(characterToBeRemoved, "").trim();
    }

    public String takeFilterString() {
        return vowels;
    }

    public String takeConsonentString() {
        return consonents;
    }

    public void setNewConsonent(String value) {
        consonentLength -= 1;
        consonents = consonents.replace(value, "").trim();
    }

    public boolean isVowel(String keyEle) {
        if (vowelValidationPerpose.contains(keyEle)) {
            vowels = vowelValidationPerpose;
            return true;
        } else {
            return false;
        }
    }

    public boolean isConstant(String keyEle) {
        if (consValidationPurpose.contains(keyEle)) {
            consonents = consValidationPurpose;
            return true;
        } else {
            return false;
        }
    }
}

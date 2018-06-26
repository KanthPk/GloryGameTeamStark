package glory_services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class WordAutoGenerate {

    private static final String FILE_NAME = "words.txt";
    private static final long MAX_TIME_SEARCH = 5000;
    private String[] letters;
    private String pattern = "";
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    private String longestWord = "";

    public WordAutoGenerate(String[] letters) {
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

    public void calculateHashMap(String word) {
        for (String l : letters) {
            this.frequency.put(l, StringUtils.countMatches(word, l));
        }
    }

    protected void setPattern() {
        StringBuilder patternLetter = new StringBuilder();
        patternLetter.append("([");
        for (int i = 0; i < letters.length; i++) {
            patternLetter.append(letters[i]);
        }
        patternLetter.append("])");
        patternLetter.append("{0,1}");

        StringBuilder fullPattern = new StringBuilder();
        fullPattern.append("^");
        for (int i = 0; i < letters.length; i++) {
            fullPattern.append(patternLetter.toString());
        }
        fullPattern.append("$");
        this.pattern = fullPattern.toString();
    }

    public boolean checkFrequency(String word) {
        for (String key : this.frequency.keySet()) {
            if (!(StringUtils.countMatches(word, key) <= this.frequency.get(key))) {
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
            while ((line = br.readLine()) != null) {

                m = r.matcher(line);
                if (m.find()) {
                    if (this.longestWord.equals("") && checkFrequency(line)) {
                        this.longestWord = line;
                    } else if ((this.longestWord.length() <= line.length()) && checkFrequency(line)) {
                        this.longestWord = line;
                    }
                }
                if ((System.currentTimeMillis() - startTime)
                        > MAX_TIME_SEARCH) {
                    break;
                }

            }
        } catch (Exception ex) {
        }

    }

    public String getLongestWord() {
        if (longestWord.equals("")) {
            longestWord = "No Words";
        }
        return longestWord;
    }
}

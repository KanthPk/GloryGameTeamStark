package glory_schema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author TeamStark
 */
public class WordElement extends GloryElement {

    public boolean validateWord(String wordField) {

        String word = "";
        int val = 0;
        word = wordField.toLowerCase();
        Scanner file = null;
        try {
            file = new Scanner(new File("words.txt"));
        } catch (FileNotFoundException ex) {

        }
        if (wordField.length() <= 2) {

            if (word.equals("he") || word.equals("me") || word.equals("or") || word.equals("by") || word.equals("i") || word.equals("to") || word.equals("go") || word.equals("do") || word.equals("no")) {
                //return true if it is a meaningful word
                return true;

            } else {
                // return false if the word is not meaningful         
                return false;

            }

        } else if (wordField.length() > 11) {
            // return false if the word is not meaningful
            return false;
        } else {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (line.indexOf(word) != -1) {
                    val = 1;
                    //return true if it is a meaningful word
                    return true;

                } else {
                    val = 0;

                }
            }
            if (val == 0) {

                // return false if the word is not meaningful
                return false;

            }
        }
        return true;
    }
}

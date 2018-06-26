/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_schema;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Scanner;

/**
 *
 * @author TeamStark
 */
public class GloryAward {

    public int GetxpPoints(long elapsed, String checkWord) {
        int xpPoints = 0;
        long remaining = 0;
        long range = 0;
        if (ConstantElement.roundId == 1) {
            remaining = 59000 - elapsed;
            range = 30000;
        } else if (ConstantElement.roundId == 2) {
            remaining = 59000 - elapsed;
            range = 25000;
        } else if (ConstantElement.roundId == 3) {
            remaining = 40000 - elapsed;
            range = 20000;
        } else if (ConstantElement.roundId == 4) {
            remaining = 30000 - elapsed;
            range = 10000;
        } else if (ConstantElement.roundId == 5) {
            return 0;
        }

        if (remaining > range) {
            xpPoints = 250;
        }
        xpPoints = xpPoints + getWordComplexityXp(checkWord);
        System.out.println("############### : word Complextity" + getWordComplexityXp(checkWord));
        return xpPoints;
    }

    public int GetDiamonds(int xpoints) {

        if (xpoints > 0) {
            int diamonds = xpoints / 500;
            return diamonds;
        } else {
            return 0;
        }
    }

    public int getWordComplexityXp(String checkWord) {
        String word = "";
        int val = 0;
        int finalScore = 0;
        word = checkWord.toLowerCase();
        Scanner file = null;
        try {
            file = new Scanner(new File("complex.txt"));
        } catch (FileNotFoundException ex) {
        }
        if (word.length() > 4) {
            while (file.hasNextLine()) {
                String line = file.nextLine();
                if (line.indexOf(word) != -1) {
                    val = 250;
                } else {
                    val = 0;
                }
            }
        } else {
            val = 0;
        }

        return val;
    }
}

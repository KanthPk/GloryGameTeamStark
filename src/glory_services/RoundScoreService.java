/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import glory_schema.ConstantElement;
import glory_schema.ScoreElement;

public class RoundScoreService {

    public int getScoreFromEachRound(int round, String fullQualifiedEnglishWord) {
        try {

            switch (round) {
                case 1: {
                    String valueWord = fullQualifiedEnglishWord;
                    ScoreElement ScoreEleObject = new ScoreElement(valueWord);
                    if (valueWord.length() < ConstantElement.StandardWordLength) {
                        return ScoreEleObject.getScore() - (ConstantElement.StandardDeductPoints + ConstantElement.UnusedLetters);
                    } else if (valueWord.length() == ConstantElement.MaxEnglishWordLength) {
                        return ScoreEleObject.getScore() + ConstantElement.ExtraPointsForWord;
                    }
                    return ScoreEleObject.getScore();
                }
                case 2: {
                    String valueWord = fullQualifiedEnglishWord;
                    ScoreElement ScoreEleObject = new ScoreElement(valueWord);
                    if (valueWord.length() < ConstantElement.StandardWordLength) {
                        return ScoreEleObject.getScore() - (ConstantElement.StandardDeductPoints + ConstantElement.UnusedLetters);
                    } else if (valueWord.length() == ConstantElement.MaxEnglishWordLength) {
                        return ScoreEleObject.getScore() + ConstantElement.ExtraPointsForWord;
                    }
                    return ScoreEleObject.getScore();
                }
                case 3: {
                    String valueWord = fullQualifiedEnglishWord;
                    ScoreElement ScoreEleObject = new ScoreElement(valueWord);
                    if (valueWord.length() < ConstantElement.StandardWordLength) {
                        return ScoreEleObject.getScore() - (ConstantElement.StandardDeductPoints + ConstantElement.UnusedLetters);
                    } else if (valueWord.length() == ConstantElement.MaxEnglishWordLength) {
                        return ScoreEleObject.getScore() + ConstantElement.ExtraPointsForWord;
                    }
                    return ScoreEleObject.getScore();
                }
                case 4: {
                    String valueWord = fullQualifiedEnglishWord;
                    ScoreElement ScoreEleObject = new ScoreElement(valueWord);
                    if (valueWord.length() < ConstantElement.StandardWordLength) {
                        return ScoreEleObject.getScore() - (ConstantElement.StandardDeductPoints + ConstantElement.UnusedLetters);
                    } else if (valueWord.length() == ConstantElement.MaxEnglishWordLength) {
                        return ScoreEleObject.getScore() + ConstantElement.ExtraPointsForWord;
                    }
                    return ScoreEleObject.getScore();
                }
                case 5: {
                    String valueWord = fullQualifiedEnglishWord;
                    ScoreElement ScoreEleObject = new ScoreElement(valueWord);
                    if (valueWord.length() < ConstantElement.StandardWordLength) {
                        return ScoreEleObject.getScore() - (ConstantElement.StandardDeductPoints + ConstantElement.UnusedLetters);
                    } else if (valueWord.length() == ConstantElement.MaxEnglishWordLength) {
                        return ScoreEleObject.getScore() + ConstantElement.ExtraPointsForWord;
                    }
                    return ScoreEleObject.getScore();
                }
                default:
                    throw new Exception("Invalid Operation");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
}

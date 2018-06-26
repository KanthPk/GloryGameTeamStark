/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_services;

import glory_schema.ConstantElement;
import java.sql.Time;

/**
 *
 * @author TeamStark
 */
public class RewardService {

    public RewardService() {

    }

    public int setReward(String caseToID, String englishWord, Time time) {
        try {
            String isGoodQualityEnglishWord = "Comprehensive";
            String isGoodNormalEnglishWord = "Quality";
            String isEasyEnglishWord = "Dog";

            switch (caseToID) {
                case "isGoodQualityEnglishWord": {
                    return ConstantElement.BlueEssenceForFirstClassEnglishWord;
                }
                case "isGoodNormalEnglishWord": {
                    return ConstantElement.BlueEssenceForSecondClassEnglishWord;
                }
                case "isEasyEnglishWord": {
                    return ConstantElement.BlueEssenceForThirdClassEnglishWord;
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
}

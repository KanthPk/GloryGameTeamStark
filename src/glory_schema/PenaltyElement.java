package glory_schema;

public class PenaltyElement {

    public static int calculateRoundPanalty(int RoundScore) {

        if (RoundScore >= 3) {
            return RoundScore - 3;
        } else {
            if (ConstantElement.GlobalScore <= 3) {
                ConstantElement.GlobalScore = 0;
            } else {
                ConstantElement.GlobalScore = ConstantElement.GlobalScore + RoundScore - 3;
            }
            return 0;
        }
    }

}

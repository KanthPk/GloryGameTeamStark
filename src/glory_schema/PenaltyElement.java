package glory_schema;


public class PenaltyElement  {
      
    public static int calculateRoundPanalty(int RoundScore)
    {
        if(RoundScore>0)
        {
         return RoundScore - 3;
        }
        else
        {
                return 0;            
        }
        
        
    }
    
 }


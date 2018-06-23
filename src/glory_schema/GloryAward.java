/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glory_schema;

import java.sql.Time;

/**
 *
 * @author Suba
 */
public class GloryAward {
    
    
    
    public int GetxpPoints(long elapsed)
    { 
        
        long remaining = 0;long range =0 ;
           if (ConstantElement.roundId == 1) {
                remaining =  35000 - elapsed;
                range = 15000;
            } else if (ConstantElement.roundId == 2) {
                remaining =  30000 - elapsed;
                range = 15000;
            } else if (ConstantElement.roundId == 3) {
                remaining =  25000 - elapsed;
                range = 10000;
            } else if (ConstantElement.roundId == 4) {
               remaining =  20000 - elapsed;
               range = 15000;
            } else if (ConstantElement.roundId == 5) {
               remaining =  15000 - elapsed;
               range = 5000;
            }
   
        if(remaining > range){
            return 250;
        }
        else
            return 0;
        }
    
        public int GetDiamonds(int xpoints)
    { 
       
        if(xpoints> 0){
            int diamonds = xpoints/500;
            return diamonds;
        }
        else
            return 0;
        }
     
         
    
    
}

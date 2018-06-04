package glory_schema;

/**
 *
 * @author AshanPerera
 */
public class LetterValueElement extends GloryElement {

    Bag bagObject;
    char []randomLetters;
    
    public LetterValueElement() {
        
        //initialize the bag object
        bagObject = new Bag();
        randomLetters = new char[3];
        
        for(int x=0;x<3;x++){
          randomLetters[x] = bagObject.randomGen();
          //System.out.println(""+randomLetters[x]);
        }
    }
    
    
}

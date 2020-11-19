package de.slfw.comparator;

import java.text.Collator;
import java.util.Locale;

import de.slfw.util.StringUtil;


/**
 * <p>CompareHelper</p>
 * <p>TODO docu me</p>
 *
 * @author Alexander Ströll</a>
 * @version $Revision$
 * */
public class CompareHelper
{
    private final static int NOINDEX = -1;

       
    /**
     * 
     * <p>Zuständig um zu entscheiden, ob etwas vorne oder hinten angefügt werden soll.
     * Bsp: XXXXXXXXXX|ZZZZZZZZZZZ|PPPPPPPPPPP  unterteilung in 3 Bereiche. Wird jetzt mit mode FRONT übergeben,
     * werden alle in sortOrder definierten Keys nach vorne in den X-Bereich sortiert. Dabei wird zusätzlich die
     * Reihenfolge der Keys innerhalb von sortOrder berücksichtigt. Analog funktioniert es für den P-Bereich. Hierzu
     * muss allerdings mit mode END übergeben werden.
     * Im Z Bereich sind keys, die nicht sortiert werden müssen. Also die nicht in "sortOrder" vorhanden sind.
     * Gibt man dem mode SORTED mit, wird der Bereich Z allderdings noch nach der normalSort - Methode sortiert. (alphabetisch und nummerisch)
     * FRONT und END können nicht gleichzeitig übergeben werden!! (Tritt dieser Fall auf wird - falls SORTED an
     * mode übergeben wurde- nur normal sortiert)
     * Sowohl die methode sortFrontOrEnd wie auch die Methode normalSort liefert entweder -1, 0 oder +1 zurück. Diese
     * werden für die Sortierung durch Collections.sort benötigt und geben die Reihenfolge innerhalb der zu sortierenden Collection an.
     * </p>
     * <p>
     * Bsp für das setzten des Modus:
     * comp.setMode(AbstractComparator.FRONT | AbstractComparator.SORTED)
     * </p>
     * 
     * @param sortOrder
     * @param leftkey
     * @param rightkey
     * @return gibt entweder 0 oder +1 zur�ck
     */
    public static int sortFrontOrEnd(String[] sortOrder, String leftkey, String rightkey,int mode){

      
        int indexLeftKey = NOINDEX;
        int indexRightKey = NOINDEX;

        //Falls NONE übergeben wird wird garnicht sortiert
        if((mode & AbstractComparator.NONE) == AbstractComparator.NONE)
      	  return 0;
        
        //Falls Front + End übergeben wird
        if(sortOrder == null || ((mode & (AbstractComparator.END |AbstractComparator.FRONT)) == (AbstractComparator.END | AbstractComparator.FRONT)) ) 
      	  sortOrder = AbstractComparator.NULLARRAY;
        
        //Falls weder FRONT noch END übergeben wurde, wird auf sortOrder keine Rücksicht mehr genommen
        if((mode & AbstractComparator.FRONT) != AbstractComparator.FRONT && (mode & AbstractComparator.END) != AbstractComparator.END)
        	  sortOrder = AbstractComparator.NULLARRAY;	
        
        //es wird ermittelt, ob es sich bei a oder b um einen Key handelt. Wenn es sich um einen Key handelt,
        //wird bei a der Indexwert innerhalb des Arrays in die Variable indexa geschrieben (das gleiche bei b)
        //Bsp: Ein Array(storkeys) mit den Werten {3,4,6,2,3} ist jetzt a = 6 wird indexa=2
        for(int i=0, size=sortOrder.length; i<size; i++){
          if (leftkey!=null){
            if(leftkey.equals(sortOrder[i])) indexLeftKey=i;
          }
          if (rightkey != null){
            if(rightkey.equals(sortOrder[i])) indexRightKey=i;
          }
          if(indexLeftKey != NOINDEX && indexRightKey != NOINDEX) break;
        }
        
        //Wenn beide Werte aus storkeys sind, wir nach der Position überprüft, ob der indexa
        //kleiner als indexb ist. Falls das nicht so ist, wird a nach hinten geschoben 
        if(indexLeftKey != NOINDEX && indexRightKey != NOINDEX){
          return reverse(mode,normalSort(String.valueOf(indexLeftKey), String.valueOf(indexRightKey)));
        }
        
        
        if(indexLeftKey != NOINDEX || indexRightKey != NOINDEX){
  	      if((mode & AbstractComparator.END) == AbstractComparator.END ){
  		          //Durch die Returns in der oberen Zeile ist sichergestellt, dass er an diese Stelle nur kommt, wenn mind
  		          //einer der beiden Index nicht gesetzt ist (also "-1" ist). Ist jetzt indexa nicht -1, wird er nach hinten 
  		          //sortiert, da die Werte in storkeys an das Ende geschrieben werden sollen
  		          if(indexLeftKey != NOINDEX){
  		            return reverse(mode,1);
  		          }
  		          else return reverse(mode,-1);
  	      }
   
  	      if((mode & AbstractComparator.FRONT) == AbstractComparator.FRONT ){
  		          //Durch die Returns in der oberen Zeile ist sichergestellt, dass er an diese Stelle nur kommt, wenn mind
  		          //einer der beiden Index nicht gesetzt ist (also "-1" ist). Ist jetzt indexb nicht -1, wird er nach vorne 
  		          //sortiert, da ja die Werte in storkeys nach vorne sortiert werden sollen
  		          if(indexRightKey != NOINDEX){
  		            return reverse(mode,1);
  		          } 
  		         else return reverse(mode,-1);
  		  }
        }
        else{
  	      if((mode & AbstractComparator.DESC) == AbstractComparator.DESC || (mode & AbstractComparator.ASC) == AbstractComparator.ASC ||  (mode & AbstractComparator.SORTED) == AbstractComparator.SORTED ){

  	  	    	return reverse(mode, ascOrDesc(mode,normalSort(leftkey, rightkey)));

  	      }

        }
        //Sind beide Werte nicht aus "storkeys" und ist SORTED im mode nicht enthalten
        //wird nicht sortiert
        return reverse(mode,ascOrDesc(mode,0));
      
    }

    /**
     * 
     * <p>Eine Methode, die egal welche Zeichen kommen, sortiert. Die Reihenfolge ist:
     * Sonderzeichen, Minuszahlen, Pluszahlen, Strings, Sonstiges(z.b. null)</p>
     * @param leftkey
     * @param rightkey
     * @return -1, 0, 1
     */
    public static int normalSort(String leftkey, String rightkey){
      //falls bei x,y   y == null ist gibt es keine Veränderung. So werden alle
      //null werte nach an das Ende sortiert
    	if(rightkey!= null){
	        //wenn es sich um 2 Zahlen handelt wird verglichen
	        if( StringUtil.isNumeric(leftkey) && StringUtil.isNumeric(rightkey)){
	        	return (Double.valueOf(leftkey)).compareTo(Double.valueOf(rightkey));
	        }  
	        if(leftkey != null){
	          //hier wird nach dem deutschen Alphabet sortiert
	          Collator col = Collator.getInstance( Locale.GERMAN );
	          return col.compare(leftkey, rightkey);
	        }
	        if(!(StringUtil.isNumeric(leftkey))) return 1;
    	}
        return -1;
    }
    
    
    private static int reverse(int mode, int sortKey){
    	if((mode & AbstractComparator.REVERSE) == AbstractComparator.REVERSE){
	    	if(sortKey <= 0){
				return 1;
			}
			return -1;
    	}
    	return sortKey;
    }
    
    private static int ascOrDesc(int mode, int sortKey){
    	
    	if((mode & AbstractComparator.DESC) == AbstractComparator.DESC){
    		if(sortKey == 0){
    			return 0;
    		}
    		if(sortKey == -1){
    			return 1;
    		}
    		return -1;
    	}
    	return sortKey;
    }
}

package startypes;
import java.util.HashMap;
import java.util.Map;
public class StarFactory {
    public static Map<Character, StarType> starTypes = new HashMap<>();

    public static StarType getStarType(char type){
        if(!starTypes.containsKey(type)){
            StarType starType = null;
            switch (type) {
                case 'O' : starType = new OStar(); break;
                case 'A' : starType = new AStar(); break;
                case 'B' : starType = new BStar(); break;
                case 'F' : starType = new FStar(); break;
                case 'G' : starType = new GStar(); break;
                case 'K' : starType = new KStar(); break;
                case 'M' : starType = new MStar(); break;
            }
            starTypes.put(type, starType);
            return starType;
        }
        return starTypes.get(type);
    } 
}

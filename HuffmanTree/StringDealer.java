import java.util.HashMap;
import java.util.Map;

public class StringDealer {
    String string;
    StringDealer(String s){
        this.string = s;
    }
    HashMap<Character, Integer> Deal(){
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0 ; i < string.length() ; i ++){
            map.put(string.charAt(i),map.getOrDefault(string.charAt(i),0)+1);
        }
        return (HashMap<Character, Integer>) map;
    }
}

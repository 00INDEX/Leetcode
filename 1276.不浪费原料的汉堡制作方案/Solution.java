import java.util.*;

public class Solution {
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        for(int i = tomatoSlices / 4; i >= 0; i--){
            int leftCheese = cheeseSlices - i;
            int leftTomato = tomatoSlices - 4 * i;
            if(leftCheese * 2 == leftTomato){
                return Arrays.asList(i, leftCheese);
            }
        }
        return Arrays.asList();
    }
    public static void main(String args[]) {
        System.out.println((new Solution()).numOfBurgers(2, 1));
    }
    
}

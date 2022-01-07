import java.util.ArrayList;

class Solution {

    // AABBCBBDD

    private String s;

    public int minCut(String s) {
        this.s = s;
        ArrayList<Integer> cutPos = new ArrayList<>();
        cutPos.add(0);
        return recursion(cutPos, 0);
    }

    private int recursion(ArrayList<Integer> cutPos, int level){
        if(cutPos.isEmpty()){
            return level;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int pos : cutPos){
            for(int i = pos; i < s.length(); i++){
                for(int j = s.length(); j > i; j--){
                    if(isGoodString(s.substring(i, j))){
                        cutPos.add(j);
                    }
                }
                res.add(recursion(cutPos, level + 1));
            }
        }
        return getMin(res);
    }

    /**
     * @apiNote 检测一个字符串是否为回文字符串
     * @param s:String
     * @return
     */
    private boolean isGoodString(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private int getMin(ArrayList<Integer> array){
        int res = array.get(0);
        for(int i : array){
            if(i < res){
                i = res;
            }
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println((new Solution()).minCut(""));
    }
}
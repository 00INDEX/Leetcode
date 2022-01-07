
public class Solution {
    public int minCostClimbingStairs(int[] costs) {
        if (costs.length <= 2) {
            return costs[0] > costs[1] ? costs[1] : costs[0];
        }
        int bp[] = new int[costs.length];
        bp[costs.length - 1] = costs[costs.length - 1];
        bp[costs.length - 2] = costs[costs.length - 2];
        for (int i = costs.length - 3; i >= 0; i--) {
            if (costs[i] + bp[i + 1] < costs[i] + bp[i + 2]) {
                bp[i] = costs[i] + bp[i + 1];
            } else {
                bp[i] = costs[i] + bp[i + 2];
            }
        }
        return bp[0] > bp[1] ? bp[1] : bp[0];
    }

    public static void main(String args[]) {
        System.out.println((new Solution()).minCostClimbingStairs(new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 }));
    }
}
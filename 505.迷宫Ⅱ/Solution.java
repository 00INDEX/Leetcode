import java.util.*;

public class Solution {
    private ArrayList<Integer> distances = new ArrayList<>();
    private Stack<int[]> passed = new Stack<>();
    int bp[][];

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        this.bp = new int[maze.length][maze[0].length];
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                this.bp[i][j] = -1;
            }
        }
        this.bp[start[0]][start[1]] = 0;
        this.passed.add(start);
        recursion(maze, start, destination, 0);
        if(distances.size() == 0) return -1;
        return Collections.min(distances);
    }

    private void recursion(int[][] maze, int[] start, int[] destination, int cost) {
        if (start[0] == destination[0] && start[1] == destination[1])
            this.distances.add(cost);
        int rowNum = maze.length;
        int columnNum = maze[0].length;
        int i = start[0], j = start[1];
        while (i < rowNum && maze[i][start[1]] == 0)
            i++;
        i--;
        if(bp[i][start[1]] != -1 && bp[start[0]][start[1]] + i - start[0] > bp[i][start[1]]){}
        else if (i != start[0] && !isPassed(new int[] { i, start[1] })){
            passed.push(new int[] { i, start[1] });
            bp[i][start[1]] = bp[start[0]][start[1]] + i - start[0];
            recursion(maze, new int[] { i, start[1] }, destination, cost + i - start[0]);
            passed.pop();
        }

        while (j < columnNum && maze[start[0]][j] == 0)
            j++;
        j--;
        if(bp[start[0]][j] != -1 && bp[start[0]][start[1]] + j - start[1] > bp[start[0]][j]){}
        else if (j != start[1] && !isPassed(new int[] { start[0], j })){
            passed.push(new int[] { start[0], j });
            bp[start[0]][j] = bp[start[0]][start[1]] + j - start[1];
            recursion(maze, new int[] { start[0], j }, destination, cost + j - start[1]);
            passed.pop();
        }
            
        i = start[0]; j = start[1];
        while (i >= 0 && maze[i][start[1]] == 0)
            i--;
        i++;
        if(bp[i][start[1]] != -1 && bp[start[0]][start[1]] + start[0] - i > bp[i][start[1]]){}
        else if (i != start[0] && !isPassed(new int[] { i, start[1] })){
            passed.push(new int[] { i, start[1] });
            bp[i][start[1]] = bp[start[0]][start[1]] + start[0] - i;
            recursion(maze, new int[] { i, start[1] }, destination, cost + start[0] - i);
            passed.pop();
        }
            
        while (j >= 0 && maze[start[0]][j] == 0)
            j--;
        j++;
        if(bp[start[0]][j] != -1 && bp[start[0]][start[1]] + start[1] - j > bp[start[0]][j]){}
        else if (j != start[1] && !isPassed(new int[] { start[0], j })){
            passed.push(new int[] { start[0], j });
            bp[start[0]][j] = bp[start[0]][start[1]] + start[1] - j;
            recursion(maze, new int[] { start[0], j }, destination, cost + start[1] - j);
            passed.pop();
        }
            
    }

    private boolean isPassed(int[] pos){
        for(int[] i : passed){
            if(i[0] == pos[0] && i[1] == pos[1]) return true;
        }
        return false;
    }

    public static void main(String args[]) {
        int start[] = new int[] { 0, 4 };
        int destination[] = new int[] { 4, 4 };
        int maze[][] = new int[5][5];
        maze[0] = new int[] { 0, 0, 1, 0, 0 };
        maze[1] = new int[] { 0, 0, 0, 0, 0 };
        maze[2] = new int[] { 0, 0, 0, 1, 0 };
        maze[3] = new int[] { 1, 1, 0, 1, 1 };
        maze[4] = new int[] { 0, 0, 0, 0, 0 };

        System.out.println((new Solution()).shortestDistance(maze, start, destination));
    }
}

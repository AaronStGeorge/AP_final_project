package scheduling;

import org.junit.*;

import java.util.*;


public class ScheduleTest {

    @Test
    public void testCheckForDuplicates() throws Exception {
        int[] testArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        for (int i = 0; i < 10; i++){
            shuffleArray(testArray);
            Assert.assertTrue(Schedule.checkForDuplicates(testArray));
        }

        testArray = new int[]{1,1,3,4,5,6,7,8,9,10};
        for (int i = 0; i < 10; i++){
            shuffleArray(testArray);
            Assert.assertFalse(Schedule.checkForDuplicates(testArray));
        }

        testArray = new int[]{-1,-1,-1,-1,-1};
        Assert.assertTrue(Schedule.checkForDuplicates(testArray));

        testArray = new int[]{1,1,-1,-1,-1,-1,-1,-1,-1,-1};
        for (int i = 0; i < 10; i++){
            shuffleArray(testArray);
            Assert.assertFalse(Schedule.checkForDuplicates(testArray));
        }
    }

    @Test
    public void testGridValid() throws Exception {
        // test that -1 don't screw anything up
        int[][] testGrid = new int[][]  {
                {2 ,-1,-1},
                {-1, 2,-1},
                {-1,-1, 2},
        };
        Assert.assertTrue(Schedule.gridValid(testGrid));

        testGrid = new int[][]  {
                {1,2,3},
                {2,3,1},
                {3,1,2},
        };
        Assert.assertTrue(Schedule.gridValid(testGrid));

        // check repeat in row returns false
        testGrid[0][0] = 2;
        Assert.assertFalse(Schedule.gridValid(testGrid));
        testGrid[0][0] = 1;

        // check repeat in column returns false
        testGrid[0] = testGrid[1];
        Assert.assertFalse(Schedule.gridValid(testGrid));
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
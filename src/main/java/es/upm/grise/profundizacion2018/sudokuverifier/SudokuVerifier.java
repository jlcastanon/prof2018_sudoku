package es.upm.grise.profundizacion2018.sudokuverifier;

public class SudokuVerifier 
{
    private  int checkRule1(int[][] grid, int min, int max)
    {
        for (int row = 0; row < grid.length; ++row)
        {
            for (int col = 0; col < grid[0].length; ++col)
            {
                if (grid[row][col] < min || grid[row][col] > max)
                {
                    return -1;
                }
            }
        }
        return 0;
    }

    private  int checkRule3(int[][] grid, int whichRow)
    {
        final int size = grid.length;
        boolean[] found = new boolean[size];
        for (int col = 0; col < size; ++col)
        {
            // set found[x - 1] to be true if we find x in the row
            int index = grid[whichRow][col] - 1;
            if (!found[index])
            {
                found[index] = true;
            }
            else
            {
                // found it twice, so return false
                return -3;
            }
        }

        // didn't find any number twice, so return true
        return 0;
    }

    private int checkRule4(int[][] grid, int whichCol)
    {
        final int size = grid.length;
        boolean[] found = new boolean[size];
        for (int row = 0; row < size; ++row)
        {
            // set found[x - 1] to be true if we find x in the row
            int index = grid[row][whichCol] - 1;
            if (!found[index])
            {
                found[index] = true;
            }
            else
            {
                // found it twice, so return false
                return -4;
            }
        }

        // didn't find any number twice, so return true
        return 0;
    }

    private int checkRule2(int[][] grid, int baseRow, int baseCol, int subSquareSize)
    {
        boolean[] found = new boolean[grid.length];
        for (int row = baseRow; row < (baseRow + subSquareSize); ++row)
        {
            for (int col = baseCol; col < (baseCol + subSquareSize); ++col)
            {
                // set found[x - 1] to be true if we find x in the row
                int index = grid[row][col] - 1;
                if (!found[index])
                {
                    found[index] = true;
                }
                else
                {
                    // found it twice, so return false
                    return -2;
                }
            }
        }

        return 0;
    }

    private  int[][] stringGridToIntArrayGrid(String grid){
        int[][] res = new int[9][9];
        int p = 0;
        for(int row=0;row < 9 ;row++){
            for(int col=0;col < 9 ;col++){
                res[row][col] = Character.getNumericValue(grid.charAt(p));
                p++;
            }
        }
        return res;
    }

    public int verify(String candidateSolution) throws Not81NumbersExceptions{
        int res = 0;

        //string grid to int[][] grid
        int[][] grid = stringGridToIntArrayGrid(candidateSolution);
        int size = grid.length;
        //System.out.println(size);
        int subSquareSize = 3;

        //check 81numbers
        int r = candidateSolution.length();

        //System.out.println(candidateSolution.length());
        if ( r > 81  || r<81)
            throw new Not81NumbersExceptions();

        //check rule 1
        res = checkRule1(grid, 1, size);
        if (res < 0)
            return res;

        //check rule 2
        for (int baseRow = 0; baseRow < size; baseRow += subSquareSize){
            for (int baseCol = 0; baseCol < size; baseCol += subSquareSize){
                res = checkRule2(grid, baseRow, baseCol, subSquareSize);
                if (res < 0)
                    return res;
            }
        }

        //check rule 3
        for (int row = 0; row < size; ++row){
            res = checkRule3(grid, row);
            if (res < 0)
                return res;
        }

        //check rule 4
        for (int col = 0; col < size; ++col){
            res = checkRule4(grid, col);
            if (res < 0)
                return res;
        }
        return res;
    }
}

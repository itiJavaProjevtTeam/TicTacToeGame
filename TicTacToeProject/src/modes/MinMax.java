/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modes;

/**
 *
 * @author nermeen
 */
public class MinMax extends Mode 
{
      public int minimax(String [] moves)
        {
            int bestmove = -1;//0:8
            int temp = -1000;//very small
            for(int i = 0; i < 9; i++)
            {
                //if this location is empty 
                if (moves[i].equals("0"))
                {
                    moves[i] = oppSgm;
                    int result = Max_Min(moves,-1);//10 //-10 //0
                    if (result > temp)
                    {
                        bestmove = i;//0
                        temp = result;//10
                    }
                    moves[i] = "0";
                }
            }
            return bestmove;
        }
        //if turn = 1 >>>the computer will play 
        //if turn =-1 the user will play
        public int Max_Min(String [] moves,int turn)
        {
           // Print(n);
            if (isWin())
            {
                return 10;
            }
            
            else if (isLoss())
            {
                return -10;
            }
            else if (isFull())
            {
                return 0;
            }
            //this represent the max
            if (turn == 1)
            {
                turn *= -1;
                int maxscore = -100;
                for (int i = 0; i < 9; i++)
                {
                    if (moves[i].equals("0"))
                    {
                        moves[i] = oppSgm;
                        maxscore = Math.max(maxscore, Max_Min(moves,turn));//10 0 -10
                        moves[i] = "0";
                    }
                }
                return maxscore;
            }
            //this represent the min
            else
            {
                turn *= -1;
                int minscore = 100;
                for (int i = 0; i < 9; i++)
                {
                    if (moves[i].equals("0"))
                    {
                        moves[i] = sgm;
                        //10
                        minscore = Math.min(minscore, Max_Min(moves,turn));//10
                        moves[i] = "0";
                    }
                }
                return minscore;
            }
        }
}

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
public class Mode {
 public String sgm,oppSgm;
 public int Score,oppScore,tieScore,totalScore;
public String[] xo;
  public Mode()
  {
     sgm="X";
     oppSgm="O";
     Score=0;
     oppScore=0;
     tieScore=0;
     totalScore=0;
      xo = new String[9];
      for (int i = 0; i < 9; i++)
                xo[i] = "0";
  }
 public Mode(String sgm,String oppSgm)
 {
      xo = new String[9];
     this.sgm=sgm;
     this.oppSgm=oppSgm;
     Score=0;
     oppScore=0;
     tieScore=0;
 } 
     public boolean isFull()
        {
            for (int i = 0; i < 9; i++)
                if (xo[i] == "0")
                    return false;
            return true;
        }
     
 
        public boolean isLoss()
        {
            //first row
                  if ((xo[0].equals(xo[1]) && xo[1].equals(xo[2]) && xo[2].equals(sgm))
                  || (xo[3].equals(xo[4]) && xo[4].equals(xo[5]) && xo[5].equals(sgm))
                  || (xo[6].equals(xo[7]) && xo[7].equals(xo[8]) && xo[8].equals(sgm))
                  || (xo[0].equals(xo[3]) && xo[3].equals(xo[6]) && xo[6].equals(sgm))
                  || (xo[1].equals(xo[4]) && xo[4].equals(xo[7]) && xo[7].equals(sgm))
                  || (xo[2].equals(xo[5]) && xo[5].equals(xo[8]) && xo[8].equals(sgm))
                  || (xo[0].equals(xo[4]) && xo[4].equals(xo[8]) && xo[8].equals(sgm))
                  || (xo[2].equals(xo[4]) && xo[4].equals(xo[6]) && xo[6].equals(sgm)))
                return true;
            else
                return false;
        }
        public boolean isWin()
        {
            //first row
           if ((xo[0].equals(xo[1]) && xo[1].equals(xo[2]) && xo[2].equals(oppSgm))
                  || (xo[3].equals(xo[4]) && xo[4].equals(xo[5]) && xo[5].equals(oppSgm))
                  || (xo[6].equals(xo[7]) && xo[7].equals(xo[8]) && xo[8].equals(oppSgm))
                  || (xo[0].equals(xo[3]) && xo[3].equals(xo[6]) && xo[6].equals(oppSgm))
                  || (xo[1].equals(xo[4]) && xo[4].equals(xo[7]) && xo[7].equals(oppSgm))
                  || (xo[2].equals(xo[5]) && xo[5].equals(xo[8]) && xo[8].equals(oppSgm))
                  || (xo[0].equals(xo[4]) && xo[4].equals(xo[8]) && xo[8].equals(oppSgm))
                  || (xo[2].equals(xo[4]) && xo[4].equals(xo[6]) && xo[6].equals(oppSgm)))
                return true;
            else
                return false;
        } 
}

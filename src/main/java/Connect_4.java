/**
 * Created by glbes on 24/06/2017.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Connect_4 {
    public static int[][] myframe;
    public static String gameStatus;
    public static int latestRow;


    public static void main(String[] args){
        gameStatus="not yet";
        myframe= new int[4][4];
        commsWelcome();
        Scanner in=new Scanner(System.in);
        int round=1;
        while(gameStatus=="not yet"){
            if(round%2==1){
                Player1(in);
                if(gameStatus=="winner"){
                    commsWinner(1);
                }
                }else{
                Player2(in);
                if(gameStatus=="winner"){
                    commsWinner(2);
                }
                }
                round++;
        }

    }

    /**
     * The actions of the first player (player 1). Two coordinates have to be entered describing the position
     * where player 1 wishes to "enter his chip". First the number of the row is entered,
     * followed by the number of the column. Correct numbers need to be within the boundaries of the playfield (myframe);
     * @param in reads in an integer;
     */


    public static void Player1(Scanner in){
        System.out.println();
        System.out.println("Player 1:");
        commsColumn();
        int column = in.nextInt();
        while(column<0 || column>=myframe[0].length){
            int maxnum = myframe[0].length-1;
            System.out.println("This was out of bounds! Please enter a number between 0 and "+maxnum);
            column = in.nextInt();
        }
        addChipPlayer1(column);
        evaluateGameStatusColumns(column,1);
        evaluateGameStatusRows(1);
        evaluateGameStatusDiagonal(column, 1);
        drawOutput();
    }

    /**
     * The actions of the second player (player 2). Two coordinates have to be entered describing the position
     * where player wishes to "enter his chip". Correct numbers need to be within the boundaries of the playfield (myframe);
     * @param in reads in an integer;
     */

    public static void Player2(Scanner in) {
        System.out.println();
        System.out.println("Player 2:");
        commsColumn();
        int column = in.nextInt();
        while(column<0 || column>=myframe[0].length){
            int maxnum = myframe[0].length-1;
            System.out.println("This was out of bounds! Please enter a number between 0 and "+maxnum);
            column = in.nextInt();
        }
        addChipPlayer2(column);
        evaluateGameStatusColumns(column,2);
        evaluateGameStatusRows(2);
        evaluateGameStatusDiagonal(column, 2);
        drawOutput();
    }

      /**
     * adds a Chip to coordinates provided by player 1;
     * @param column is the number of the column
     */

    public static void addChipPlayer1(int column){
        if(myframe[0][column]!=0){
            System.out.println("Sorry, but this column is already full.");
        }

        boolean setChip = false;
        for(int i = myframe.length-1;i>=0;i--){
            if(myframe[i][column] == 0 && !setChip){
                myframe[i][column]=1;
                setChip = true;
                latestRow = i;
            }
        }

    }

    /**
     * adds a Chip to coordinates provided by player 2;
     * @param column is the number of the column
     */

    public static void addChipPlayer2(int column){
        if(myframe[0][column]!=0){
            System.out.println("Sorry, but this column is already full.");
        }

        boolean setChip = false;
        for(int i = myframe.length-1;i>=0;i--){
            if(myframe[i][column] == 0 && !setChip){
                myframe[i][column]=2;
                setChip = true;
                latestRow = i;
            }
        }

    }

    /**
     * Evaluation of the game status: is column complete?;
     * @param column selected by the player
     * @param playernr is the player number
     */

    public static void evaluateGameStatusColumns(int column, int playernr){
        int sum = 0;
        for(int i = myframe.length-1; i>=0;i--){
            if(myframe[i][column]==playernr){
            sum += myframe[i][column];
        }}
        if(playernr==1){
            if(sum==4){
                gameStatus =  "winner";
            }
        }else{
            if(sum==8){
                gameStatus =  "winner";
            }
        }
    }

    /**
     * Evaluation of the game status: is row complete?;
     * @param playernr is the player number
     */

    public static void evaluateGameStatusRows(int playernr){
        int sum = 0;
        for(int j = myframe[0].length-1; j>=0;j--){
         if(myframe[latestRow][j]==playernr){
            sum += myframe[latestRow][j];
        }}
        if(playernr==1){
            if(sum==4) {
                gameStatus = "winner";
            }
        }else{
            if(sum==8){
                gameStatus =  "winner";
            }
        }
    }

    /**
     * Evaluation of the game status: is diagonal complete?;
     * @param column selected by the player
     * @param playernr is the player number
     */

    public static void evaluateGameStatusDiagonal(int column,int playernr){
        int sum = 0;
        int i=latestRow;
        int j =column;
        while(i>-1 &j<myframe[0].length){
            if(myframe[i][j]==playernr){
            sum+=myframe[i][j];}
            i--;
            j++;
            }
        int z=latestRow+1;
        int y =column-1;
        while(z<myframe.length &y>-1){
            if(myframe[z][y]==playernr){
            sum+=myframe[z][y];}
            z++;
            y--;
        }
        if(playernr==1){
            if(sum==4) {
                gameStatus = "winner";
            }
        }else{
            if(sum==8){
                gameStatus =  "winner";
            }
        }
    }


    /**
     * Communications of the game: Welcome message;
     */

    public static void commsWelcome(){
        System.out.println("Hurray! You chose to play Connect 4!");
        System.out.println("            *******                 ");
        System.out.println("You are playing with a play-field of "+ myframe.length +" rows and "+ myframe[0].length+" columns");
        System.out.println();
        System.out.println("Be bold and make your first move...");
    }

    /**
     * Communications of the game: Initiation of the next round;
     */

    public static void commsColumn(){
        System.out.println("In which slot would you like to drop your chip? Enter number:");
    }

    /**
     * Communications of the game: Winner message;
     * @param playernr is the player number;
     */

    public static void commsWinner(int playernr){
        System.out.println();
        System.out.println("Congratulations!");
        System.out.println();
        System.out.println("###################################");
        System.out.println("######                       ######");
        System.out.println("###### Player "+playernr+" is a winner! ######");
        System.out.println("######                       ######");
        System.out.println("###################################");
    }

    /**
     * draws the Output of myframe;
     * @param
     */

    public static void drawOutput(){
        System.out.println(" --- --- --- ---");
        for(int i=0;i<myframe.length;i++){
            for(int j=0;j<myframe[0].length;j++){
                System.out.print("| "+myframe[i][j]+" ");
            }System.out.println("|");
            System.out.println(" --- --- --- ---");
        }
    }
}

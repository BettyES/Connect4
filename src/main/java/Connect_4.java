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
        myframe= new int[6][7];
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
        evaluateGameStatusColumn(column,1);
        evaluateGameStatusRows(column,1);
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
        evaluateGameStatusColumn(column,2);
        evaluateGameStatusRows(column,2);
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
     * Evaluation of the game status: is diagonal complete?;
     * @param column selected by the player
     * @param playernr is the player number
     */


    public static void evaluateGameStatusDiagonal(int column,int playernr){
        int i = latestRow;
        int j = column;
        int sum = 0;
        int start = column;
        boolean startFound = false;
        for(int num=0;num<4;num++){
            if(j-num>=0 && i+num<myframe.length && !startFound){
                System.out.println("ha" +num);
                System.out.println(j-num);
                System.out.println(myframe[0].length);
                if(j-num==0 || i+num==myframe.length || myframe[i+num+1][j-num-1]!=playernr ){
                    start += num;
                    startFound = true;
                }
            }}
        //the idea is to go as far to the lower left as possible and start counting. The same
        // has to be written for the other direction

        int start_i = i+start;
        int start_j = j-start;

        for(int num=0;num<4;num++){
            if(start_i-num>=0 && start_j+num<=myframe[0].length){
                if(myframe[start_i-num][start_j+num]==playernr){
                    sum+= myframe[start_i-num][start_j+num];
                    System.out.println("SUM "+sum);
                }}
        }

        int sumR = 0;
        int startR = column;
        boolean startFoundR = false;
        for(int num=0;num<4;num++){
            if(j+num>=0 && i-num<myframe.length && !startFoundR){
                System.out.println("ha" +num);
                System.out.println(j-num);
                System.out.println(myframe[0].length);
                if(j+num==0 || i-num==myframe.length || myframe[i-num-1][j+num+1]!=playernr ){
                    startR += num;
                    startFoundR = true;
                }
            }}
        //the idea is to go as far to the lower left as possible and start counting. The same
        // has to be written for the other direction

        int startR_i = i+startR;
        int startR_j = j+startR;

        for(int num=0;num<4;num++){
            if(startR_i-num>=0 && startR_j-num>=0){
                if(myframe[startR_i-num][startR_j-num]==playernr){
                    sumR+= myframe[startR_i-sum][startR_j-num];
                    System.out.println("SUMR "+sumR);
                }}
        }


        if(playernr==1){
            if(sum==4 || sumR==4) {
                gameStatus = "winner";
            }
        }else{
            if(sum==8 || sumR==8){
                gameStatus =  "winner";
            }
        }
    }


    /**
     * Evaluation of the game status: is a row complete?;
     * @param playernr is the player number
     * @param column column selected by player
     */

    public static void evaluateGameStatusRows(int column,int playernr){
        int i = latestRow;
        int j = column;
        int sum = 0;
        int start = column;
        boolean startFound = false;
        for(int num=0;num<4;num++){
            if(j+num<myframe[0].length && !startFound){
                System.out.println("ha" +num);
                System.out.println(j+num);
                System.out.println(myframe[0].length);
                if(j+num==myframe[0].length-1 || myframe[i][j+num+1]!=playernr ){
                    start += num;
                    startFound = true;
                }
            }}


        for(int num=0;num<4;num++){
            if(start-num>=0){
                if(myframe[i][start-num]==playernr){
                    sum+= myframe[i][start-num];
                    System.out.println("SUM "+sum);
                }}
        }
        System.out.println(start + "sum" + sum);

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
     * Evaluation of the game status: is column complete?;
     * @param column selected by the player
     * @param playernr is the player number
     */

    public static void evaluateGameStatusColumn(int column,int playernr){
        int i = latestRow;
        int j = column;
        int sum = 0;

        for(int num=0;num<4;num++){
            if(i+num<myframe.length){
            if(myframe[i+num][j]==playernr){
                sum+= myframe[i+num][j];//issue if there is a  2 in between he will still continue to count
            }}
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
        String star = "\u2605";
        System.out.println();
        System.out.println("Congratulations! "+star+star+star);
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
        System.out.println(" --- --- --- --- --- --- ---");
        for(int i=0;i<myframe.length;i++){
            for(int j=0;j<myframe[0].length;j++){
                System.out.print("| "+myframe[i][j]+" ");
            }System.out.println("|");
            System.out.println(" --- --- --- --- --- --- ---");
        }
    }
}

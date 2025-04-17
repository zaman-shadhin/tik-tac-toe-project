//                 SAY
//                        MY
//                               NAME

//                                         SHADHIN (24070101 cse43rd)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class TicTacToegame {
    static ArrayList<Integer> playerposition = new ArrayList<>();
    static ArrayList<Integer> cpuposition = new ArrayList<>();

    public static void main(String[] args) {
        
        char[][] gameboard = {{' ','|',' ','|',' '}, 
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '},
                              {'-','+','-','+','-'},
                              {' ','|',' ','|',' '}};
                              
        printboard(gameboard); 

        while(true){

            //player movements
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int pos =input.nextInt();
            while(playerposition.contains(pos) || cpuposition.contains(pos) ){
                System.out.println("This Position is taken Blind... Try the available one");
                pos =input.nextInt();
            } 
            placepiece(gameboard ,pos ,"player");
            printboard(gameboard);
            String result = checkwinner();
            if(result.length()>0){
                
                System.out.println(result);
                break;
            }
    

            //cpu movements :{
            Random rand = new Random();
            int cpupos = rand.nextInt(9)+1;
            while(playerposition.contains(cpupos)||cpuposition.contains(cpupos)){
                cpupos = rand.nextInt(9)+1;
            }
            placepiece(gameboard ,cpupos ,"cpu");
      
            System.out.println("Cpu placement is : "+cpupos);
            printboard(gameboard);

            result = checkwinner();
            if(result.length()>0){
                System.out.println(result);
                break;
            }    
        }      
    }

    public static void printboard(char[][] gameboard){
        for (char [] row : gameboard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println( );
        }
    }                                      
    public static void placepiece(char[][] gameboard,int pos, String user) {
        
        char symbol = 'X';
        if(user.equals("player")){
            symbol ='X';
            playerposition.add(pos);

        } else if(user.equals("cpu")){
            symbol = 'O';
            cpuposition.add(pos);

        }        
        switch(pos){
            case 1:
            gameboard[0][0] = symbol;
            break;
            case 2:
                gameboard[0][2] = symbol;
                break;
             case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;
            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
               break;
        }
    }

    public static String checkwinner() {
        
        List toprow = Arrays.asList(1,2,3);
        List midrow = Arrays.asList(4,5,6);
        List botrow = Arrays.asList(7,8,9);
        List topcol = Arrays.asList(1,4,7);
        List midcol = Arrays.asList(2,5,8);
        List botcol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(topcol);
        winning.add(midcol);
        winning.add(botcol);
        winning.add(cross1);
        winning.add(cross2);

        for(List Y : winning){
            if(playerposition.containsAll(Y)){
              return "Congratulations NOOB you WON :) ";   
              }
            else if(cpuposition.containsAll(Y)){
                return"HAHAHA NOOB CPU WON :( ";
            }
            else if (playerposition.size()+cpuposition.size() == 9){
                return "DRAW :: NICE TRY DIDDY  ";
            }
        }
        return "";    
    }

}
// every line er code nije liksi sir (you tube er help niya) :)
// sir bissash koren shob nijei jene buje liksi

// sir number ta ektu valo diyen sir please 
// ei semister e COMEBACK korte hobe 

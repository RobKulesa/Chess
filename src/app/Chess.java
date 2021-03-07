package app;

import structures.Board;
import java.util.Scanner;
//import structures.pieces.*;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public class Chess {
    public static void main(String[] args) {
        int turn = 1;
        Board board = new Board();
        board.printBoard();
        System.out.println();
        boolean gameContinue = true;
        Scanner sc = new Scanner(System.in);
        while(gameContinue){
            if(turn % 2 == 0)
                System.out.print("Black's move: ");
            else
                System.out.print("White's move: ");
            sc.nextLine();
            //gameContinue = board.movePiece(cmdString);
            System.out.println();
            board.printBoard();
            System.out.println();

        }
        sc.close();
    }
}
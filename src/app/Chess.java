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
    	//Test boolean for debugging
	public static boolean debug = true;
	
	public enum cmdType{
		INVALID, MOVECMD, DRAWREQUEST, RESIGN, PROMO
	}
	
	public static void printWinner(int turnCount) {
		if(turnCount%2==0)
			System.out.println("Black wins");
		else
			System.out.println("White wins");
	}
	
	public static String promptUserInput(Scanner sc, int turnCount) {
		
		if(turnCount%2 == 0) 
			System.out.print("White's");
		else
			System.out.print("Black's");
		System.out.print(" move: ");
		String usrInpt = sc.nextLine();
		return usrInpt.toLowerCase();
	}
	
	public static cmdType isValidInpt(String s) {
		if(debug) {
			if(s.equals("debugDraw"))
				return cmdType.DRAWREQUEST;
			if(s.equals("debugCont"))
				return cmdType.MOVECMD;
			if(s.equals("debugResign"))
				return cmdType.RESIGN;
		}
		if(s.equals("resign")){
            return cmdType.RESIGN;
        }
        else if(s.substring(s.length()-5).equals("draw?")){
            return cmdType.DRAWREQUEST;
        } else {
            return cmdType.MOVECMD;
        }
	}
	
	/*
	 * 1. Must initialize the board
	 * 2. Must set up scanner
	 * 3. Must set up a loop
	 * 		3.1   
	 */
	public static void main(String[] args) {
		Board b = new Board();
		Scanner sc = new Scanner(System.in);
		boolean gameContinue = true;
		int turnCount = 0;
		while(gameContinue) {
			//PRINT STATEMENTS
			b.printBoard();			
			String usrInpt = promptUserInput(sc, turnCount);
			cmdType usrCmdType = isValidInpt(usrInpt);
			
			System.out.println();
			switch(usrCmdType) {
				case INVALID:
					System.out.println("\nInvalid Input Detected, please try again.");
					break;
				case MOVECMD:
					boolean validCmd = b.checkString(usrInpt);
					while(!validCmd) {
						System.out.println("Illegal move, try again");
						usrInpt = promptUserInput(sc, turnCount);
						if(isValidInpt(usrInpt) == cmdType.MOVECMD &&  b.checkString(usrInpt))
							validCmd = true;
					}
					turnCount++;
					b.movePiece(usrInpt);
					//if(board.isCheckMate()) {
					//	printWinner(turnCount);
					//	gameContinue = false;
					//}
					//if(board.inCheck())
					//	System.out.println("Check");
					break;
				case DRAWREQUEST:
					System.out.println("draw");
					gameContinue = false;
					break;					
				case RESIGN:
					printWinner(turnCount);
					gameContinue = false;
					break;
				default:
					if(debug) {
						System.out.println("Error: Default of switch statement reached");
					}
					
					
			}
		}
	}
}

/*
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
*/
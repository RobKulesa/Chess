package app;

import structures.Board;
import structures.pieces.Piece;
import java.util.Scanner;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Chess is the class that creates the board instance to run the game,
 * and interfaces with the players to communicate their commands to move pieces on the chess board.
 * It keeps track of the number of turns that have been taken, and when the chess game reaches
 * an end case, it communicates to the players which team won, ends the game, and stops the program.
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public class Chess {
    /**
	 * Test boolean used for debugging
	 */
	public static boolean debug = true;
	

	/**
	 * Represents all the different kinds of commands a user-input could be
	 */
	public enum cmdType{
		INVALID, MOVECMD, DRAWREQUEST, RESIGN
	}
	
	
	/** 
	 * Prints the winning team of the current game.
	 * 
	 * @param turnCount 	the number of turns that have been taken in the current game
	 */
	public static void printWinner(int turnCount) {
		if(turnCount%2==0)
			System.out.println("Black wins");
		else
			System.out.println("White wins");
	}
	
	
	/** 
	 * Prompts the user for their desired move.
	 * 
	 * @param sc 	        the {@link Scanner} object used to scan input from the java console
	 * @param turnCount 	the number of turns that have been taken in the current game
	 * @return              String command passed by a player from the console
	 */
	public static String promptUserInput(Scanner sc, int turnCount) {
		if(turnCount%2 == 0) 
			System.out.print("White's");
		else
			System.out.print("Black's");
		System.out.print(" move: ");
		String usrInpt = sc.nextLine();
		return usrInpt.toLowerCase();
	}
	
	
	/** 
	 * Analyzes the input string to determine the type of command given by the player.
	 * 
	 * @param s 	the string command sent by the player
	 * @return 		cmdType of either resign command, draw command, or move command
	 */
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
        }else {
            return cmdType.MOVECMD;
        }
	}
	
	
	/** 
	 * Creates and holds the board instance for the current game. 
	 * Takes in user input for each turn, and determines the command type to be:
	 * <ul>
	 * <li>An invalid command: The commmand is ignored and we ask for user input
	 * again.
	 * <li>A move command: The move command is checked for validity. If it is
	 * not valid, it requests user input again. If it is valid, the move is
	 * executed and the board is evaluated for end (checkmate) conditions.
	 * If the board is in an end condition, the winner is printed and the game
	 * ends. Otherwise, the game continues.
	 * <li>A draw command: The game ends in a draw.
	 * <li>A resign command: The game ends with the resigning team losing.
	 * </ul>
	 * 
	 * @param args an array of command-line arguments for the application
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
					boolean validCmd = b.checkString(usrInpt, turnCount);
					while(!validCmd) {
						System.out.println("Illegal move, try again");
						usrInpt = promptUserInput(sc, turnCount);
						if(isValidInpt(usrInpt) == cmdType.MOVECMD &&  b.checkString(usrInpt, turnCount))
							validCmd = true;
					}
					turnCount++;
					b.movePiece(usrInpt);
					if(b.isTeamInCheckMate(turnCount % 2 == 0 ? Piece.WHITE : Piece.BLACK)) {
						System.out.println("Checkmate");
						gameContinue = false;
						printWinner(turnCount);
					}
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
						System.out.println("Error: Default of switch statement in Chess.java reached");
					}
			}
		}
	}
}
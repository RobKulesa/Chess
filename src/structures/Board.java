package structures;

import structures.pieces.*;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment 
 * @author Rob Kulesa
 * @author Aaron Kan
 *
 * Board will be made of Spots and can print its current state
 */
public class Board {
    public static 


    Spot[][] spotMat;

    public Board() {
        spotMat = new Spot[8][8];
        for(int i = 0; i < spotMat[0].length; i++) {
            for(int j = 0; j < spotMat[i].length; j++) {
                spotMat[i][j] = new Spot(i+1, j+1);
            }
        }
        initBoard();
    }

    public void printBoard() {
        System.out.println("\n Current Board Status: ");
        for(int i = 0; i < spotMat.length; i++) {
            for(int j = 0; j < spotMat[i].length; j++) {
                Spot currSpot = spotMat[i][j];
                if(currSpot.getPiece() == null) {
                    if((currSpot.getX() + currSpot.getY()) % 2 == 0)
                        System.out.print("##");
                    else
                        System.out.print("  ");
                } else{
                    System.out.print(currSpot.getPiece().getPieceType());
                }
                System.out.print(" ");
            }
            System.out.printf("%d\n", 8-i);
        }
        for(int i = 0; i < 8; i++) {
            System.out.printf(" %c ", ('a'+ i));
        }
        System.out.println();
    }

    public void initBoard() {
        //Pawns
        for(int i = 0; i < 8; i++) {
            spotMat[1][i].setPiece(new Pawn(Piece.WHITE));
            spotMat[6][i].setPiece(new Pawn(Piece.BLACK));
        }
        //White Pieces
        spotMat[0][0].setPiece(new Rook(Piece.WHITE));
        spotMat[0][7].setPiece(new Rook(Piece.WHITE));
        spotMat[0][1].setPiece(new Knight(Piece.WHITE));
        spotMat[0][6].setPiece(new Knight(Piece.WHITE));
        spotMat[0][2].setPiece(new Bishop(Piece.WHITE));
        spotMat[0][5].setPiece(new Bishop(Piece.WHITE));
        spotMat[0][3].setPiece(new Queen(Piece.WHITE));
        spotMat[0][4].setPiece(new King(Piece.WHITE));

        //Black Pieces
        spotMat[7][0].setPiece(new Rook(Piece.BLACK));
        spotMat[7][7].setPiece(new Rook(Piece.BLACK));
        spotMat[7][1].setPiece(new Knight(Piece.BLACK));
        spotMat[7][6].setPiece(new Knight(Piece.BLACK));
        spotMat[7][2].setPiece(new Bishop(Piece.BLACK));
        spotMat[7][5].setPiece(new Bishop(Piece.BLACK));
        spotMat[7][4].setPiece(new Queen(Piece.BLACK));
        spotMat[7][3].setPiece(new King(Piece.BLACK));


    }

    public boolean gameContinue(){
        return false;
    }

    public boolean checkMove(String cmdString) {
        //Check cmdString validity
        if(cmdString.length()!= 5 || cmdString.charAt(2) != ' ')
            return false;
        if(cmdString.charAt(0) < 'a' || cmdString.charAt(0) > 'h' || cmdString.charAt(3) < 'a'|| cmdString.charAt(3) > 'h')
            return false;
        if(cmdString.charAt(1) < '1' || cmdString.charAt(1) > '8' || cmdString.charAt(4) < '1'|| cmdString.charAt(4) > '8')
            return false;
        
        int xCoordFrom = Integer.parseInt(String.valueOf(cmdString.charAt(1)));
        int yCoordFrom = Integer.parseInt(String.valueOf(cmdString.charAt(4)));


        return false;
    }

    //Returns a boolean that tells Chess.Java if the game is still going on
    public boolean movePiece(String cmdString) {
        if(!this.checkMove(cmdString)){
            System.out.println("Illegal move, try again");
            return false;
        } else {
            //Actually move the piece
            return this.gameContinue();
        }
    }




}

package structures;

import structures.pieces.*;
import java.util.*;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment 
 * @author Rob Kulesa
 * @author Aaron Kan
 *
 * Board will be made of Spots and can print its current state
 */
public class Board { 
    
    Spot[][] spotMat;

    public Board() {
        this.spotMat = new Spot[8][8];
        for(int i = 0; i < spotMat[0].length; i++) {
            for(int j = 0; j < spotMat[i].length; j++) {
                this.spotMat[i][j] = new Spot(i+1, j+1);
            }
        }
        initBoard();
    }

    //public String coordString
    
    public void printBoard() {
        System.out.println("\n Current Board Status: ");
        for(int j = 0; j < spotMat.length; j++) {
            for(int i = 0; i < spotMat[j].length; i++) {
                Spot currSpot = spotMat[i][j];
                if(currSpot.getPiece() == null) {
                    if((currSpot.getX() + currSpot.getY()) % 2 == 1)
                        System.out.print("##");
                    else
                        System.out.print("  ");
                } else{
                    System.out.print(currSpot.getPiece().getPieceType());
                }
                System.out.print(" ");
            }
            System.out.printf("%d\n", 8-j);
        }
        for(int i = 0; i < 8; i++) {
            System.out.printf(" %c ", ('a'+ i));
        }
        System.out.println();
    }

    public void initBoard() {
        //Pawns
        /*
        for(int i = 0; i < 8; i++) {
            spotMat[i][1].setPiece(new Pawn(Piece.BLACK));
            spotMat[i][6].setPiece(new Pawn(Piece.WHITE));
        }
        //BLACK Pieces
        spotMat[0][0].setPiece(new Rook(Piece.BLACK));
        spotMat[7][0].setPiece(new Rook(Piece.BLACK));
        spotMat[1][0].setPiece(new Knight(Piece.BLACK));
        spotMat[6][0].setPiece(new Knight(Piece.BLACK));
        spotMat[2][0].setPiece(new Bishop(Piece.BLACK));
        spotMat[5][0].setPiece(new Bishop(Piece.BLACK));
        spotMat[3][0].setPiece(new Queen(Piece.BLACK));
        spotMat[4][0].setPiece(new King(Piece.BLACK));

        //WHITE Pieces
        spotMat[0][7].setPiece(new Rook(Piece.WHITE));
        spotMat[7][7].setPiece(new Rook(Piece.WHITE));
        spotMat[1][7].setPiece(new Knight(Piece.WHITE));
        spotMat[6][7].setPiece(new Knight(Piece.WHITE));
        spotMat[2][7].setPiece(new Bishop(Piece.WHITE));
        spotMat[5][7].setPiece(new Bishop(Piece.WHITE));
        spotMat[3][7].setPiece(new Queen(Piece.WHITE));
        spotMat[4][7].setPiece(new King(Piece.WHITE));

        */
        spotMat[0][0].setPiece(new Rook(Piece.BLACK));
        spotMat[7][0].setPiece(new Rook(Piece.BLACK));
        spotMat[4][0].setPiece(new King(Piece.BLACK));
        spotMat[1][0].setPiece(new Knight(Piece.BLACK));

    }

    public boolean gameContinue(){
        return false;
    }

    public boolean checkString(String cmdString) {
        //Check cmdString validity
        System.out.println("Checking cmdString validity");
        if(cmdString.length() != 5 || cmdString.charAt(2) != ' ')
            return false;
        if(cmdString.charAt(0) < 'a' || cmdString.charAt(0) > 'h' || cmdString.charAt(3) < 'a'|| cmdString.charAt(3) > 'h')
            return false;
        if(cmdString.charAt(1) < '1' || cmdString.charAt(1) > '8' || cmdString.charAt(4) < '1'|| cmdString.charAt(4) > '8')
            return false;
    
        Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];
        Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
        return checkMove(from, to);
    }

    public boolean checkMove(Spot from, Spot to) {
        System.out.println("Checking if designated coordinate has a piece");
        System.out.println("Getting Path from piece");
        if(from.getPiece() == null) return false;
        ArrayList<Spot> path = from.getPiece().getPath(from, to);
        System.out.println(path);
        //Check if any pieces in the way except for final spot in path
        if(path.isEmpty() || piecesInWay(path, from.getPiece().getTeam())) return false;

        //Check if the move in question is an attempt for castling
        if(from.getPiece().getPieceType().charAt(1) == 'K' && path.size() == 2){
            //Check which rook is affected
            System.out.println("Castling attempt detected");
            Spot rookSpot;
            Piece rook;
            if(to.getX()-from.getX() > 0){
                rookSpot = spotMat[7][from.getY()-1];
            } else {
                rookSpot = spotMat[0][from.getY()-1];
            }
            System.out.println(rookSpot);
            rook = rookSpot.getPiece();
            
            if(rook == null || rookSpot.getPiece().getPieceType().charAt(1) != 'R' || rook.hasMoved()) return false;
            else{
                System.out.println("Checking rook path");
                return checkMove(rookSpot, path.get(0));
            }
        }


        return true;
    }

    public boolean piecesInWay(ArrayList<Spot> path, int team) {
        for(Spot pathSpot : path) {
            Spot boardSpot = this.spotMat[pathSpot.getX()-1][pathSpot.getY()-1];
            if(path.indexOf(pathSpot) != path.size() - 1 && boardSpot.getPiece() != null) { //any piece in way of path except end
                return true;
            }
            else if(boardSpot.getPiece() != null && boardSpot.getPiece().getTeam() == team) { //friendly piece at end of path
                return true;
            }
        }
        return false;
    }
    //Returns a boolean that tells Chess.Java if the game is still going on
    public boolean movePiece(String cmdString) {
        if(!this.checkString(cmdString)){
            System.out.println("Illegal move, try again");
            return false;
        } else {
            //Actually move the piece
            
            
            Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];
            Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
            
            //Check if the move in question is an attempt for castling
        if(from.getPiece().getPieceType().charAt(1) == 'K' && Math.abs(from.getX() - to.getX()) > 1){
            //Check which rook is affected
            System.out.println("Castling attempt detected");
            Spot rookFromSpot;
            Spot rookToSpot;
            Piece rook;
            if(to.getX()-from.getX() > 0){
                
                rookFromSpot = spotMat[7][from.getY()-1];
                rookToSpot = spotMat[from.getX()][from.getY()-1];

            } else {
                rookFromSpot = spotMat[0][from.getY()-1];
                rookToSpot = spotMat[from.getX()-2][from.getY()-1];
            }
            System.out.println(rookFromSpot);
            System.out.println(rookToSpot);
            rook = rookFromSpot.getPiece();
            rookFromSpot.setPiece(null);
            rookToSpot.setPiece(rook);
        }
            
            /**
             * before move is made, evaluate check status to see if the move will put your own king in check. If so it is not a valid move
             * After move is made, evaluate check status to see if any king is in check. If so we have to print that the king is in check
             */
            to.setPiece(from.getPiece());
            from.setPiece(null);
            to.getPiece().incrementMoveCount();
            


            //return this.gameContinue();
            return true;
        }
    }

    /**
     * Split into find king location
     * Change evaluate check status to evaluate check status of specific king
     */

    public Spot findKing(int team) {
        for(int j = 0; j < spotMat.length; j++) {
            for(int i = 0; i < spotMat[j].length; i++) {
                Spot currSpot = spotMat[i][j];
                if(currSpot.getPiece() != null) {
                    Piece currPiece = currSpot.getPiece();
                    if(currPiece instanceof King && currPiece.getTeam() == team) {
                        return currSpot;
                    }
                }
            }
        }
        return null;
    }
   
   //Argument: Proposed SPOT location of THE KING, and the team that may or may not be in check
    public void evaluateCheckStatus() {
        //Find where both kings are located
        

        //Both kings have been found at this point. Begin evaluating check status for both kings
        for(int j = 0; j < spotMat.length; j++) {
            for(int i = 0; i < spotMat[j].length; i++) {
                Spot currSpot = spotMat[i][j];
                if(currSpot.getPiece() != null) {
                    Piece currPiece = currSpot.getPiece();
                    Spot enemyKingSpot = currPiece.getTeam() == Piece.WHITE ? whiteKingSpot : blackKingSpot;
                    King enemyKing = (King) enemyKingSpot.getPiece();
                    if(checkMove(currSpot, enemyKingSpot)) {
                        enemyKing.setInCheck(true);
                    }    
                }
            }
        }
        return;
    }
}

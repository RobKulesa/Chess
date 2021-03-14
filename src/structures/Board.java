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
        this.spotMat[3][7].setPiece(new Bishop(Piece.WHITE));
        this.spotMat[3][0].setPiece(new King(Piece.BLACK));

    }

    public boolean gameContinue(){
        return false;
    }

    public boolean checkMove(String cmdString) {
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

        System.out.println("Checking if designated coordinate has a piece");
        
        /* for(int j = 0; j < spotMat.length; j++) {
            for(int i = 0; i < spotMat[j].length; i++) {
                System.out.println(spotMat[i][j]);
            }
        }
        if(from.getPiece() == null){
            System.out.println(from);
            System.out.println("No piece was found");
            return false;
        } */
            
        System.out.println("Getting Path from piece");
        
        ArrayList<Spot> path = from.getPiece().getPath(from, to);
        System.out.println(path);
        //Check if any pieces in the way except for final spot in path
        if(path.isEmpty() || piecesInWay(path, from.getPiece().getTeam())) return false;

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
            //TODO: need to implement en passant for pawns and castling for kings and other potential special move cases
        }
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

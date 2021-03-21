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
    Spot lastMove;

    public Board() {
        this.spotMat = new Spot[8][8];
        for(int i = 0; i < spotMat[0].length; i++) {
            for(int j = 0; j < spotMat[i].length; j++) {
                this.spotMat[i][j] = new Spot(i+1, j+1);
            }
        }
        initBoard();
    }

    public Board makeCopy(){
        Board copy = new Board();
        copy.spotMat = new Spot[8][8];
        for(int i = 0; i < this.spotMat[0].length; i++) {
            for(int j = 0; j < this.spotMat[i].length; j++) {
                copy.spotMat[i][j] = new Spot(i+1, j+1);
                Piece oldPiece = this.spotMat[i][j].getPiece();
                Piece newPiece = null;
                if(oldPiece != null){
                    if(oldPiece instanceof Pawn)
                        newPiece = new Pawn(oldPiece.getTeam());
                    else if(oldPiece instanceof Rook)
                        newPiece = new Rook(oldPiece.getTeam());
                    else if(oldPiece instanceof Knight)
                        newPiece = new Knight(oldPiece.getTeam());
                    else if(oldPiece instanceof Bishop)
                        newPiece = new Bishop(oldPiece.getTeam());
                    else if(oldPiece instanceof Queen)
                        newPiece = new Queen(oldPiece.getTeam());
                    else if(oldPiece instanceof King)
                        newPiece = new King(oldPiece.getTeam());
                }
                copy.spotMat[i][j].setPiece(newPiece);
            }
        }
        return copy;
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
        spotMat[4][0].setPiece(new King(Piece.BLACK));
        spotMat[4][7].setPiece(new King(Piece.WHITE));
        spotMat[1][6].setPiece(new Pawn(Piece.BLACK));

    }

    public boolean gameContinue(){
        return false;
    }

    public boolean checkString(String cmdString) {
        //Check cmdString validity
        System.out.println("Checking cmdString validity");
        if((cmdString.length() != 5 && cmdString.length() != 7)|| cmdString.charAt(2) != ' ')
            return false;
        if(cmdString.charAt(0) < 'a' || cmdString.charAt(0) > 'h' || cmdString.charAt(3) < 'a'|| cmdString.charAt(3) > 'h')
            return false;
        if(cmdString.charAt(1) < '1' || cmdString.charAt(1) > '8' || cmdString.charAt(4) < '1'|| cmdString.charAt(4) > '8')
            return false;
    
        Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];
        Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
        Character promoRequest = null;
        if(cmdString.length() == 7 ){
            promoRequest = cmdString.charAt(cmdString.length()-1);
        }
        
        return checkMove(from, to, promoRequest);
    }

    public boolean checkMove(Spot from, Spot to, Character promoRequest) {
        System.out.println("Checking if designated coordinate has a piece");
        System.out.println("Getting Path from piece");
        if(from.getPiece() == null) return false;
        ArrayList<Spot> path = from.getPiece().getPath(from, to);
        System.out.println(path);
        //Check if any pieces in the way except for final spot in path
        if(path.isEmpty() || piecesInWay(path, from.getPiece().getTeam())) return false;

        //Check if its a valid promotion request
        if(promoRequest != null && ((to.getY()!= 8 && to.getY()!= 1)  || !(from.getPiece() instanceof Pawn))){
            System.out.println("Invalid promotion request detected");
            return false;
        }
        
        //Check if the move is enpassant
        if(from.getPiece() instanceof Pawn && (Math.abs(to.getY() - from.getY()) == 1 && Math.abs(to.getX() - from.getX()) == 1) && to.getPiece() == null){
            Spot enPassantSpot = spotMat[to.getX() - 1][from.getY() - 1];
            Piece capturedEnpassant = enPassantSpot.getPiece();
            if(!(capturedEnpassant instanceof Pawn) || !capturedEnpassant.hasMovedOnce() || !enPassantSpot.equals(this.lastMove)
                || (enPassantSpot.getY()!= 5 && enPassantSpot.getY()!=4))
                return false;
            
        }
        
        //Check if the move in question is an attempt for castling
        if(from.getPiece().getPieceType().charAt(1) == 'K' && path.size() == 2 && !isTeamInCheck(makeCopy(), from.getPiece().getTeam())) {
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
                if(!checkMove(rookSpot, path.get(0), null))
                    return false;
            }
        }

        
        Board boardCopy = makeCopy();
        Spot boardCopyTo = boardCopy.spotMat[to.getX()-1][to.getY()-1];
        Spot boardCopyFrom = boardCopy.spotMat[from.getX()-1][from.getY()-1];
        boardCopyTo.setPiece(boardCopyFrom.getPiece());
        boardCopyFrom.setPiece(null);
        boardCopyTo.getPiece().incrementMoveCount();
        return !isTeamInCheck(boardCopy, from.getPiece().getTeam());
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
        //Actually move the piece
        Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];
        Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
        
        Character promoRequest = null;
        if(cmdString.length() == 7) {
            promoRequest = cmdString.charAt(cmdString.length()-1);
        }

        //Check if the move in question is a promotion
        if((to.getY() == 8 || to.getY()== 1)  && (from.getPiece() instanceof Pawn)){
            System.out.println("Promotion detected");
            if(promoRequest == null)
                from.setPiece(new Queen(from.getPiece().getTeam()));
            else{
                System.out.println(promoRequest.charValue());
                switch(promoRequest.charValue()){
                    case 'p': break;
                    case 'r': from.setPiece(new Rook(from.getPiece().getTeam())); break;
                    case 'n': from.setPiece(new Knight(from.getPiece().getTeam())); break;
                    case 'b': from.setPiece(new Bishop(from.getPiece().getTeam())); break;
                    case 'q': from.setPiece(new Queen(from.getPiece().getTeam())); break;
                    default: break;
                }
            }
        }  else {
            System.out.println("Promotion not detected");
        }      

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
        
        //Check if move is enpassant
        if(from.getPiece() instanceof Pawn && (Math.abs(to.getY() - from.getY()) == 1 && Math.abs(to.getX() - from.getX()) == 1) && to.getPiece() == null){
            Spot enPassantSpot = spotMat[to.getX() - 1][from.getY() - 1];
            enPassantSpot.setPiece(null);
            
            
        }

        /**
        * Before move is made after initial validity checks, make move on copy of board instance and pass to isTeamInCheck
        * If returns true, move is not valid as it puts own king in check
        * If returns false, move is valid and we make move on current instance of board
        * Dont need to check for checkmate here because it will be accounted for with isTeamInCheck
        * 
        * After a move is made after initial validity checks, make move on current instance of board and evaluate 
        * 1. isTeamInCheckMate on enemy team
        * |--If returns true, game over
        * |--If returns false, do nothing
        * 
        * 2. isTeamInCheck on enemy team
        * |--If returns true, enemy king is in check
        * |--If returns false, do nothing.
        */

            
        to.setPiece(from.getPiece());
        from.setPiece(null);
        to.getPiece().incrementMoveCount();
        this.lastMove = to;


        if(isTeamInCheck(makeCopy(), to.getPiece().getEnemyTeam())) System.out.println("***Check***");

        return true;
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
   
    public static boolean isTeamInCheck(Board board, int evaluatedTeam) {
        if(evaluatedTeam == Piece.WHITE) System.out.println("**Evaulating check status for white king");
        if(evaluatedTeam == Piece.BLACK) System.out.println("**Evaulating check status for black king");
        Spot kingSpot = board.findKing(evaluatedTeam);
        for(int j = 0; j < board.spotMat.length; j++) {
            for(int i = 0; i < board.spotMat[j].length; i++) {
                Spot currSpot = board.spotMat[i][j];
                if(currSpot.getPiece() != null) {
                    Piece currPiece = currSpot.getPiece();
                    if(currPiece.getTeam() != evaluatedTeam) {
                        if(board.checkMove(currSpot, kingSpot, null)) {
                            
                            if(evaluatedTeam == Piece.WHITE) System.out.println("**White king is in check");
                            if(evaluatedTeam == Piece.BLACK) System.out.println("**Black king is in check");
                            
                            return true;
                        }
                    }    
                }
            }
        }
        if(evaluatedTeam == Piece.WHITE) System.out.println("**White king is NOT in check");
        if(evaluatedTeam == Piece.BLACK) System.out.println("**Black king is NOT in check");
        return false;
    }

    public boolean isTeamInCheckMate(int evaluatedTeam) {
        if(evaluatedTeam == Piece.WHITE) System.out.println("**Evaulating checkMATE status for white king");
        if(evaluatedTeam == Piece.BLACK) System.out.println("**Evaulating checkMATE status for black king");
        Spot kingSpot = findKing(evaluatedTeam);

        //Build list of spots king could move to
        ArrayList<Spot> spots = new ArrayList<Spot>();
        if(kingSpot.getX() == 1) {
            if(kingSpot.getY() == 1) { //top left
                spots.add(new Spot(2, 1));
                spots.add(new Spot(2, 2));
                spots.add(new Spot(1, 2));
            } else if(kingSpot.getY() == 8) { //bottom left
                spots.add(new Spot(2, 8));
                spots.add(new Spot(2, 7));
                spots.add(new Spot(1, 7));
            } else {
                spots.add(new Spot(kingSpot.getX(), kingSpot.getY()-1)); //up one
                spots.add(new Spot(kingSpot.getX(), kingSpot.getY()+1)); //down one
                spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()-1)); //up right one
                spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY())); //right one
                spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()+1)); //down right one
            }
        } else if(kingSpot.getX() == 8) {
            if(kingSpot.getY() == 1) { //top right
                spots.add(new Spot(7, 1));
                spots.add(new Spot(7, 2));
                spots.add(new Spot(8, 2));
            } else if(kingSpot.getY() == 8) { //bottom right
                spots.add(new Spot(7, 8));
                spots.add(new Spot(7, 7));
                spots.add(new Spot(8, 7));
            } else {
                spots.add(new Spot(kingSpot.getX(), kingSpot.getY()-1)); //up one
                spots.add(new Spot(kingSpot.getX(), kingSpot.getY()+1)); //down one
                spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()-1)); //up left one
                spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY())); //left one
                spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()+1)); //down left one
            }
        } else if(kingSpot.getY() == 1) {
            //corners already accounted for
            spots.add(new Spot(kingSpot.getX(), kingSpot.getY()+1)); //down one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY())); //left one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY())); //right one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()+1)); //down left one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()+1)); //down right one
        } else if(kingSpot.getY() == 8) {
            spots.add(new Spot(kingSpot.getX(), kingSpot.getY()-1)); //up one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY())); //left one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY())); //right one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()-1)); //up right one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()-1)); //up left one
        } else {
            spots.add(new Spot(kingSpot.getX(), kingSpot.getY()-1)); //up one
            spots.add(new Spot(kingSpot.getX(), kingSpot.getY()+1)); //down one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY())); //left one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY())); //right one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()-1)); //up right one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()-1)); //up left one
            spots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()+1)); //down left one
            spots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()+1)); //down right one
        }
        return false;
    }
}

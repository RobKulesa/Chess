package structures;

import structures.pieces.*;
import java.util.*;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Board will be made of Spots and can print its current state.
 * <p>  This class also contains all the functionality for a board
 *      to observe, interpret, and act upon its current board-state.</p>
 *  
 * @author Rob Kulesa
 * @author Aaron Kan
 * 
 */
public class Board { 
    /**
     * A 2-dimensional array of Spots that correspond to a chessboard
     * and holds all the Pieces and their locations using these Spot instances
     */
    Spot[][] spotMat;

    /**
     * A Spot that is used to hold the last move made on the board
     * This will be updated after every move.
     */
    Spot lastMove;


    /**
     * A constructor that sets spotMat to the standard chessboard
     * size of 8x8 and sets value in that matrix to a Spot
     * that corresponds to an actual location on the chessboard
     */
    public Board() {
        this.spotMat = new Spot[8][8];
        for(int i = 0; i < spotMat[0].length; i++) {
            for(int j = 0; j < spotMat[i].length; j++) {
                this.spotMat[i][j] = new Spot(i+1, j+1);
            }
        }
        initBoard();
    }

    
    /** 
     * This method returns an instance of Board that is 
     * an exact copy of the current board and its board-state
     * 
     * @return Board    A clone of the current board instance
     */
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
    
    /**
     * Looks at each row and prints each square from left to right
     * while going through the rows starting from the top and ending to the bottom.
     * Also, it prints out the coordinates at the right and bottom edges of the board.
     */
    public void printBoard() {
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
        System.out.println("\n");
    }


    /**
     * Sets all the pieces of a standard chess game into their standard places.
     * Sets the pieces of certain Spot instances of the Spot 2-d matrix spotMat
     * in the instance of Board that runs this method.
     */
    public void initBoard() {
        //Pawns
        
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
    }

    
    /** 
     * This method checks if the command a user is attempting to execute on the board
     * is properly formatted. If it makes it to the return statement, it recognizes
     * that the command is properly formatted and then proceeds to call checkMove to 
     * determine move validity according to the board and its game-state.
     * 
     * @param cmdString The user-inputted string used to potentially execute a command
     * @param turncount records which turn is currently taking place in the game
     * @return          boolean A value that indicates the validity of a move as determined by checkMove
     */
    public boolean checkString(String cmdString, int turncount) {
        //Check cmdString validity
        //System.out.println("Checking cmdString validity");
        if((cmdString.length() != 5 && cmdString.length() != 7)|| cmdString.charAt(2) != ' ')
            return false;
        if(cmdString.charAt(0) < 'a' || cmdString.charAt(0) > 'h' || cmdString.charAt(3) < 'a'|| cmdString.charAt(3) > 'h')
            return false;
        if(cmdString.charAt(1) < '1' || cmdString.charAt(1) > '8' || cmdString.charAt(4) < '1'|| cmdString.charAt(4) > '8')
            return false;
    
        Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];

        if(turncount % 2 != from.getPiece().getTeam()) return false;
        
        Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
        Character promoRequest = null;
        if(cmdString.length() == 7 ){
            promoRequest = cmdString.charAt(cmdString.length()-1);
        }
        
        return checkMove(from, to, promoRequest);
    }

    
    /** 
     * checkMove determines validty first by the following means:
     * <ol type="1">
     * <li> Checking path validity as specified by the command and
     *      according to the rules of chess
     * <li> Determine if the move in question fits the mold of a special move
     *      This includes enpassant, castling, and promotion.
     * <li> If it does, we determine if the special move is valid considering
     *      the user-input and the board-state
     * <li> Lastly, it determines if the move in question will place the Piece's
     *      own King in check.
     * </ol>
     * If a move is valid through all these checks, it is considered a valid move
     * 
     * 
     * @param from          The Spot on the board that the move origniates from
     * @param to            The Spot on the board that piece will end up on after the move is executed
     * @param promoRequest  A Character that stores a promotion request as indicated
     *                      by the user. Will be null if there is no stated promotion request.
     * @return              boolean that indicates if a move from one Spot on the board to another spot
     *                      is valid or not. 
     */
    public boolean checkMove(Spot from, Spot to, Character promoRequest) {
        //System.out.println("Checking if designated coordinate has a piece");
        //System.out.println("Getting Path from piece");
        if(from.getPiece() == null) return false;
        ArrayList<Spot> path = from.getPiece().getPath(from, to);
        //System.out.println(path);
        //Check if any pieces in the way except for final spot in path
        if(path.isEmpty() || piecesInWay(path, from.getPiece().getTeam())) return false;

        //Check if its a valid promotion request
        if(promoRequest != null && ((to.getY()!= 8 && to.getY()!= 1)  || !(from.getPiece() instanceof Pawn))){
            //System.out.println("Invalid promotion request detected");
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
            //System.out.println("Castling attempt detected");
            Spot rookSpot;
            Piece rook;
            if(to.getX()-from.getX() > 0){
                rookSpot = spotMat[7][from.getY()-1];
            } else {
                rookSpot = spotMat[0][from.getY()-1];
            }
            //System.out.println(rookSpot);
            rook = rookSpot.getPiece();
            
            if(rook == null || rookSpot.getPiece().getPieceType().charAt(1) != 'R' || rook.hasMoved()) return false;
            else{
                //System.out.println("Checking rook path");
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

    
    /** 
     * piecesInWay analyzes a path and determines if there are spots
     * that would inhibit the movement of a piece that wants to move
     * to the last Spot entry of its path. 
     * 
     * @param path  the path a given piece wants to take
     * @param team  the team of that given piece.
     * @return      boolean determines if there is a spot on the path that would inhibit movement
     */
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
    
    /** 
     * Analyzes a user inputted command and executes it on the current board instance. 
     * Updates lastTurn and will remove the opponent's piece if a take is detected.
     * 
     * 
     * @param cmdString a user-inputted command that moves one piece from one Spot
     *                  on the board to another. This command is assumed to be valid
     *                  and correctly formatted.
     */
    //Returns a boolean that tells Chess.Java if the game is still going on
    public void movePiece(String cmdString) {
        //Actually move the piece
        Spot from = this.spotMat[cmdString.charAt(0) - 'a'][8 - Character.getNumericValue(cmdString.charAt(1))];
        Spot to = this.spotMat[cmdString.charAt(3) - 'a'][8 - Character.getNumericValue(cmdString.charAt(4))];
        
        Character promoRequest = null;
        if(cmdString.length() == 7) {
            promoRequest = cmdString.charAt(cmdString.length()-1);
        }

        //Check if the move in question is a promotion
        if((to.getY() == 8 || to.getY()== 1)  && (from.getPiece() instanceof Pawn)){
            //System.out.println("Promotion detected");
            if(promoRequest == null)
                from.setPiece(new Queen(from.getPiece().getTeam()));
            else{
                //System.out.println(promoRequest.charValue());
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
            //System.out.println("Promotion not detected");
        }      

        //Check if the move in question is an attempt for castling
        if(from.getPiece().getPieceType().charAt(1) == 'K' && Math.abs(from.getX() - to.getX()) > 1){
            //Check which rook is affected
            //System.out.println("Castling attempt detected");
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
            //System.out.println(rookFromSpot);
            //System.out.println(rookToSpot);
            rook = rookFromSpot.getPiece();
            rookFromSpot.setPiece(null);
            rookToSpot.setPiece(rook);
        }
        
        //Check if move is enpassant
        if(from.getPiece() instanceof Pawn && (Math.abs(to.getY() - from.getY()) == 1 && Math.abs(to.getX() - from.getX()) == 1) && to.getPiece() == null){
            Spot enPassantSpot = spotMat[to.getX() - 1][from.getY() - 1];
            enPassantSpot.setPiece(null);
            
            
        }
        to.setPiece(from.getPiece());
        from.setPiece(null);
        to.getPiece().incrementMoveCount();
        this.lastMove = to;

    }

    
    /** 
     * Looks through all Spot instances in the current Board instance's spotMat
     * and locates the King piece of an indicated team
     * 
     * @param team  The team whose King location we are interested in finding
     * @return      Spot the location of the indicated team
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
   
    
    /** 
     * Returns true if the evaluated team's king is in check for the given board instance.
     * 
     * @param board             an instance of the board with all the pieces to be evaluated
     * @param evaluatedTeam     the king of this team will be evaluated for check status
     * @return boolean          whether the king of the evaluated team is in check
     */
    public static boolean isTeamInCheck(Board board, int evaluatedTeam) {
        //if(evaluatedTeam == Piece.WHITE) System.out.println("**Evaulating check status for white king");
        //if(evaluatedTeam == Piece.BLACK) System.out.println("**Evaulating check status for black king");
        Spot kingSpot = board.findKing(evaluatedTeam);
        if(kingSpot == null) return false;
        for(int j = 0; j < board.spotMat.length; j++) {
            for(int i = 0; i < board.spotMat[j].length; i++) {
                Spot currSpot = board.spotMat[i][j];
                if(currSpot.getPiece() != null) {
                    Piece currPiece = currSpot.getPiece();
                    if(currPiece.getTeam() != evaluatedTeam) {
                        if(board.checkMove(currSpot, kingSpot, null)) {
                            return true;
                        }
                    }    
                }
            }
        }
        return false;
    }

    
    /** 
     * Returns true if the evaluated team's king is in checkmate for the current board instance.
     * 
     * @param evaluatedTeam     the king of this team will be evaluated for checkmate status
     * @return boolean          whether the king of the evaluated team is in checkmate
     */
    public boolean isTeamInCheckMate(int evaluatedTeam) {
        //if(evaluatedTeam == Piece.WHITE) System.out.println("**Evaulating checkMATE status for white king");
        //if(evaluatedTeam == Piece.BLACK) System.out.println("**Evaulating checkMATE status for black king");
        Spot kingSpot = findKing(evaluatedTeam);

        if(!isTeamInCheck(makeCopy(), evaluatedTeam)) return false;

        System.out.println("Check");
        
        //Build list of spots king could move to
        ArrayList<Spot> potSpots = new ArrayList<Spot>();
        potSpots.add(new Spot(kingSpot.getX(), kingSpot.getY()-1)); //up one
        potSpots.add(new Spot(kingSpot.getX(), kingSpot.getY()+1)); //down one
        potSpots.add(new Spot(kingSpot.getX()-1, kingSpot.getY())); //left one
        potSpots.add(new Spot(kingSpot.getX()+1, kingSpot.getY())); //right one
        potSpots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()-1)); //up right one
        potSpots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()-1)); //up left one
        potSpots.add(new Spot(kingSpot.getX()-1, kingSpot.getY()+1)); //down left one
        potSpots.add(new Spot(kingSpot.getX()+1, kingSpot.getY()+1)); //down right one
        ArrayList<Spot> validSpots = new ArrayList<Spot>();
        
        for(Spot potSpot : potSpots) {
            if(potSpot.getX() <= 8 && potSpot.getX() >= 1 && potSpot.getY() <= 8 && potSpot.getY() >= 1) {
                validSpots.add(potSpot);
            }
        }

        for(Spot spot : validSpots) {
            if(checkMove(kingSpot, spot, null)) {
                return false;
            }
        }
        return true;
        
    }
}

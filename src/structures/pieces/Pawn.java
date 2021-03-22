package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Pawn is a class that implements the Piece abstract class.
 * Represents the Pawn piece of a standard chess game and knows
 * which standard Pawn moves are allowed and which ones are not.
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public class Pawn extends Piece {
    
    
    /**
     * Constructor for a Pawn instance
     * 
     * @param color indicates which team a Pawn belongs to
     */
    public Pawn(int color) {
        super(color);
    }

    
    /** 
     * This method determines move validity by the following measures:
     * <ol type = "1">
     * <li> Determines if the pawn is moving too far in the X or Y direction
     * <li> Determines if the pawn is moving in the correct vertical direction (according to its team)
     * <li> Determines if the pawn is attempting to move one step forward, two steps forward
     *      Or one step diagonal forward
     * <li> Determines if anything might inhibit the pawn from moving in one of the above scenarios
     * </ol>
     * 
     * @param from     The origin {@link Spot} that the Pawn is moving from.
     * @param to       The destination {@link Spot} that the Pawn is moving to.
     * @return         ArrayList of spots that the Pawn follows to get to its
     *                 destination.
     */
    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();
        //Checks piece color
        boolean isWhite = this.getTeam() == WHITE;
        
        //errors if it moves too far in the X or Y direction
        if(Math.abs(from.getX()-to.getX()) > 1)
            return path;
        if(Math.abs(from.getY()-to.getY()) > 2 || Math.abs(from.getY()-to.getY()) < 1)
            return path;

        //errors if it is moving in the wrong direction (pawns cant move backwards)
        if(isWhite){
            if(to.getY() - from.getY() > 0)
                return path;
        } else {
            if(to.getY() - from.getY() < 0)
                return path;
        }

        //Checks Move Type
        if(Math.abs(from.getX()-to.getX()) == 0) { //regular move
            if(to.getPiece() != null)
                return path;
            //If moving 1 Square forward
            if(Math.abs(from.getY()-to.getY()) == 1) {
                path.add(to);
                return path;
            } else { // Moving 2 Squares Forward
                Spot mid;
                if(!isWhite){
                    if(from.getY()!= 2)
                        return path;
                    mid = new Spot(from.getX(), from.getY()+1);
                    
                } else {
                    if(from.getY()!=7) 
                        return path;
                    mid = new Spot(from.getX(), from.getY()-1);
                }
                path.add(mid);
                path.add(to);
                return path;
            }
        } else {
            if(Math.abs(from.getY()-to.getY()) != 1)
                return path;
            path.add(to);
            return path;
        }
    }

    
    /**
     * Returns a formatted string representing the pawn as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wp";
        else return "bp";
    }
}

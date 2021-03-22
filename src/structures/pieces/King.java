package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * King is a class that implements the Piece abstract class.
 * Represents the King piece of a standard chess game and knows
 * which standard King moves are allowed and which ones are not.
 * 
 * @author Robert Kulesa
 * @author Aaron Kan
 */
public class King extends Piece {

    /**
     * A boolean for methods that access the King to know if
     * the current King instance is in check
     */
    private boolean inCheck;
    
    /**
     * A constructor for a King instance
     * 
     * @param color Indicates what team the King instance is on
     */
    public King(int color) {
        super(color);
        this.inCheck = false;
    }

    
    /** 
     * A getter for the inCheck field
     * 
     * @return boolean  returns the value of the inCheck field
     */
    public boolean getInCheck() {
        return this.inCheck;
    }

    
    /** 
     * A setter for the inCheck field
     * 
     * @param inCheck   the value you are attempting to set the 
     *                  inCheck field to
     */
    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    
    /** 
     * This method determines move validity by the following measures:
     * <ol type = "1">
     * <li> Determines if the King is moving too far in the X or Y direction
     * <li> If the King is attempting a normal 1 square move, the path is returned
     * <li> If a castling attempt is detected, it will return the two squares the King will move across
     * </ol>
     * @param from     The origin {@link Spot} that the King is moving from.
     * @param to       The destination {@link Spot} that the King is moving to.
     * @return         ArrayList of spots that the King follows to get to its
     *                 destination.
     */
    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();
        if(Math.abs(to.getY() - from.getY()) > 1 || from.equals(to) || Math.abs(to.getX() - from.getX()) > 2) return path;
        
        if(Math.abs(to.getX() - from.getX()) <= 1){
            path.add(to); 
            return path;
        } else {
            if(this.getMoveCount() <= 0 && Math.abs(to.getY() - from.getY()) == 0){ 
                if(to.getX() - from.getX() > 0){
                    path.add(new Spot(from.getX()+1, from.getY()));
                    path.add(to);
                    return path;
                } else{
                    path.add(new Spot(from.getX()-1, from.getY()));
                    path.add(to);
                    return path;
                }
            }else {
                return path;
            }
        }
        
    }

    
    /**
     * Returns a formatted string representing the king as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wK";
        else return "bK";
    }
    
}

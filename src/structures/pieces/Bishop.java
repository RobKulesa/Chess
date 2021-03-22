package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Bishop is a class that implements the Piece abstract class.
 * Represents the Bishop piece of a standard chess game and knows
 * which standard Bishop moves are allowed and which ones are not.
 * 
 * @author Robert Kulesa
 * @author Aaron Kan
 */
public class Bishop extends Piece {

    /**
     * A constructor for a Bishop instance
     * 
     * @param color Indicates what team the Bishop instance is on
     */
    public Bishop(int color) {
        super(color);
    }

    /**
     * Implements getPath to return the bishop's path of spots from 
     * {@link Spot} <code>from</code> to {@link Spot} <code>to</code>.
     * 
     * @param from     The origin {@link Spot} that the bishop is moving from.
     * @param to       The destination {@link Spot} that the bishop is moving to.
     * @return         ArrayList of spots that the bishop follows to get to its
     *                 destination.
     */
    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();

        if(from.equals(to)) return path;
        int dX = to.getX() - from.getX();
        int dY = to.getY() - from.getY();
        if(dX == 0 || dY == 0) return path;

        if(dX == dY) {
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY() + i));
                }
            } else {
                for(int i = 1; i <= -dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY() - i));
                }
            }
        } else if(dX == -dY) {            
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY() - i));
                }
            } else {
                for(int i = 1; i <= -dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY() + i));
                }
            }
        }
        return path;
    }

    /**
     * Returns a formatted string representing the bishop as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wB";
        else return "bB";
    }
    
}

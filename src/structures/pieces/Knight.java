package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Knight is a class that implements the Piece abstract class.
 * Represents the Knight piece of a standard chess game and knows
 * which standard Knight moves are allowed and which ones are not.
 * 
 * @author Robert Kulesa
 * @author Aaron Kan
 */
public class Knight extends Piece {

    /**
     * A constructor for a Knight instance
     * 
     * @param color Indicates what team the Knight instance is on
     */
    public Knight(int color) {
        super(color);
    }
    /**
     * This method determines move validity by the following measures:
     * <ol type = "1">
     * <li> Determines if the pawn is moving too far in the X or Y direction
     * <li> Determines if the Knight is making a valid move of either shifting 
     *      2 vertical 1 horizontal or shifting 1 vertical and 2 horizontal
     * </ol> 
     * @param from     The origin {@link Spot} that the Knight is moving from.
     * @param to       The destination {@link Spot} that the Knight is moving to.
     * @return         ArrayList of spots that the Knight follows to get to its
     *                 destination.
     */
    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();

        if(from.equals(to)) return path;
        int xDist = Math.abs(to.getX() - from.getX());
        int yDist = Math.abs(to.getY() - from.getY());

        if(xDist == 2) {
            if(yDist == 1) {
                path.add(to);
            }
        } else if(xDist == 1) {
            if(yDist == 2) {
                path.add(to);
            }
        }
        return path;
    }

    /**
     * Returns a formatted string representing the knight as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wN";
        else return "bN";
    }
    
}

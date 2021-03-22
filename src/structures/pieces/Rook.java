package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Rook is a class that implements the Piece abstract class.
 * Represents the Rook piece of a standard chess game and knows
 * which standard Rook moves are allowed and which ones are not.
 * 
 * @author Robert Kulesa
 * @author Aaron Kan
 */
public class Rook extends Piece {

    /**
     * A constructor for a Rook instance
     * 
     * @param color Indicates what team the Rook instance is on
     */
    public Rook(int color) {
        super(color);
    }

    /**
     * Implements getPath to return the rook's path of spots from 
     * {@link Spot} <code>from</code> to {@link Spot} <code>to</code>.
     * 
     * @param from     The origin {@link Spot} that the rook is moving from.
     * @param to       The destination {@link Spot} that the rook is moving to.
     * @return         ArrayList of spots that the rook follows to get to its
     *                 destination.
     */
    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();

        if(from.equals(to)) return path;
        int dX = to.getX() - from.getX();
        int dY = to.getY() - from.getY();

        if(dX == 0) {
            if(dY > 0) {
                for(int i = 1; i <= dY; i++) {
                    path.add(new Spot(from.getX(), from.getY() + i));
                }
            } else {
                for(int i = 1; i <= -dY; i++) {
                    path.add(new Spot(from.getX(), from.getY() - i));
                }
            }
        } else if(dY == 0) {
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY()));
                }
            } else {
                for(int i = 1; i <= -dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY()));
                }
            }
        } 
        return path;
    }

    /**
     * Returns a formatted string representing the rook as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wR";
        else return "bR";
    }
    
}

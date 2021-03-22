package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Queen is a class that implements the Piece abstract class.
 * Represents the Queen piece of a standard chess game and knows
 * which standard Queen moves are allowed and which ones are not.
 * 
 * @author Robert Kulesa
 * @author Aaron Kan
 */
public class Queen extends Piece {
    /**
     * A constructor for a Queen instance
     * 
     * @param color Indicates what team the Queen instance is on
     */
    public Queen(int color) {
        super(color);
    }

    /**
     * Implements getPath to return the queen's path of spots from 
     * {@link Spot} <code>from</code> to {@link Spot} <code>to</code>.
     * 
     * @param from     The origin {@link Spot} that the queen is moving from.
     * @param to       The destination {@link Spot} that the queen is moving to.
     * @return         ArrayList of spots that the queen follows to get to its
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
        } else if (dX == dY) {
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
     * Returns a formatted string representing the queen as it appears in board printouts.
     * 
     * @return String
     */
    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wQ";
        else return "bQ";
    }
    
}

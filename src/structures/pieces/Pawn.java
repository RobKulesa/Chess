package structures.pieces;

import structures.Spot;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public boolean validPieceMove(Spot from, Spot to) {
        return false;
    }

    @Override
    public String getPieceType() {
        return null;
    }
}

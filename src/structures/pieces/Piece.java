package structures.pieces;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public abstract class Piece {
    private boolean inPlay;
    private int team;
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public Piece(int team) {
        this.team = team;
    }

    public boolean isInPlay() {
        return this.inPlay;
    }

    public void setInPlay(boolean inPlay) {
        this.inPlay = inPlay;
    }

    public int getTeam() {
        return this.team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public abstract boolean validPieceMove(Spot from, Spot to);

    public abstract String getPieceType();
}

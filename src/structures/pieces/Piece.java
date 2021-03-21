package structures.pieces;

import java.util.ArrayList;

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
    private int moveCount;
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    public Piece(int team) {
        this.team = team;
        this.moveCount = 0;
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

    public void incrementMoveCount() {
        this.moveCount++;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public int getEnemyTeam() {
        if(this.team == Piece.WHITE) return Piece.BLACK;
        else return Piece.WHITE;
    }

    public boolean hasMovedOnce() {
        return this.getMoveCount() == 1;
    }

    public boolean hasMoved() {
        return this.getMoveCount() > 0;
    }

    public abstract ArrayList<Spot> getPath(Spot from, Spot to);

    public abstract String getPieceType();
}

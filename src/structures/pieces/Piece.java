package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Piece is the abstract class that defines the shared functionality of all
 * chess pieces, and leaves unimplemented the functionalities specfic to
 * each chess piece type.
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public abstract class Piece {
    /**
     * white pieces will have 0 as their team. Use Piece.WHITE
     * to reference black team outside of this class.
     */
    public static final int WHITE = 0;
    
    /**
     * black pieces will have 1 as their team. Use Piece.BLACK
     * to reference black team outside of this class.
     */
    public static final int BLACK = 1;

    /**
     * team of this piece: <code>WHITE</code> or <code>BLACK</code>
     */
    private int team;

    /**
     * number of times this piece has moved.
     */
    private int moveCount;

    /**
     * Constructor for creating a new piece
     * 
     * @param team     team of this piece: <code>WHITE</code> or <code>BLACK</code>
     */
    public Piece(int team) {
        this.team = team;
        this.moveCount = 0;
    }
 
    /** 
     * Get this piece's team
     * 
     * @return int team of this piece: <code>WHITE</code> or <code>BLACK</code>
     */
    public int getTeam() {
        return this.team;
    }

    
    /** 
     * Set this piece's team
     * 
     * @param team     team of this piece: <code>WHITE</code> or <code>BLACK</code>
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * Add one to this piece's move counter
     */
    public void incrementMoveCount() {
        this.moveCount++;
    }

    
    /** 
     * Get the number of moves this piece has made
     * 
     * @return int
     */
    public int getMoveCount() {
        return this.moveCount;
    }

    
    /** 
     * Return the color of the enemy team
     * 
     * @return int 
     */
    public int getEnemyTeam() {
        if(this.team == Piece.WHITE) return Piece.BLACK;
        else return Piece.WHITE;
    }

    
    /** 
     * Return true if this piece has only moved once
     * 
     * @return boolean
     */
    public boolean hasMovedOnce() {
        return this.getMoveCount() == 1;
    }

    
    /** 
     * Return true if this piece has moved at least once
     * 
     * @return boolean
     */
    public boolean hasMoved() {
        return this.getMoveCount() > 0;
    }

    /**
     * An abstract method to return the piece's path of spots from 
     * {@link Spot} <code>from</code> to {@link Spot} <code>to</code>.
     * 
     * @param from     The origin {@link Spot} that the piece is moving from.
     * @param to       The destination {@link Spot} that the piece is moving to.
     * @return         ArrayList of spots that the piece follows to get to its
     *                 destination.
     */
    public abstract ArrayList<Spot> getPath(Spot from, Spot to);

    /**
     * An abstract method to return the formatted string representing the piece
     * as it appears in board printouts.
     * 
     * @return String
     */
    public abstract String getPieceType();
}

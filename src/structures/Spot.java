package structures;
import structures.pieces.Piece;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * Spots are the objects that make up the chess board. They have an
 * x and y coordinate, and can hold a chess piece.
 *  
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public class Spot {
    /**
     * x-coordinate location of the spot
     */
    private int x;

    /**
     * y-coordinate location of the spot
     */
    private int y;

    /**
     * {@link Piece} piece currently located in this spot
     */
    private Piece piece;

    /**
     * A constructor for an empty spot
     * 
     * @param x     x-coordinate location of the spot
     * @param y     y-coordinate location of the spot
     */
    public Spot(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setPiece(null);
    }

    /**
     * A constructor for a spot with a piece in it
     * 
     * @param x         x-coordinate location of the spot
     * @param y         y-coordinate location of the spot
     * @param piece     piece that is located in the spot 
     */
    public Spot(int x, int y, Piece piece) {
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
    }
    
    
    /** 
     * Returns the x-coordinate of this spot
     * 
     * @return int
     */
    public int getX() {
        return this.x;
    }

    
    /**
     * Returns the y-coordinate of this spot
     *  
     * @return int
     */
    public int getY() {
        return this.y;
    }

    
    /** 
     * Returns the piece in this spot
     * 
     * @return Piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    
    /** 
     * Set the x-coordinate of this spot
     * 
     * @param x     x-coordinate location of the spot
     */
    public void setX(int x) {
        this.x = x;
    }

    
    /** 
     * Set the y-coordinate of this spot
     * 
     * @param y     y-coordinate location of the spot
     */
    public void setY(int y) {
        this.y = y;
    }

    
    /** 
     * Set the piece in this spot
     * 
     * @param piece     piece to be put in the spot
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    
    /** 
     * Returns true if the current spot and spot <code>o</code> have the same
     * x and y coordinates.
     * 
     * @param o     Object o can be casted to a {@link Spot}
     * @return      <code>true</code> if the spots have the same x and y
     *              coordinates. <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Spot)) return false;
        Spot other = (Spot) o;
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    
    /** 
     * Returns a formatted string showing the spots coordinates and piece, if any.
     * 
     * @return String
     */
    @Override
    public String toString() {
        if(this.getPiece() == null) return "(" + (char) (this.getX() + '`') + ", " + (9 - this.getY()) + ", null)";


        return "(" + (char) (this.getX() + '`') + ", " + (9 - this.getY()) + ", " + this.getPiece().getPieceType() + ")";
    }

}

package structures;
import structures.pieces.Piece;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment 
 * @author Rob Kulesa
 * @author Aaron Kan
 *
 * Spots will hold chess pieces and know their place on the board
 */
public class Spot {
    private int x;
    private int y;
    private Piece piece;

    public Spot(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setPiece(null);
    }

    public Spot(int x, int y, Piece piece) {
        this.setX(x);
        this.setY(y);
        this.setPiece(piece);
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class King extends Piece {
    
    public King(int color) {
        super(color);
    }


    @Override
    public ArrayList<Spot> validPieceMove(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();
        if(Math.abs(to.getX() - from.getX()) > 1 || Math.abs(to.getY() - from.getY()) > 1 || from.equals(to)) return path;
        path.add(to); 
        return path;
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wK";
        else return "bK";
    }
    
}

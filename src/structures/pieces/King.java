package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class King extends Piece {
    
    public King(int color) {
        super(color);
    }


    @Override
    public ArrayList<Spot> validPieceMove(Spot from, Spot to) {
        
        return null;
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wK";
        else return "bK";
    }
    
}

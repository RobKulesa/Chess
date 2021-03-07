package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class Queen extends Piece {

    public Queen(int color) {
        super(color);
    }

    @Override
    public ArrayList<Spot> validPieceMove(Spot from, Spot to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wQ";
        else return "bQ";
    }
    
}

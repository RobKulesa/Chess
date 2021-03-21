package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class Knight extends Piece {

    public Knight(int color) {
        super(color);
    }

    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();

        if(from.equals(to)) return path;
        int xDist = Math.abs(to.getX() - from.getX());
        int yDist = Math.abs(to.getY() - from.getY());

        if(xDist == 2) {
            if(yDist == 1) {
                path.add(to);
            }
        } else if(xDist == 1) {
            if(yDist == 2) {
                path.add(to);
            }
        }
        return path;
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wN";
        else return "bN";
    }
    
}

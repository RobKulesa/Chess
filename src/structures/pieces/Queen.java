package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class Queen extends Piece {

    public Queen(int color) {
        super(color);
    }

    @Override
    public ArrayList<Spot> validPieceMove(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();

        if(from.equals(to)) return path;
        int dX = to.getX() - from.getX();
        int dY = to.getY() - from.getY();

        if(dX == 0) {
            if(dY > 0) {
                for(int i = 1; i <= dY; i++) {
                    path.add(new Spot(from.getX(), from.getY() + i));
                }
            } else {
                for(int i = 1; i <= dY; i++) {
                    path.add(new Spot(from.getX(), from.getY() - i));
                }
            }
        } else if(dY == 0) {
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY()));
                }
            } else {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY()));
                }
            }
        } else if (dX == dY) {
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY() + i));
                }
            } else {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY() - i));
                }
            }
        } else { //dX == -dY
            if(dX > 0) {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() + i, from.getY() - i));
                }
            } else {
                for(int i = 1; i <= dX; i++) {
                    path.add(new Spot(from.getX() - i, from.getY() + i));
                }
            }
        }
        return path;
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wQ";
        else return "bQ";
    }
    
}

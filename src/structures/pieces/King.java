package structures.pieces;

import java.util.ArrayList;

import structures.Spot;

public class King extends Piece {

    private boolean inCheck;
    
    public King(int color) {
        super(color);
        this.inCheck = false;
    }

    public boolean getInCheck() {
        return this.inCheck;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    @Override
    public ArrayList<Spot> getPath(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();
        if(Math.abs(to.getY() - from.getY()) > 1 || from.equals(to) || Math.abs(to.getX() - from.getX()) > 2) return path;
        
        //Under Construction
        if(Math.abs(to.getX() - from.getX()) <= 1){
            path.add(to); 
            return path;
        } else {
            if(this.getMoveCount() <= 0 && Math.abs(to.getY() - from.getY()) == 0){ 
                if(to.getX() - from.getX() > 0){
                    path.add(new Spot(from.getX()+1, from.getY()));
                    path.add(to);
                    return path;
                } else{
                    path.add(new Spot(from.getX()-1, from.getY()));
                    path.add(to);
                    return path;
                }
            }else {
                return path;
            }
        }
        
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wK";
        else return "bK";
    }
    
}

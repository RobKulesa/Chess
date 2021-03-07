package structures.pieces;

import java.util.ArrayList;

import structures.Spot;
/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment
 * 
 * @author Rob Kulesa
 * @author Aaron Kan
 */
public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    /*
    * Valid Moves
        1. Move forward 1 spot
        2. Move diagonal forward to take
        3. To move forward 2 spots for 1st move
    * Invalid Moves
        1. Can't move if a piece is blocking it
    */
    public ArrayList<Spot> validPieceMove(Spot from, Spot to) {
        ArrayList<Spot> path = new ArrayList<Spot>();
        //Checks piece color
        boolean isWhite = this.getTeam() == WHITE;
        
        //errors if it moves too far in the X or Y direction
        if(Math.abs(from.getX()-to.getX()) > 1)
            return path;
        if(Math.abs(from.getY()-to.getY()) > 2 || Math.abs(from.getY()-to.getY()) < 1)
            return path;

        //errors if it is moving in the wrong direction (pawns cant move backwards)
        if(isWhite){
            if(to.getY() - from.getY() < 0)
                return path;
        } else {
            if(to.getY() - from.getY() > 0)
                return path;
        }

        //Checks Move Type
        if(Math.abs(from.getX()-to.getX()) == 0) { //regular move
            if(to.getPiece() != null)
                return path;
            //If moving 1 Square forward
            if(Math.abs(from.getY()-to.getY()) == 1) {
                path.add(to);
                return path;
            }
                
            else { // Moving 2 Squares Forward
                Spot mid;
                if(isWhite){
                    if(from.getY()!= 2)
                        return path;
                    mid = new Spot(from.getX(), from.getY()+1);
                    
                } else {
                    if(from.getY()!=7) 
                        return path;
                    mid = new Spot(from.getX(), from.getY()-1);
                }
                path.add(mid);
                path.add(to);
                return path;
            }
        } else { //trying to take piece
            if(to.getPiece()==null) {
                return path;
            }
            if(to.getPiece().getTeam() == WHITE && isWhite){
                return path;
            }
            if(to.getPiece().getTeam()== BLACK && !isWhite)
                return path;
            if(Math.abs(from.getY()-to.getY()) != 1)
                return path;
            path.add(to);
            return path;
        }
    }

    @Override
    public String getPieceType() {
        if(this.getTeam() == WHITE) return "wp";
        else return "bp";
    }
}

package structures;

/**
 * Rutgers CS213 Sp21 Group 30 Chess Assignment 
 * @author Rob Kulesa
 * @author Aaron Kan
 *
 * Board will be made of Spots and can print its current state
 */
public class Board {
    
    Spot[][] spotMat;

    public Board(){
        spotMat = new Spot[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                spotMat[i][j] = new Spot(i+1, j+1);
            }
        }
    }

    public void printBoard(){
        System.out.println("\n Current Board Status: ");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Spot currSpot = this.spotMat[i][j];
                if(currSpot.getPiece() == null){
                    if((currSpot.getX() + currSpot.getY()) % 2 == 0)
                        System.out.print("##");
                    else
                        System.out.print("  ");
                } else{
                    //System.out.print(piece.getPieceType());
                }
                System.out.print(" ");
            }
            System.out.printf("%d\n", 8-i);
        }
        for(int i = 0; i < 8; i++){
            System.out.printf(" %c ", ('a'+ i));
        }
        System.out.println();
    }

    public boolean checkMove(String cmdString){
        
        return false;
    }


    public void movePiece(){

    }




}

package nl.sogyo.mancala;

public class Player
{
    private boolean isTurn = false;
    private Player opponent;

    Player(){
        this.setTurn(true);
        this.opponent = new Player(this);
    }

    Player(Player otherPlayer){
        this.setTurn(false);
        this.opponent = otherPlayer;
    }

    //Declare Getters and Setters
    void setTurn(boolean isTurn){
        this.isTurn = isTurn;
    }

    boolean getTurn(){
        return this.isTurn;
    }

    Player getOpponent(){
        return this.opponent;
    }

    void endTurn(){
        this.setTurn(false);
        this.getOpponent().setTurn(true);
    }
}

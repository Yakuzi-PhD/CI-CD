package nl.sogyo.mancala;

public class Kalaha extends SuperPit {

    public Kalaha(Player player){
        super(0, player, null);
    }

    protected boolean isKalaha(){
        return true;
    }

    protected SuperPit getKalaha(){
            return this;
    }

    protected void passStones(int passableStones){
        if (passableStones > 0 && this.getOwner().getTurn()){
            this.addStone();
            this.getNextPit().passStones(passableStones-1);
        }
        else{
            this.getNextPit().passStones(passableStones);
        }
    }

    protected void lastStone(){
        this.addStone();
        Pit checkEndPit = (Pit) this.getNextPit();
        if (checkEndPit.checkEnd()){
            this.finalScore();
        }
        //No change of turn since the last stone ended up in the Kalaha.
    }


}

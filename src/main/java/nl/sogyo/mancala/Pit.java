package nl.sogyo.mancala;

public class Pit extends SuperPit {

    Pit() {
        this(1,new Player());
        Pit opponentPit = new Pit(1, new Player(this.getOwner()));
        this.connectPits(opponentPit);

    }

    Pit(int pitNumber, Player player){
        super(4, player, (pitNumber <6 ) ? (new Pit(pitNumber+1,player)) : new Kalaha(player));
    }

    Pit getOppositePit(){
        for (int i = 1; i < 7; i++) {
            if (this.getNextPit(i).isKalaha()) {
                return (Pit) this.getNextPit(2 * i);
            }
        }
        return null;
    }

    protected SuperPit getKalaha(){
        return this.getNextPit().getKalaha();
    }

    public void play(){
        int passableStones = this.getStones();
        if (this.getStones() < 1 || !this.getOwner().getTurn()){
            return;
        }
        this.emptyStones();
        this.getNextPit().passStones(passableStones);
    }

    protected void passStones(int passableStones){
        if (passableStones > 1){
            this.addStone();
            this.getNextPit().passStones(passableStones-1);
        }
        else if (passableStones == 1){
            this.lastStone();
        }
    }

    protected void lastStone(){
        if (this.getStones() == 0 && this.getOppositePit().getStones() > 0) {
            this.capture();
            if (this.checkEnd()) this.getKalaha().finalScore();
        }
        else {
            this.addStone();
            if (this.checkEnd()) this.getKalaha().finalScore();
        }
        this.getOwner().endTurn();
    }

    protected void capture(){
        int capturedStones = this.getOppositePit().getStones();
        this.getOppositePit().setStones(0);
        this.getKalaha().addStone(capturedStones + 1);
    }

    public boolean checkEnd(){
        boolean theEnd = true;
        for (int i = 1; i < 7; i++){
            if (this.getKalaha().getNextPit(7 + i).getStones() > 0){
                theEnd = false;
            }
        }
        return theEnd;
    }

}

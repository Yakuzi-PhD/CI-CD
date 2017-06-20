package nl.sogyo.mancala;

public abstract class SuperPit {
    private int stones;
    private Player owner;
    private SuperPit nextPit;

    protected SuperPit(int stones, Player owner, SuperPit nextPit) {
        this.stones = stones;
        this.owner = owner;
        this.nextPit = nextPit;
    }

    //Declare Getter and Setter Methods
    protected int getStones(){
        return stones;
    }
    protected void setStones(int stones){
        this.stones = stones;
    }

    protected Player getOwner(){
        return owner;
    }

    protected SuperPit getNextPit(){
        return nextPit;
    }

    protected SuperPit getNextPit(int increments){
        while (increments <1) increments += 14;
            if (increments == 1) {
                return nextPit;
            } else {
                return nextPit.getNextPit(increments - 1);
            }
    }

    protected void connectPits(SuperPit opponentPit){
        this.getKalaha().nextPit = opponentPit;
        opponentPit.getKalaha().nextPit = this;
    }

    protected boolean isKalaha(){
        return false;
    }

    protected void addStone(){
        this.stones++;
    }
    protected void addStone(int x){
        this.setStones(this.getStones() + x);
    }

    protected void emptyStones(){
        this.stones = 0;
    }

    protected int getScore(){
        return this.getKalaha().getStones();
    }

    protected String finalScore(){
        String s = null;
        if (this.getScore() > 25){
            s = "Won!";
        }
        if (this.getScore() < 25){
            s = "Lost :(";
        }
        if (this.getScore() == 25 ){
            s = "Tie.";
        }
        return s;
    }

    protected abstract SuperPit getKalaha();
    protected abstract void passStones(int passableStones);
    protected abstract void lastStone();
}

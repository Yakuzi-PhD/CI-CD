package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void VerifyKalahaIsInSixthPosFromStart(){
        Pit testPit = new Pit();
        Assert.assertTrue(testPit.getNextPit(6).isKalaha());
    }

    @Test
    public void TestSetStones() {
        Pit testPit = new Pit();
        testPit.setStones(20);
        Assert.assertTrue(testPit.getStones() == 20);
    }

    @Test
    public void TestSetTurn(){
        Pit testPit = new Pit();
        testPit.getOwner().setTurn(true);
        Assert.assertTrue(testPit.getOwner().getTurn());
    }

    @Test
    public void VerifyInitAmountOfPitStonesIsFour() {
        Pit testPit = new Pit();
        boolean testSuccess = false;
        if (testPit.getStones() == 4){
            testSuccess = true;
        }
        Assert.assertTrue(testSuccess);
    }

    @Test
    public void VerifyInitAmountOfKalahaStonesIsZero(){
        Pit testPit = new Pit();
        Assert.assertEquals(testPit.getKalaha().getStones(), 0);
    }

    @Test
    public void VerifyLinkedPitGeneration(){
        Pit testPit = new Pit();
        Assert.assertNotNull(testPit.getNextPit(7));
    }

    @Test
    public void VerifyPitLinkingWorks(){
        Pit testPit = new Pit();
        boolean testSuccess = true;
        for (int i = 0; i < 50; i++) {
           if (testPit.getNextPit(i) == null){
               testSuccess = false;
           }
        }
        Assert.assertTrue(testSuccess);
    }

    @Test
    public void VerifyKalahaAndPitLocations(){
        Pit testPit = new Pit();
        boolean testSuccess = true;
        for (int i = 0; i < 14; i++){
            if (i==6 || i==13){
                if (testPit.getNextPit(i).getStones() != 0){
                    testSuccess = false;
                }
            }
            else{
                if (testPit.getNextPit(i).getStones() != 4){
                    testSuccess = false;
                }
            }
        }
        Assert.assertTrue(testSuccess);
    }

    @Test
    public void VerifyPitAndKalahaOwnership(){
        Pit testPit = new Pit();
        Player player1 = testPit.getOwner();
        Player player2 = testPit.getNextPit(7).getOwner();
        boolean testSuccess = true;
        for (int i = 0; i < 14; i++) {
            if (i<7){
                if (testPit.getNextPit(i).getOwner() != player1) {
                    testSuccess = false;
                }
            }
            else{
                if (testPit.getNextPit(i).getOwner() != player2) {
                    testSuccess = false;
                }
            }
        }
        Assert.assertTrue(testSuccess);
    }

    @Test
    public void VerifyOppositePitLocation(){
        Pit initPit = new Pit();
        boolean testSuccess = true;
        for (int i = 0; i < 14; i++) {
            if (i==6 || i==13) i++;
            Pit testPit = (Pit) initPit.getNextPit(i);
            if(testPit.getOppositePit() != testPit.getNextPit(12 - 2 * i)){
                testSuccess = false;
            }
        }
        Assert.assertTrue(testSuccess);
    }

    @Test
    public void VerifyPitIsEmptyAfterSelecting(){
        Pit testPit = new Pit();
        testPit.play();
        Assert.assertEquals(testPit.getStones(), 0);
    }

    @Test
    public void VerifyStoneIsPassedToNextPitAfterSelecting(){
        Pit testPit = new Pit();
        testPit.getNextPit().emptyStones();
        testPit.play();
        Assert.assertEquals(testPit.getNextPit().getStones(), 1);
    }

    @Test
    public void VerifyAllStonesArePassedToOwnKahalaAndOpponentPit(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(5);
        testPit.play();
        Assert.assertTrue(testPit.getNextPit().getStones() == 1 && testPit.getNextPit(3).getStones() == 5);
    }

    @Test
    public void VerifyOpponentKalahaIsSkipped(){
        Pit initPit = new Pit();
        boolean a,b,c,d;
        a=b=c=d=true;
        Pit testPit = (Pit) initPit.getNextPit(5);
        testPit.setStones(8);
        testPit.play();
        if (testPit.getNextPit().getStones() != 1){
            a = false;
        }
        if (testPit.getNextPit(2).getStones() != 5){
            b = false;
        }
        if (testPit.getNextPit(8).getStones() != 0){
            c = false;
        }
        if (testPit.getNextPit(9).getStones() != 5){
            d = false;
        }
        Assert.assertTrue(a && b && c && d);
    }

    @Test
    public void VerifyPlayerCantSelectOpponentPits(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(10);
        testPit.play();
        Assert.assertEquals(testPit.getStones(), 4);
    }

    @Test
    public void CheckPlayerTurnEndsUnderNormalConditions(){
        Pit testPit = new Pit();
        testPit.play();
        Assert.assertTrue(!testPit.getOwner().getTurn());
    }

    @Test
    public void CheckOpponentsTurnStartsAfterNormalConditions(){
        Pit testPit = new Pit();
        testPit.play();
        Assert.assertTrue(testPit.getOwner().getOpponent().getTurn());
    }

    @Test
    public void VerifyPlayerGetsExtraTurnOnLastStoneInKalaha(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(3);
        testPit.play();
        Assert.assertTrue(initPit.getOwner().getTurn());
    }

    @Test
    public void VerifyNotOpponentsTurnAtStart(){
        Pit testPit = new Pit();
        Assert.assertTrue(!testPit.getOwner().getOpponent().getTurn());
    }

    @Test
    public void CapturingTest() {
        Pit testPit = new Pit();
        testPit.getNextPit(4).setStones(0);
        testPit.play();
        Assert.assertEquals(testPit.getKalaha().getStones(), 5);
    }

    @Test
    public void TestEndCheckOnKalaha(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(5);
        testPit.setStones(1);
        for (int i = 0; i < 5; i++){
            initPit.getNextPit(i).setStones(0);
        }
        testPit.play();
        Assert.assertTrue(initPit.checkEnd());
    }

    @Test
    public void TestEndCheckOnCapture(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(3);
        for (int i = 0; i < 6; i++){
            initPit.getNextPit(i).setStones(0);
        }
        testPit.setStones(1);
        testPit.play();
        Assert.assertTrue(initPit.checkEnd());
    }

    @Test
    public void VerifyWinStateOne(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(3);
        for (int i = 0; i < 6; i++){
            initPit.getNextPit(i).setStones(0);
        }
        testPit.setStones(1);
        testPit.getKalaha().setStones(25);
        testPit.play();
        Assert.assertEquals(initPit.finalScore(), "Won!");
    }

    @Test
    public void VerifyWinStateTwo(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(3);
        for (int i = 0; i < 6; i++){
            initPit.getNextPit(i).setStones(0);
        }
        testPit.setStones(1);
        testPit.play();
        Assert.assertEquals(initPit.finalScore(), "Lost :(");
    }

    @Test
    public void VerifyWinStateThree(){
        Pit initPit = new Pit();
        Pit testPit = (Pit) initPit.getNextPit(3);
        for (int i = 0; i < 6; i++){
            initPit.getNextPit(i).setStones(0);
        }
        testPit.setStones(1);
        testPit.getKalaha().setStones(20);
        testPit.play();
        Assert.assertEquals(initPit.finalScore(), "Tie.");
    }
}


package com.algonquincollege.dosh0005.hilo_game;

import java.util.Random;

/**
 * Created by Harsh Doshi - dosh0005
 * Date: 2017-10-05
 * Purpose : HiLo Game
 */

public class GameHiLo {

    private static final short GUESS_LIMIT = 10;
    private short randomNumber, userInput, userInutCount;

    private String playerName;

    private String returnMsg;

    // CONSTRUCTORS
    public GameHiLo(String PlayerName) {
        setPlayerName(PlayerName);
        gameReset();
    }

    // SET METHODS
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setUserInutCount(short userInutCount) {
        this.userInutCount = userInutCount;
    }

    public void setRandomNumber() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1001);
        this.randomNumber = (short) randomNumber;
    }

    public void setUserInput(short userInput) {
        incrementUserInputCount();
        this.userInput = userInput;
    }

    public void setRMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public void incrementUserInputCount() {
        short uic = getUserInutCount();
        uic = (short) (uic + 1);
        setUserInutCount(uic);
    }

    // GET METHODS
    public String getPlayerName() {
        return playerName;
    }

    public short getUserInutCount() {
        return userInutCount;
    }

    public short getRandomNumber() {
        return randomNumber;
    }

    public static short getGuessLimit() {
        return GUESS_LIMIT;
    }

    public short getUserInput() {
        return userInput;
    }

    public String getRMsg() {
        return returnMsg;
    }

    // Player input and game play
    public boolean userAttempt(short userInput) {
        setUserInput(userInput);
        short uic = getUserInutCount();
        short gl = getGuessLimit();
        short rn = getRandomNumber();

        if (uic <= gl) {
            if (rn == userInput) {
                if (uic <= (gl / 2)) {
                    setRMsg("winm1");
                } else {
                    setRMsg("winf2");
                }
                return true;
            } else {
                if (userInput < rn) {
                    setRMsg("fail1");
                    return false;
                }
                if (userInput > rn) {
                    setRMsg("fail2");
                    return false;
                }
            }
        } else {
            setRMsg("fail3");
            return false;
        }
        setRMsg("Error in userAttempt");
        return false;
    }


    // game reset function
    public void gameReset() {
        setUserInutCount((short) 0);
        setRandomNumber();
    }

    // count user's remaining plays
    public short getGuessRemain() {
        short r = (short) (getGuessLimit() - getUserInutCount());
        if (r < 0)
            r = 0;
        return r;
    }

}

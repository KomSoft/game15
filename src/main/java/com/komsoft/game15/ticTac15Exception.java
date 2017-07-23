package com.komsoft.game15;

/**
 * Exception для игры "Пятнашки"
 * Created by John on 16.07.2017.
 */
public class ticTac15Exception extends Exception {
    private String msg;

    ticTac15Exception(String s) {
        super(s);
        msg = s;
    }

   public String toString() {
        return "TicTac15 Exception: " + msg;
    }

}

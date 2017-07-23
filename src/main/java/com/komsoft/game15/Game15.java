package com.komsoft.game15;

import java.nio.charset.Charset;

/**
 * Игра "Пятнашки"
 * Created by John on 15.07.2017.
 */
public class Game15 {
    private final int MAXSIZE = 10;
    private int lSize = 0;
    private int[][] gameField;

    private boolean gameOver = false;
    private boolean won = false;
    private Charset currentCharset;

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWon() {
        return won;
    }

    //    public Game15(int ii) throws TicTac15Exception {
    public void init(int ii) throws TicTac15Exception {
        if (ii < 2 || ii > MAXSIZE) {
            gameOver = true;
            throw new TicTac15Exception("Size of field must be 'Integer' from 2 to " + MAXSIZE);
        }
        lSize = ii;
        gameField = new int[lSize][lSize];
        for (int i = 1; i < lSize * lSize; i++) {  // перебираем все числа для занесения в ячейки
            int k;
            do {
                k = (int) Math.round(Math.random() * (lSize * lSize - 1)); // получить номер ячейки
            } while (gameField[k / lSize][k % lSize] != 0);  // крутим, пока не найдем пустую ячейку
            gameField[k / lSize][k % lSize] = i;   // как нашли пустую - заносим в нее порядковый номер
        }
/*
        SortedMap<String, Charset> charsets = Charset.availableCharsets();//список доступных кодировок
        for (SortedMap.Entry<String, Charset> entry : charsets.entrySet()) {
            System.out.println(entry);
        }
        currentCharset = Charset.defaultCharset();//узнать текущую кодировку
        System.out.println("Test message: current Charset is " + currentCharset);
*/
    }

    public void outField() {
        final char BLU = '\u250e', BHOR = '\u2501', BHORDOWN = '\u2530', BRU = '\u2512';
        final char BVERT = '\u2503', BLCROSS = '\u2520', BRCROSS = '\u2526', BCROSS = '\u2540';
        final char BLD = '\u2516', BHORUP = '\u2538', BRD = '\u251a';

        if (lSize < 2) return;
/*
        try {
            System.out.print(new String(new String("" + BLU + BHOR + BHOR + BHOR + BHOR).getBytes(), "Cp866"));
        } catch (UnsupportedEncodingException ue) {
            System.out.println("Incorrect encoding;");

        }
*/
        System.out.print("" + BLU + BHOR + BHOR + BHOR + BHOR);
        for (int j = 1; j < lSize; j++) {
            System.out.print("" + BHORDOWN + BHOR + BHOR + BHOR + BHOR);
        }
        System.out.println(BRU);
        for (int i = 0; i < lSize; i++) {
            for (int j = 0; j < lSize; j++) {
                System.out.print("" + BVERT + "    ");
            }
            System.out.println(BVERT);
            for (int j = 0; j < lSize; j++) {
                if (gameField[i][j] == 0) {
                    System.out.print("" + BVERT + "    ");
                } else {
                    System.out.printf("" + BVERT + " %2d ", gameField[i][j]);
                }
            }
            System.out.println(BVERT);
            for (int j = 0; j < lSize; j++) {
                System.out.print("" + BVERT + "    ");
            }
            System.out.println(BVERT);
            if (i < lSize - 1) {
                System.out.print("" + BLCROSS + BHOR + BHOR + BHOR + BHOR);
                for (int j = 1; j < lSize; j++) {
                    System.out.print("" + BCROSS + BHOR + BHOR + BHOR + BHOR);
                }
                System.out.println(BRCROSS);
            } else {
                System.out.print("" + BLD + BHOR + BHOR + BHOR + BHOR);
                for (int j = 1; j < lSize; j++) {
                    System.out.print("" + BHORUP + BHOR + BHOR + BHOR + BHOR);
                }
                System.out.println(BRD);
            }
        }
    }

    private void checkComplete() { // throws TicTac15Exception {
        for (int i = 0; i < lSize * lSize - 1; i++) {
            if (gameField[i / lSize][i % lSize] != i + 1) {
                return;
            }
        }
        gameOver = true;
        won = true;
    }

    public void moveElem(String s) throws TicTac15Exception {
        int elem;
        try {
            elem = Integer.parseInt(s);
            if (elem < 0 || (elem > (lSize * lSize - 1))) {
                throw new TicTac15Exception("Element's number must be 'Integer' from 1 to " + (lSize * lSize - 1));
            }
        } catch (NumberFormatException ae) {
            throw new TicTac15Exception("Element's number must be 'Integer'");
        }
        if (elem == 0) {
            gameOver = true;
            return;
        }
        // находим положение элемента
        int i = lSize;
        int j = lSize;
        for (int k1 = 0; k1 < lSize; k1++) {
            for (int k2 = 0; k2 < lSize; k2++) {
                if (gameField[k1][k2] == elem) {
                    i = k1;
                    j = k2;
                    break;
                }
            }
        }
        if (i >= lSize || j >= lSize) {
            throw new TicTac15Exception("Ooops! Cannot find element " + elem);
            // хотя такого быть не должно!
        }
        // смотрим, в какую сторону можно двинуть
        if (i - 1 >= 0 && gameField[i - 1][j] == 0) {
//        if ( gameField[i-1][j] == 0 ) { // для проверки теста уберем проверку выхода за пределы
            gameField[i - 1][j] = gameField[i][j];
            gameField[i][j] = 0;
            checkComplete();
            return;
        }
        if (i + 1 < lSize && gameField[i + 1][j] == 0) {
            gameField[i + 1][j] = gameField[i][j];
            gameField[i][j] = 0;
            checkComplete();
            return;
        }
        if (j - 1 >= 0 && gameField[i][j - 1] == 0) {
            gameField[i][j - 1] = gameField[i][j];
            gameField[i][j] = 0;
            checkComplete();
            return;
        }
        if (j + 1 < lSize && gameField[i][j + 1] == 0) {
            gameField[i][j + 1] = gameField[i][j];
            gameField[i][j] = 0;
            checkComplete();
            return;
        }
        throw new TicTac15Exception("Element " + elem + " cannot be move!");
    }

 /*   public static void main(String[] args) {
        int i;
        String s;

        Game15 ng = new Game15();
        try {
            i = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException | NumberFormatException ie) {
            System.out.println("Please enter field size in command line, 'Integer' value (2 or above).");
            System.out.println("Example: 'java Game15 3'");
            return;
//            System.exit(-1);
//        } catch (NumberFormatException ne) {
//            System.out.println("Please, enter 'Integer' value of field size in command line.");
//            System.exit(-1);
        }
        try {
            ng.init(i);
        } catch (TicTac15Exception te) {
            System.out.println(te.getMessage());
        }

        while (!ng.gameOver) {
            ng.outField();
            System.out.println("Enter number that you want to move, or 0 for Exit: ");
            try {
                BufferedReader br = new BufferedReader( new InputStreamReader(System.in, "Cp866"));
                s = br.readLine();
            } catch (Exception e){
                s = "Error";
            }
            try {
            ng.moveElem(s);
            } catch (TicTac15Exception te) {
                System.out.println(te.getMessage());
            }
            if ( ng.isWon() ) {
                ng.outField();
                System.out.println("Congratulation! You Won!");
            }
        }
    }

    */

}


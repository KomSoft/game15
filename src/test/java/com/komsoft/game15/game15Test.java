package com.komsoft.game15;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Тесты для игры "Пятнашки"
 * Created by John on 16.07.2017.
 */
public class game15Test {
    private game15 game = new game15();

    //    @Test (expected ticTac15Exception.class)
    @Test
    public void init() throws Exception {
        boolean thrown = false;
        try {
            game.init(-1);
        } catch (ticTac15Exception te) {
            thrown = true;
        }
        assertTrue(thrown);
        thrown = false;
        try {
            game.init(1);
        } catch (ticTac15Exception te) {
            thrown = true;
        }
        assertTrue(thrown);
        for (int i = 2; i <= 10; i++) {
            thrown = true;
            try {
                game.init(i);
            } catch (ticTac15Exception te) {
                thrown = false;
            }
            assertTrue(thrown);
        }
    }

    @Test
    public void outField() throws Exception {
        game.outField();
        game.init(10);
        game.outField();
        game.init(2);
        game.outField();
    }

    @Test
    public void moveElem() throws Exception {
        boolean unhandledException = false;
        String s = "";
        int k = 6;
        game.init(k);
       // проверим, чтобы отлавливался нужный exception
        try {
            game.moveElem("not_integer");
        } catch (ticTac15Exception te) {
            // do nothing
        } catch (Throwable unhandledE) {
            unhandledException = true;
            s = unhandledE.getMessage();
        }
        assertTrue("Unhandled Exception: " + s, !unhandledException);
        // Попробуем подвигать все элементы (не выйдет ли индекс за границы поля)

        for (int i = k - 2; i < k * k + 2; i++) {
            try {
                game.moveElem("" + i);
            } catch (ticTac15Exception te) {
                // do nothing
            } catch (Throwable unhandledE) {
                unhandledException = true;
                s = unhandledE.getMessage();
            }
            assertTrue("Unhandled Exception: " + s, !unhandledException);
        }
    }

/*    @Test
    public void main() throws Exception {
        String[] s;
        s = new String[]{};
        game15.main(s);
        s = new String[]{"test"};
        game15.main(s);
//        s = new String[]{"8"};
//        game15.main(s);
        s = new String[]{"15"};
        game15.main(s);
    }
*/
}

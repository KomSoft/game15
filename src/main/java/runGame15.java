import com.komsoft.game15.*;
import java.io.*;

/**
 * Main class для игры в пятнашки
 * Created by John on 20.07.2017.
 */
public class runGame15 {

    public static void main(String[] args) {
        int i = 3;
        String s;

        game15 newGame15 = new game15();
        try {
            i = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException | NumberFormatException ie) {
            System.out.println("Please enter field size in command line, 'Integer' value (2 or above).");
            System.out.println("Example: 'java game15 3'");
//            return;
            System.exit(-1);
        }
        try {
            newGame15.init(i);
        } catch (ticTac15Exception te) {
            System.out.println(te.getMessage());
        }

        while (!newGame15.isGameOver()) {
            newGame15.outField();
            System.out.println("Enter number that you want to move, or 0 for Exit: ");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "Cp866"));
                s = br.readLine();
            } catch (Exception e) {
                s = "Error";
            }
            try {
                newGame15.moveElem(s);
            } catch (ticTac15Exception te) {
                System.out.println(te.getMessage());
            }
        }
        if (newGame15.isWon()) {
            newGame15.outField();
            System.out.println("Congratulation! You Won!");
        }

    }

}

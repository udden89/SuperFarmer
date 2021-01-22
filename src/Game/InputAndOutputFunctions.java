package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class InputAndOutputFunctions {


    public static String line = "________________________________________";
    public static String wrongInput = "Incorrect input, please enter a valid key";


    public static int convertStringToInt() {

        Scanner scan = new Scanner(System.in);

        while(true){
            try{
                return Integer.parseInt(scan.nextLine());
            }catch(Exception exception) {
                System.out.println("Please enter a correct number");
            }
        }
    }

    public static String inputString() {

        Scanner scan = new Scanner(System.in);

        while(true){
            try{
                return scan.nextLine();
            }catch(Exception exception) {
                System.out.println("Please enter something");
            }
        }
    }

    public static void pressEnterToContinue(){
        System.out.println(line);
        System.out.println("Press ENTER to continue...");
        try{
            System.in.read();
        }catch (Exception e){

        }
    }


}

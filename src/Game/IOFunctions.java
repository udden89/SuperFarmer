package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

//Input & output functions
public class IOFunctions {


    public static String line = "________________________________________";
    public static String wrongInput = "Incorrect input, please enter a valid key";
    public static String notInStock = "Sorry, you don't have any in stock!";


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

    public static boolean tryInt(){
        try{

            return true;
        }
        catch (java.lang.IndexOutOfBoundsException e){
            return false;
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

    public static boolean areYouSure(String areYouSureYouWantTo){
        System.out.println("Are you sure you want to " + areYouSureYouWantTo + " This will end your turn.");
        System.out.println("[Y] - Yes");
        System.out.println("[N] - No");

        String choice = inputString();

        if(choice.equalsIgnoreCase("Y")){
            return true;
        }
        else if(choice.equalsIgnoreCase("N")) {
            return false;
        }
        return areYouSure(areYouSureYouWantTo);
    }
}

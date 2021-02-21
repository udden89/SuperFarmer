package game;

import java.util.Scanner;

//Input & output functions
public class IOFunctions {

    public static String notInStock = "Sorry, you don't have any in stock!";

    public static void printLine(){
        String star = "¤";
        System.out.println();
        System.out.println();
        System.out.println(star.repeat(50));
        System.out.println();
        System.out.println();

    }

    public static void printSomethingWithThreadSleep(String whatToPrint, int forHowLongInMS){

        try{
            for(int i = 0; i < whatToPrint.length(); i++){
                System.out.print(whatToPrint.charAt(i));
                Thread.sleep(forHowLongInMS);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }


    public static int convertStringToInt(int min, int max) {

        Scanner scan = new Scanner(System.in);

        while(true){
            try{
                int number = Integer.parseInt(scan.nextLine());
                if(number < min || number > max){
                    System.out.println("Please enter a correct number");
                    continue;
                }

                return number;

            }catch(Exception exception) {
                System.out.println("1");
                System.out.println("Please enter a correct number");
            }
        }
    }

    //Overload method
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

    public static boolean printAndAskIfUserAreSure(String areYouSureYouWantTo){
        System.out.println(areYouSureYouWantTo);
        System.out.println("[Y] - Yes");
        System.out.println("[N] - No");

        String choice = inputString();

        if(choice.equalsIgnoreCase("Y")){
            return true;
        }
        else if(choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("0")) {
            return false;
        }
        return printAndAskIfUserAreSure(areYouSureYouWantTo);
    }

    public static int randomNumber(int minNumber, int maxNumber){

        int min = minNumber;
        int max = maxNumber;

        return (int)(Math.random() * max) + min;

    }


}

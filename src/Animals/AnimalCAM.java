package Animals;

import Game.InputAndOutputFunctions;

import java.util.Scanner;

//CAM Stands for calculations and menus
public class AnimalCAM {

    public static String inputGenderOfNewAnimal(String animalType) {

        System.out.println(InputAndOutputFunctions.line);
        System.out.println("What gender do you want on your new " + animalType + "?" );
        System.out.println("Press F for female");
        System.out.println("Press M for male");

        while (true) {

            String choice = InputAndOutputFunctions.inputString().toUpperCase();

            if (choice.equals("F")) {
                return "Female";
            } else if (choice.equals("M")) {
                return "Male";
            } else {
                System.out.println("Please enter F or M");
            }
        }

    }

    public static String inputNameOfNewAnimal(String animalType){

        System.out.println(InputAndOutputFunctions.line);
        System.out.println("Give your new " + animalType + " a name: ");

        return InputAndOutputFunctions.inputString();
    }

    public static void printAnimalInfo(String animalType){

        switch (animalType.toUpperCase()){

            case "WOLF":
                System.out.println(InputAndOutputFunctions.line);
                System.out.println("About the wolves: ");
                break;

            case "PANDA":
                System.out.println(InputAndOutputFunctions.line);
                System.out.println("About the pandas: ");
                break;

            case "BEAR":
                System.out.println(InputAndOutputFunctions.line);
                System.out.println("About the bears: ");
                break;

            case "EAGLE":
                System.out.println(InputAndOutputFunctions.line);
                System.out.println("About the eagles: ");
                break;

            case "BABY JEDI":
                System.out.println(InputAndOutputFunctions.line);
                System.out.println("About the baby jedi´s: ");
                break;

            default:
                System.out.println("Something went wrong in printAnimalInfo");

        }







    }


}

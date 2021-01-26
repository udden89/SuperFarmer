package Animals;

import Game.IOFunctions;
import Player.Player;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class AnimalCAM {

    public static String inputGenderOfNewAnimal(String animalType) {

        System.out.println(IOFunctions.line);
        System.out.println("What gender do you want on your new " + animalType + "?" );
        System.out.println("Press F for female");
        System.out.println("Press M for male");

        while (true) {

            String choice = IOFunctions.inputString().toUpperCase();

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

        System.out.println(IOFunctions.line);
        System.out.println("Give your new " + animalType + " a name: ");

        return IOFunctions.inputString();
    }

    public static void printAnimalInfo(String animalType){

        switch (animalType.toUpperCase()){

            case "WOLF":
                System.out.println(IOFunctions.line);
                System.out.println("About the wolves: ");
                break;

            case "PANDA":
                System.out.println(IOFunctions.line);
                System.out.println("About the pandas: ");
                break;

            case "BEAR":
                System.out.println(IOFunctions.line);
                System.out.println("About the bears: ");
                break;

            case "EAGLE":
                System.out.println(IOFunctions.line);
                System.out.println("About the eagles: ");
                break;

            case "BABY JEDI":
                System.out.println(IOFunctions.line);
                System.out.println("About the baby jedi´s: ");
                break;

            default:
                System.out.println("Something went wrong in printAnimalInfo");

        }

    }

    public static void decreaseAnimalHealthAndAgePerRound(Player player, ArrayList<Animal> animals){

        for(Animal animal : animals){
            int health = animal.getMaxHealth();
            double min = (0.1*health);
            double max = ((0.3*health)-min)+1;
            animal.setHealth(animal.getHealth() - ((int) (Math.random() * (max))+ (int)min));
            animal.setAge(animal.getAge()+1);

            if(animal.getHealth() <= 0 || animal.getAge() == animal.getDeathAtAge()){
                System.out.println(player.getPlayerName() + "Your " + animal.getName() + " has died");
                player.getDeadAnimals().add(animal);
                animals.remove(animal);
            }

        }

    }

}

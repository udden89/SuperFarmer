package Animals;

import Game.Game;
import Game.IOFunctions;
import Game.RandomGameMode;
import Player.Player;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class AnimalHelper {


    public static String genderOfNewAnimal(String animalType, boolean random) {

        if (random){
            int number = (int) (Math.random() * 10) + 1;
            return number <= 5 ? "FEMALE" : "MALE";
        }

        System.out.println(IOFunctions.line);
        System.out.println("What gender do you want on your new " + animalType + "?" );
        System.out.println("Press F for female");
        System.out.println("Press M for male");

        while (true) {

            String choice = IOFunctions.inputString().toUpperCase();

            if (choice.equals("F")) {
                return "FEMALE";
            } else if (choice.equals("M")) {
                return "MALE";
            } else {
                System.out.println("Please enter F or M");
            }
        }

    }

    public static String inputNameOfNewAnimal(String animalType){

        System.out.println(IOFunctions.line);
        System.out.println("Give your new " + animalType + " a name: ");

        return RandomGameMode.randomName();//TODO remove this when not debugging and activate the line below
        //return IOFunctions.inputString();
    }

    public static void printAboutAnimals(String animalType){

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

    public static void printAnimalToChoose(ArrayList<Animal> animals, String menuName , String verb){

        System.out.println(IOFunctions.line);
        System.out.println(menuName + "... ");
        System.out.println(IOFunctions.line + "\n");

        int number = 1;

        for (Animal animal : animals) {

            System.out.println("Enter " + number + " to " + verb + " your "
                    + animal.getName().toUpperCase() + " (type: "
                    + animal.getClass().getSimpleName() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;

        }

        System.out.println("Press 0 to go back.");

    }

    //Decrease health, increase age and making animal sick.
    public static void decreaseAnimalHealthAndAgePerRound(){

        for (int i = 0; i < Game.players.size(); i++){

            ArrayList<Animal> animals = Game.players.get(i).animals;
            Player player = Game.players.get(i);

            for(int j = 0; j < animals.size(); j++){

                Animal animal = animals.get(j);

                int health = animal.getMaxHealth();
                int sickRandom = (int) (Math.random() * 100) + 1;
                double min = (0.1 * health); //Min 10% of health
                double max = ( (0.3 * health) -min) +1; //Max 30% of health
                int decreaseHealth = (int) (Math.random() * (max))+ (int)min;

                animal.setHealth(animal.getHealth() - decreaseHealth);
                animal.setAge(animal.getAge()+1);

                //If animal is already sick remove a "life" if available.
                if(animal.isSick){
                    animal.roundsBeforeDeathIfSick--;
                }

                //If health < 0, animal reached max health or animal is sick remove the animal.
                if(animal.getHealth() <= 0
                        || animal.getAge() == animal.getDeathAtAge()
                        || animal.roundsBeforeDeathIfSick == 0){

                    System.out.println(player.getPlayerName() + ", your " + animal.getName() + " has died");
                    animals.remove(animal);
                    i--;
                    continue;
                }

                if(!animal.isSick && sickRandom <= 20){
                    animal.setSick(true);
                    System.out.println(player.getPlayerName() + ", your " + animal.getName() + " has become ill and needs to see a veterinary"
                            + " (otherwise it will die under the next 1-3 rounds).");
                }

            }

        }

    }

}

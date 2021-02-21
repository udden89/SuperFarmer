package animals;

import game.Game;
import game.IOFunctions;
import game.RandomGameMode;
import player.Player;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class AnimalHelper {


    public static String setGenderOfNewAnimal(String animalType, boolean createWithRandomGender) {

        if (createWithRandomGender){
            int number = (int) (Math.random() * 10) + 1;
            return number <= 5 ? "FEMALE" : "MALE";
        }

        IOFunctions.printLine();
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

        IOFunctions.printLine();
        System.out.println("Give your new " + animalType + " a name: ");

        //return RandomGameMode.randomName();// RANDOM NAME GENERATOR
        return IOFunctions.inputString();
    }


    public static void printAnimalToChoose(ArrayList<Animal> animals, String menuName , String verb){

        IOFunctions.printLine();
        System.out.println(menuName + "... ");
        IOFunctions.printLine();

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

    //Decrease health, increase age and make animal sick.
    public static void decreaseAnimalHealthAndAgePerRound(){

        //Loop through all players
        for (int i = 0; i < Game.players.size(); i++){

            ArrayList<Animal> animals = Game.players.get(i).animals;
            Player player = Game.players.get(i);

            //Loop through all animals and decrease its health & increase its age.
            for(int j = animals.size()-1; j >= 0; j--){

                Animal animal = animals.get(j);

                int health = animal.getMaxHealth();
                int sickRandomGenerator = (int) (Math.random() * 100) + 1;
                double min = (0.1 * health); //Min 10% of health
                double max = ( (0.3 * health) -min) +1; //Max 30% of health
                int decreaseHealth = (int) (Math.random() * (max))+ (int)min;

                animal.setHealth(animal.getHealth() - decreaseHealth);
                animal.setAge(animal.getAge()+1);

                //If animal is already sick remove a "life" if available.
                if(animal.isSick){
                    animal.roundsBeforeDeathIfSick--;
                }


                if(animal.getHealth() <= 0){
                    System.out.println(player.getPlayerName() + ", your " + animal.getName() + " has died due to health was below 0");
                    animals.remove(animal);
                    continue;
                }

                if(animal.getAge() == animal.getDeathAtAge()){
                    System.out.println(player.getPlayerName() + ", your " + animal.getName() + " has died because of its age");
                    animals.remove(animal);
                    continue;
                }

                if(animal.roundsBeforeDeathIfSick == 0){
                    System.out.println(player.getPlayerName() + ", your " + animal.getName() + " has died because of its illness");
                    player.setSickAnimals(player.getSickAnimals()-1);
                    animals.remove(animal);
                    continue;
                }

                if(!animal.isSick && sickRandomGenerator <= 20){
                    IOFunctions.printLine();
                    animal.setSick(true);
                    player.setSickAnimals(player.getSickAnimals()+1);
                    IOFunctions.printSomethingWithThreadSleep(player.getPlayerName() + ", your " + animal.getName() + " has become ill and needs to see a veterinary"
                            + " (otherwise it will die under the next 1-3 rounds).", 5);

                }

            }

        }

    }

}

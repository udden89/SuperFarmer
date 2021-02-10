package Store;

import Animals.Animal;
import Game.IOFunctions;
import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Veterinary {

    public static HashMap<String, Integer> curePrices = new HashMap<>();

    public static boolean cureAnimal(Player player, ArrayList<Animal> animals){

        curePrices.put("WOLF", 500);
        curePrices.put("PANDA", 1250);
        curePrices.put("BEAR", 750);
        curePrices.put("EAGLE", 500);
        curePrices.put("BABY JEDI", 1500);

        System.out.println(IOFunctions.line);
        System.out.println("Visiting the veterinary... ");
        System.out.println(IOFunctions.line + "\n");


        ArrayList<Animal> sickAnimals = new ArrayList<>();

        //Puts all sick animals into an temp array
        for (Animal animal : animals) {
            if(animal.isSick) {
                sickAnimals.add(animal);
                player.setSickAnimals(player.getSickAnimals()+1);
            }
        }

        if(sickAnimals.isEmpty()){
            System.out.println("You have no sick animals!");
            return false;
        }

        int number = 1;
        for(Animal sickAnimal : sickAnimals){

            System.out.println("Enter " + number + " to cure your "
                    + sickAnimal.getName().toUpperCase() + " for "
                    + curePrices.get(sickAnimal.animalType) + " gold (type: "
                    + sickAnimal.getClass().getSimpleName() + ", health: "
                    + sickAnimal.getHealth() + ", gender: "
                    + sickAnimal.getGender() + ").");

            number++;

        }

        System.out.println("Press 0 to go back.");

        int index = IOFunctions.convertStringToInt(0,sickAnimals.size())-1;

        //Error handler for arraylist out of bound
        if(index < 0){
            return false;
        }

        Animal animal = sickAnimals.get(index);

        System.out.println(IOFunctions.line);
        if(IOFunctions.areYouSure("Try to cure your animal (50% chance of success)?")){

            //Checks if afforded with veterinary
            if(StoreHelper.askEnoughWithGold(player.getGold(), curePrices.get(animal.animalType))) {

                int random = IOFunctions.randomNumber(1,100);
                System.out.println(random);
                if(random >= 50){
                    animal.isSick = false;
                    player.setGold(player.getGold()-curePrices.get(animal.animalType));
                    System.out.println("You have successfully cured your " + animal.getName() + "!");
                    player.setSickAnimals(player.getSickAnimals()-1);
                }
                else{
                    player.setGold(player.getGold()-curePrices.get(animal.animalType));
                    System.out.println("Sorry, the veterinary tried her best!");
                }
                return true;

            }else{ //If not enough with gold.
                return false;
            }
        }
        return false;
    }

}

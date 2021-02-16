package store;

import animals.Animal;
import game.IOFunctions;
import player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Veterinary {

    public static HashMap<String, Integer> curePrices = new HashMap<>();

    //Method to put items in the hashMap above at start of game
    public static void addAllPricesToVeterinary(){

        if(curePrices.isEmpty()){
            curePrices.put("WOLF", 250);
            curePrices.put("PANDA", 625);
            curePrices.put("BEAR", 375);
            curePrices.put("EAGLE", 250);
            curePrices.put("BABY JEDI", 750);
        }

    }

    public static boolean startProcessOfCuringAnimal(Player player, ArrayList<Animal> animals){

        ArrayList<Animal> sickAnimals = new ArrayList<>();

        IOFunctions.printLine();
        System.out.println("Visiting the veterinary... ");
        IOFunctions.printLine();

        addAllPricesToVeterinary();

        putAllSickAnimalsIntoAnArray(player, animals, sickAnimals);
        printIfPlayerHasNoSickAnimal(sickAnimals);

        printAPlayersSickAnimals(sickAnimals);

        int animalIndex = IOFunctions.convertStringToInt(0, sickAnimals.size());

        //If user want to go back to main menu
        if(animalIndex <= 0){
            return false;
        }

        Animal animal = sickAnimals.get(animalIndex-1); //TODO Double check this index logic

        IOFunctions.printLine();
        if(IOFunctions.printAndAskIfUserAreSure("" +
                "Do you want to try and cure your animal (50% chance of success)?")){

            //Checks if afforded with veterinary
            if(Store.checksIfPlayerHasEnoughGold(player, curePrices.get(animal.animalType))) {
                cureAnimal(player, animal);
                return true;
            }
        }
        return false;
    }

    private static void putAllSickAnimalsIntoAnArray
            (Player player, ArrayList<Animal> animals, ArrayList<Animal> sickAnimals) {
        for (Animal animal : animals){
            if(animal.isSick) {
                sickAnimals.add(animal);
                player.setSickAnimals(player.getSickAnimals()+1);
            }
        }
    }

    private static void printIfPlayerHasNoSickAnimal(ArrayList<Animal> sickAnimals){
        if(sickAnimals.isEmpty()){
            System.out.println("You have no sick animals!");
        }
    }

    private static void printAPlayersSickAnimals(ArrayList<Animal> sickAnimals){

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
    }

    private static void cureAnimal(Player player, Animal animal){

        int random = IOFunctions.randomNumber(1, 100);
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

    }

}

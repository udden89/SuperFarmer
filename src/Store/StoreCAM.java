package Store;

import Animals.Animal;
import Animals.AnimalCAM;
import Game.IOFunctions;
import Game.*;

import java.util.ArrayList;

public class StoreCAM {

    public static String askQuantity = "How many do you want to buy? ";


    public static void printBuyAnimalMenu() {

        System.out.println(IOFunctions.line);
        System.out.println("Buying animals...:");
        System.out.println(IOFunctions.line);

        System.out.println("Press 1: Buy a WOLF for " + Store.animalPrices.get("WOLF") + " gold");
        System.out.println("Press 2: Buy a PANDA for " + Store.getPandaPrice() + " gold");
        System.out.println("Press 3: Buy a BEAR for " + Store.getBearPrice() + " gold");
        System.out.println("Press 4: Buy a EAGLE for " + Store.getEaglePrice() + " gold");
        System.out.println("Press 5: Buy a BABY JEDI for " + Store.getBabyJediPrice() + " gold" );
        System.out.println("\nPress 0: To go back");
    }

    public static void printFoodMenu(){

        System.out.println(IOFunctions.line);
        System.out.println("Buying delicious food");
        System.out.println(IOFunctions.line);

        System.out.println("Press 1: Buy FISH for " + Store.getFishPricePerKilo() + "/kg gold");
        System.out.println("Press 2: Buy MEAT for " + Store.getMeatPricePerKilo() + "/kg gold");
        System.out.println("Press 3: Buy SALLAD for " + Store.getSaladPricePerKilo() + "/kg gold");
        System.out.println("\nPress 0 to go back");

    }

    public static void printSellMenu(ArrayList<Animal> animals){

        System.out.println(IOFunctions.line);
        System.out.println("Selling animal ");
        System.out.println(IOFunctions.line + "\n");

        int number = 1;

        for (Animal animal : animals) {

            System.out.println("Enter " + number + " to sell your "
                    + animal.getName().toUpperCase() + " for "
                    + Store.animalPrices.get(animal.animalType) + " gold (type: "
                    + animal.getAnimalType() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;
        }

        System.out.println("Press 0 to go back.");
        int choice = IOFunctions.convertStringToInt();

        if(Game.players.size() > 1){
            System.out.println("Press 1 to sell to another player");
            System.out.println("Press 2 to sell to the store");
        }

    }

    public static boolean askEnoughWithGold(int playerGold, int price){

        //Error handler for "java.lang.ArithmeticException" when calculating max.
        if(price == 0){
            return false;
        }

        int max = (playerGold / price);

        if(max <= 0){
            System.out.println("Inefficient gold, you need at least " + (price - playerGold) + " more gold.");
            return false;
        }
        return true;
    }

    public static int totalPrice(int price, int quantity){

        return price * quantity;
    }

}





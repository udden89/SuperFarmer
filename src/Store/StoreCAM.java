package Store;

import Game.IOFunctions;

public class StoreCAM {

    public static String askQuantity = "How many do you want to buy? ";


    public static void printBuyAnimalMenu() {

        System.out.println(IOFunctions.line);
        System.out.println("Buying animals...:");
        System.out.println(IOFunctions.line);

        System.out.println("Press 1: Buy a WOLF for " + Store.getWolfPrice() + " gold");
        System.out.println("Press 2: Buy a PANDA for " + Store.getPandaPrice() + " gold");
        System.out.println("Press 3: Buy a BEAR for " + Store.getBearPrice() + " gold");
        System.out.println("Press 4: Buy a EAGLE for " + Store.getEaglePrice() + " gold");
        System.out.println("Press 5: Buy a BABY JEDI for " + Store.getBabyJediPrice() + " gold" );
        System.out.println("\nPress 9: To go back");
    }

    public static void printFoodMenu(){

        System.out.println(IOFunctions.line);
        System.out.println("Buying delicious food");
        System.out.println(IOFunctions.line);

        System.out.println("Press 1: Buy FISH for " + Store.getFishPricePerKilo() + "/kg gold");
        System.out.println("Press 2: Buy MEAT for " + Store.getMeatPricePerKilo() + "/kg gold");
        System.out.println("Press 3: Buy SALLAD for " + Store.getSaladPricePerKilo() + "/kg gold");

    }

    public static boolean askEnoughWithGold(int playerGold, int price){

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





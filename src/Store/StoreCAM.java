package Store;

import Game.InputAndOutputFunctions;
import Player.Player;

public class StoreCAM {

    static Store store = new Store();

    public static String askQuantity = "How many do you want to buy?";


    public static void printBuyAnimalMenu() {

        System.out.println(InputAndOutputFunctions.line);
        System.out.println("Buying animals...:");
        System.out.println(InputAndOutputFunctions.line);

        System.out.println("Press 1: Buy a WOLF for " + store.getWolfPrice() + " gold");
        System.out.println("Press 2: Buy a PANDA for " + store.getPandaPrice() + " gold");
        System.out.println("Press 3: Buy a BEAR for " + store.getBearPrice() + " gold");
        System.out.println("Press 4: Buy a EAGLE for " + store.getEaglePrice() + " gold");
        System.out.println("Press 5: Buy a BABY JEDI for " + store.getBabyJediPrice() + " gold\n" );

    }

    public static boolean askEnoughWithGold(int playerGold, int price){

        int max = (playerGold / price);

        if(max <= 0){
            System.out.println("Inefficient gold, you need at least " + (price - playerGold) + " more gold.");
            return false;
        }
        return true;
    }




}





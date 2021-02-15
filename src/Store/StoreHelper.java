package Store;

import Game.IOFunctions;
import Player.Player;

public class StoreHelper {


    public static void printBuyAnimalMenu(String textOnPress0) {

        System.out.println(IOFunctions.line);
        System.out.println("Buying animals...:");
        System.out.println(IOFunctions.line);

        System.out.println("[1] - Buy a WOLF for " + Store.storePrices.get("WOLF") + " gold");
        System.out.println("[2] - Buy a PANDA for " + Store.storePrices.get("PANDA") + " gold");
        System.out.println("[3] - Buy a BEAR for " + Store.storePrices.get("BEAR") + " gold");
        System.out.println("[4] - Buy a EAGLE for " + Store.storePrices.get("EAGLE") + " gold");
        System.out.println("[5] - Buy a BABY JEDI for " + Store.storePrices.get("BABY JEDI") + " gold" );
        System.out.println("\n[0] - " + textOnPress0);
    }

    public static void printFoodMenu(String textOnPress0){

        System.out.println(IOFunctions.line);
        System.out.println("Buying delicious food");
        System.out.println(IOFunctions.line);

        System.out.println("[1] - Buy 1 kg FISH for " + Store.storePrices.get("FISH") + " gold");
        System.out.println("[2] - Buy 1 kg MEAT for " + Store.storePrices.get("MEAT") + " gold");
        System.out.println("[3] - Buy 1 kg SALAD for " + Store.storePrices.get("SALAD") + " gold");
        System.out.println("\n[0] - " + textOnPress0);

    }

//    public static void playerWantToShopAgain(Player player, String whatToBuy){
//        if(IOFunctions.printAndAskIfUserAreSure("Do you want to buy again? Otherwise will this end your turn")){
//           switch (whatToBuy){
//               case "ANIMAL" -> Store.buyAnimal(player, true);
//               case "FOOD" -> Store.buyFood(player, true);
//           }
//
//        }
//    }

    public static boolean askEnoughWithGold(int playerGold, int price){

        //Error handler for "java.lang.ArithmeticException" when calculating max at line 53.
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

}





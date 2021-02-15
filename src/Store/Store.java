package Store;

import Animals.*;
import Game.*;
import Game.IOFunctions;
import Player.Player;

import java.util.HashMap;

public class Store {


    public static HashMap<String, Integer> storePrices = new HashMap<>();

    //Method to put items in the hashMap above at start of game
    public static void addAllItemsToStore(){

        //Animal prices
        storePrices.put("WOLF", 1000);
        storePrices.put("PANDA", 2500);
        storePrices.put("BEAR", 1500);
        storePrices.put("EAGLE", 1000);
        storePrices.put("BABY JEDI", 3000);

        //Food prices
        storePrices.put("MEAT", 69);
        storePrices.put("FISH", 129);
        storePrices.put("SALAD", 29);

    }

    public static boolean startProcessOfBuyingAnimalFromStore(Player player){

        printAnimalsToBuyFromStore();

        String theAnimalTypePlayerChoseToBuy = userHasToChooseWhatAnimalToBuy();

        if(controlIfUserWantBackToMainMenu(theAnimalTypePlayerChoseToBuy)){
            return false; //Returns to main menu
        }

        //Check if player has enough gold, create animal if true, and remove gold from player.
        if(checksIfPlayerHasEnoughGold(player, theAnimalTypePlayerChoseToBuy)){

            createAnAnimal(player, theAnimalTypePlayerChoseToBuy, false);

            withdrawGoldFromPlayer(player, Store.storePrices.get(theAnimalTypePlayerChoseToBuy));

        }

        if(IOFunctions.printAndAskIfUserAreSure("Do you want to continue shopping animals? Otherwise your turn will end")){
            GameHelper.printMainMenu(player);
            startProcessOfBuyingAnimalFromStore(player);
        }

        return true; //start next turn

    }

    public static boolean startProcessOfBuyingFoodFromStore(Player player){

        printFoodMenu();

        String choice = IOFunctions.inputString();

        if(controlIfUserWantBackToMainMenu(choice)){
            return false; //Returns to main menu
        }

        switch (choice){
            case "1": //FISH
                if(checksIfPlayerHasEnoughGold(player,"FISH")){
                    player.setStackOfKiloFish(player.getStackOfKiloFish()+1);
                    withdrawGoldFromPlayer(player,storePrices.get("FISH"));
                }
                break;
            case "2": //MEAT
                if(checksIfPlayerHasEnoughGold(player,"MEAT")){
                    player.setStackOfKiloMeat(player.getStackOfKiloMeat()+1);
                    withdrawGoldFromPlayer(player,storePrices.get("MEAT"));
                }
                break;
            case "3": //SALAD
                if(checksIfPlayerHasEnoughGold(player,"SALAD")){
                    player.setStackOfKiloSalad(player.getStackOfKiloSalad()+1);
                    withdrawGoldFromPlayer(player,storePrices.get("SALAD"));
                }
                break;
            case "0": //Go back
                return false;
            default:
                System.out.println(IOFunctions.wrongInput);
        }

        if(IOFunctions.printAndAskIfUserAreSure("Do you want to continue shopping food? Otherwise your turn will end")){
            GameHelper.printMainMenu(player);
            startProcessOfBuyingFoodFromStore(player);
        }

        return true;
    }

    public static void printAnimalsToBuyFromStore(){

        IOFunctions.printLine();
        System.out.println("What type of animal do you want to buy?\n");

        int number = 1;
        for(String animal : Animal.typeOfAnimals){
            System.out.println("["+ number +"] - " + animal + " for " + Store.storePrices.get(animal) + " gold.");
            number++;
        }
        System.out.println("\n[0] - To go back");
    }

    public static void printFoodMenu(){

        System.out.println(IOFunctions.line);
        System.out.println("Buying delicious food");
        System.out.println(IOFunctions.line);

        System.out.println("[1] - Buy 1 kg FISH for " + Store.storePrices.get("FISH") + " gold");
        System.out.println("[2] - Buy 1 kg MEAT for " + Store.storePrices.get("MEAT") + " gold");
        System.out.println("[3] - Buy 1 kg SALAD for " + Store.storePrices.get("SALAD") + " gold");
        System.out.println("\n[0] - To go back");

    }

    public static String userHasToChooseWhatAnimalToBuy(){

        System.out.println("\nType here: ");
        int index = IOFunctions.convertStringToInt(0, Animal.typeOfAnimals.size())-1;

        if(index < 0){
            return "0";
        }

        return Animal.typeOfAnimals.get(index);
    }

    public static boolean checksIfPlayerHasEnoughGold(Player player, String whatToBuy){

        int gold = player.getGold();
        int price = storePrices.get(whatToBuy);

        if(gold < price) {
            System.out.println("Inefficient gold, you need at least " + (price - gold) + " more gold.");
        }

        return gold >= price;

    }
    public static boolean checksIfPlayerHasEnoughGold(Player player, int price){

        int gold = player.getGold();

        if(gold < price) {
            System.out.println("Inefficient gold, you need at least " + (price - gold) + " more gold.");
        }

        return gold >= price;

    }

    public static void createAnAnimal(Player player, String animalPlayerChoseToBuy, boolean createWithRandomGender){

        String gender = AnimalHelper.setGenderOfNewAnimal(animalPlayerChoseToBuy, createWithRandomGender);
        String name = AnimalHelper.inputNameOfNewAnimal(animalPlayerChoseToBuy);

        switch (animalPlayerChoseToBuy){

            case "WOLF":
                player.animals().add(new Wolf(gender, name));
                break;

            case "PANDA":
                player.animals().add(new Panda(gender, name));
                break;

            case "BEAR":
                player.animals().add(new Bear(gender, name));
                break;

            case "EAGLE":
                player.animals().add(new Eagle(gender, name));
                break;

            case "BABY JEDI":
                player.animals().add(new BabyJedi(gender, name));
                break;

            default:
                System.out.println("Something went wrong in buyProcess switch");

        }

    }

    public static void withdrawGoldFromPlayer(Player player, int amountGold){
        player.setGold(player.getGold() - amountGold);
    }

    public static boolean controlIfUserWantBackToMainMenu(String inputToControl){
        if(inputToControl.equals("0")){
            return true;
        }
        return false;
    }

    public static boolean sellAnimal(Player player){

        try{
            printSellAnimalMenu(player);
            Animal animalToSell = player.animals.get(IOFunctions.convertStringToInt()-1);
            return askUserIfSellThroughStoreOrPlayer(player, animalToSell);

        }catch (Exception error){
            return false;
        }
    }

    private static void printSellAnimalMenu(Player player) {

        IOFunctions.printLine();
        System.out.println("Selling animal ");
        IOFunctions.printLine();

        int number = 1;
        for (Animal animal : player.animals) {

            double sellPrice = ((double) animal.getHealth()/100)* storePrices.get(animal.animalType);

            System.out.println("Enter " + number + " to sell your "
                    + animal.getName().toUpperCase() + " for "
                    + sellPrice + " gold (type: "
                    + animal.getAnimalType() + ", health: "
                    + animal.getHealth() + ", gender: "
                    + animal.getGender() + ").");

            number++;
        }

        System.out.println("Press 0 to go back.");
    }

    private static boolean askUserIfSellThroughStoreOrPlayer(Player player, Animal animalToSell){

        int userChoseSellToStoreOrPlayer;

        System.out.println("[1] - Sell back to store");
        if(Game.players.size() > 1) {
            System.out.println("[2] - Sell to another player");
            userChoseSellToStoreOrPlayer = IOFunctions.convertStringToInt(1, 2);
        }else{
            userChoseSellToStoreOrPlayer = IOFunctions.convertStringToInt(1,1);
        }

        if(userChoseSellToStoreOrPlayer == 2 && (Game.players.size() > 1)){
            return sellAnimalToOtherPlayer(player, animalToSell);
        }else {
            return sellAnimalToStore(player, animalToSell);
        }

    }

    private static boolean sellAnimalToOtherPlayer(Player player, Animal animalToSell){

        System.out.println("Choose which player you want to sell to");
        printAllPlayers();

        Player playerToSellTo = Game.players.get(IOFunctions.convertStringToInt( 1, Game.players.size()));

        boolean areYouSure;
        areYouSure = IOFunctions.printAndAskIfUserAreSure("Do you both agree on this transaction of " + animalToSell.getName()+ "?");

        double sellPrice = ((double)animalToSell.getHealth()/100)* storePrices.get(animalToSell.animalType);
        if(areYouSure) {
            player.setGold((int) (player.getGold() + sellPrice));

            //Add and remove animal between owners.
            playerToSellTo.animals().add(animalToSell);
            player.animals.remove(animalToSell);

            //Removes gold from buyer
            withdrawGoldFromPlayer(playerToSellTo, (int)sellPrice);

            //Adds gold to seller
            player.setGold(player.getGold() + (int)sellPrice);
        }

        return areYouSure;
    }

    private static boolean sellAnimalToStore(Player player, Animal animalToSell){

        double sellPrice = ((double)animalToSell.getHealth()/100)* storePrices.get(animalToSell.animalType);

        boolean areYouSure;
        areYouSure = IOFunctions.printAndAskIfUserAreSure("Are you sure you want to sell " + animalToSell.getName()+ "? This will end your turn");

        if(areYouSure) {
            player.setGold((int) (player.getGold() + sellPrice));
            player.animals.remove(animalToSell);
        }
        return areYouSure;
    }

    private static void printAllPlayers(){

        int counter = 1;
        //Prints all available players
        for(Player players : Game.players){
            if(!players.getPlayerName().equalsIgnoreCase(Game.players.get(0).getPlayerName())){
                System.out.println("[" + counter + "] - " + players.getPlayerName());
                counter++;
            }

        }

    }

}

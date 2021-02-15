package Store;

import Animals.*;
import Game.*;
import Game.IOFunctions;
import Player.Player;

import java.util.ArrayList;
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

        if(!controlIfUserWantBackToMainMenu(theAnimalTypePlayerChoseToBuy)){
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

        if(!controlIfUserWantBackToMainMenu(choice)){
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
            return false;
        }
        return true;
    }

    //TODO Shorten this method!
    public static boolean sellAnimal(Player player, ArrayList<Animal> animals){

        boolean areYouSure;

        System.out.println(IOFunctions.line);
        System.out.println("Selling animal ");
        System.out.println(IOFunctions.line + "\n");


        int number = 1;
        for (Animal animal : animals) {

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
        int indexOfAnimalToSell = IOFunctions.convertStringToInt()-1;

        if(indexOfAnimalToSell == -1){ //Not to get java.lang.IndexOutOfBoundsException when using 0 as "go back".
            return false;
        }
        double sellPrice = ((double)animals.get(indexOfAnimalToSell).getHealth()/100)* storePrices.get(animals.get(indexOfAnimalToSell).animalType);

        //If there are more than 1 player.
        if(Game.players.size() > 1){

            System.out.println("Press 1 to sell to another player");
            System.out.println("Press 2 to sell to the store");
            int userChoiceSellToStoreOrPlayer = IOFunctions.convertStringToInt();

            if(userChoiceSellToStoreOrPlayer < 1 || userChoiceSellToStoreOrPlayer > 2){
                System.out.println();
                userChoiceSellToStoreOrPlayer = IOFunctions.convertStringToInt();
            }

            if(userChoiceSellToStoreOrPlayer == 1){
                System.out.println("Choose which player you want to sell to");

                int counter = 1;
                for(Player players : Game.players){

                    if(!players.getPlayerName().equalsIgnoreCase(Game.players.get(0).getPlayerName())){
                        System.out.println("[" + counter + "] - " + players.getPlayerName());
                        counter++;
                    }

                }
                //Who to sell to.
                int indexOfPlayerToSellTo = IOFunctions.convertStringToInt( 1, Game.players.size());

                areYouSure = IOFunctions.printAndAskIfUserAreSure("Do you both agree of this transaction of " + animals.get(indexOfAnimalToSell).getName()+ "?");
                if(areYouSure) {
                    player.setGold((int) (player.getGold() + sellPrice));

                    //Add and remove animal between owners.
                    Game.players.get(indexOfPlayerToSellTo).animals().add(player.animals.get(indexOfAnimalToSell));
                    player.animals.remove(indexOfAnimalToSell);

                    //Remove gold from other player.
                    Game.players.get(indexOfPlayerToSellTo).setGold((int) (Game.players.get(indexOfPlayerToSellTo).getGold() - sellPrice));
                }

            }else{
                areYouSure = IOFunctions.printAndAskIfUserAreSure("Are you sure you want to sell " + animals.get(indexOfAnimalToSell).getName()+ "?");
                if(areYouSure) {
                    player.setGold((int) (player.getGold() + sellPrice));
                    player.animals.remove(indexOfAnimalToSell);
                }
            }
            return areYouSure;
        }

        if(Game.players.size() == 1){
            areYouSure = IOFunctions.printAndAskIfUserAreSure("Are you sure you want to sell " + animals.get(indexOfAnimalToSell).getName() + "?");
            if(areYouSure) {
                player.setGold((int) (player.getGold() + sellPrice));
                player.animals.remove(indexOfAnimalToSell);
            }
            return areYouSure;
        }
        return false;
    }

//    public static boolean buyAnimal(Player player, boolean playerWantToShopAgain){
//
//        while(true){
//
//            if (playerWantToShopAgain) {
//                StoreHelper.printBuyAnimalMenu("To end your turn");
//            }else {
//                StoreHelper.printBuyAnimalMenu("To go back");
//            }
//
//            int choice = IOFunctions.convertStringToInt();
//            String animalType = " ";
//
//            switch (choice){
//                case 1: //WOLF
//                    animalType = "WOLF";
//                    AnimalHelper.printAboutAnimals(animalType);
//                    if(userWantToBuyFromShop(player, storePrices.get(animalType), animalType)){
//                        StoreHelper.playerWantToShopAgain(player, "ANIMAL");
//                        return true;
//                    }else{
//                        continue;
//                    }
//
//                case 2: //Panda
//                    animalType = "PANDA";
//                    AnimalHelper.printAboutAnimals(animalType);
//                    if(userWantToBuyFromShop(player, storePrices.get(animalType), animalType)){
//                        StoreHelper.playerWantToShopAgain(player, "ANIMAL");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 3: //Bear
//                    animalType = "BEAR";
//                    AnimalHelper.printAboutAnimals(animalType);
//                    if(userWantToBuyFromShop(player, storePrices.get(animalType), animalType)){
//                        StoreHelper.playerWantToShopAgain(player, "ANIMAL");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 4: //Eagle
//                    animalType = "EAGLE";
//                    AnimalHelper.printAboutAnimals(animalType);
//                    if(userWantToBuyFromShop(player, storePrices.get(animalType), animalType)){
//                        StoreHelper.playerWantToShopAgain(player, "ANIMAL");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 5: //Baby Jedi
//                    animalType = "BABY JEDI";
//                    AnimalHelper.printAboutAnimals(animalType);
//                    if(userWantToBuyFromShop(player, storePrices.get(animalType), animalType)){
//                        StoreHelper.playerWantToShopAgain(player, "ANIMAL");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 0:
//                    return false;
//
//                default:
//                    System.out.println("Error from BuyAnimal default");
//            }
//        }
//    }

//    public static boolean buyFood(Player player, boolean playerWantToShopAgain){
//
//        if (playerWantToShopAgain) {
//            StoreHelper.printFoodMenu("To end your turn");
//        }else {
//            StoreHelper.printFoodMenu("To go back");
//        }
//
//        while(true){
//
//            int choice = IOFunctions.convertStringToInt();
//
//            switch (choice){
//                case 1: //FISH
//                    if(userWantToBuyFromShop(player, storePrices.get("FISH"), "FISH")){
//                        StoreHelper.playerWantToShopAgain(player, "FOOD");
//                        return true;
//                    }
//                    else{
//                        continue;
//                    }
//                case 2: //MEAT
//                    if(userWantToBuyFromShop(player, storePrices.get("MEAT"), "MEAT")){
//                        StoreHelper.playerWantToShopAgain(player, "FOOD");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 3: //SALAD
//                    if(userWantToBuyFromShop(player, storePrices.get("SALAD"), "SALAD")){
//                        StoreHelper.playerWantToShopAgain(player, "FOOD");
//                        return true;
//                    }else{
//                        continue;
//                    }
//                case 0: //Go back
//                    return false;
//                default:
//                    System.out.println(IOFunctions.wrongInput);
//            }
//        }
//    }

//    private static boolean userWantToBuyFromShop(Player player, int price, String whatToBuy){
//
//
//        if(StoreHelper.askEnoughWithGold (player.getGold(),  price)){
//
//            player.setGold (player.getGold() - (price));
//
//            switch (whatToBuy.toUpperCase()){
//                case "WOLF", "BEAR", "PANDA", "EAGLE", "BABY JEDI" :
//                    createAnimalToPlayersAnimalList(player, whatToBuy, false);
//                    return true;
//                case "FISH":
//                    player.setStackOfKiloFish(player.getStackOfKiloFish());
//                    return true;
//                case "MEAT":
//                    player.setStackOfKiloFish(player.getStackOfKiloMeat());
//                    return true;
//                case "SALAD":
//                    player.setStackOfKiloFish(player.getStackOfKiloSalad());
//                    return true;
//                default:
//                    System.out.println("Something went wrong in buyProcess switch");
//            }
//        }
//        return false;
//    }

//    public static void createAnimalToPlayersAnimalList(Player player, String animalType, boolean createAnimalWithRandomGender){
//
//
//
//        switch (animalType){
//
//            case "WOLF":
//                player.animals().add(new Wolf(gender, name));
//                break;
//
//            case "PANDA":
//                player.animals().add(new Panda(gender, name));
//                break;
//
//            case "BEAR":
//                player.animals().add(new Bear(gender, name));
//                break;
//
//            case "EAGLE":
//                player.animals().add(new Eagle(gender, name));
//                break;
//
//            case "BABY JEDI":
//                player.animals().add(new BabyJedi(gender, name));
//                break;
//
//            default:
//                System.out.println("Error in createAnimalToPlayersAnimalList function");
//        }
//
//    }



//    private static void playSoundEffectOfAnimal(String filePath) {
//        Audio audio = new Audio();
//        audio.playMusic(filePath);
//    }

    //<editor-fold desc="Getters & Setters">
//
//    public static int getMeatPricePerKilo() {
//        return meatPricePerKilo;
//    }
//    public static void setMeatPricePerKilo(int meatPricePerKilo) {
//        Store.meatPricePerKilo = meatPricePerKilo;
//    }
//    public static int getFishPricePerKilo() {
//        return fishPricePerKilo;
//    }
//    public static void setFishPricePerKilo(int fishPricePerKilo) {
//        Store.fishPricePerKilo = fishPricePerKilo;
//    }
//    public static int getSaladPricePerKilo() {
//        return saladPricePerKilo;
//    }
//    public static void setSaladPricePerKilo(int saladPricePerKilo) {
//        Store.saladPricePerKilo = saladPricePerKilo;
//    }
    //</editor-fold>

}

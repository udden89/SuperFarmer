package Store;

import Animals.Animal;
import Animals.AnimalCAM;
import Animals.Wolf;
import Game.Game;
import Game.IOFunctions;
import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {

    //Animal prices: (Better for these to be in animal class?)
    private static int wolfPrice = 1000;
    private static int pandaPrice = 2500;
    private static int bearPrice = 1500;
    private static int eaglePrice = 1000;
    private static int babyJediPrice = 3000;

    public static HashMap<String, Integer> animalPrices = new HashMap<>();

    public Store(){
        animalPrices.put("WOLF", 1000);
        animalPrices.put("PANDA", 2500);
        animalPrices.put("BEAR", 1500);
        animalPrices.put("EAGLE", 1000);
        animalPrices.put("BABY JEDI", 3000);
    }

    //Food prices:
    private static int meatPricePerKilo = 69;
    private static int fishPricePerKilo = 129;
    private static int saladPricePerKilo = 29;

    public static boolean buyAnimal(Player player){


        while(true){

            StoreCAM.printBuyAnimalMenu();

            int choice = IOFunctions.convertStringToInt();
            String animalType = " ";

            switch (choice){
                case 1: //WOLF
                    animalType = "WOLF";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, animalPrices.get(animalType), animalType)){
                        return true;
                    }else{
                        continue;
                    }

                case 2: //Panda
                    animalType = "PANDA";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, animalPrices.get(animalType), animalType)){
                        return true;
                    }else{
                        continue;
                    }
                case 3: //Bear
                    animalType = "BEAR";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, animalPrices.get(animalType), animalType)){
                        return true;
                    }else{
                        continue;
                    }
                case 4: //Eagle
                    animalType = "EAGLE";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, animalPrices.get(animalType), animalType)){
                        return true;
                    }else{
                        continue;
                    }
                case 5: //Baby Jedi
                    animalType = "BABY JEDI";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, animalPrices.get(animalType), animalType)){
                        return true;
                    }else{
                        continue;
                    }
                case 0:
                return false;

                default:
                    System.out.println("Error from BuyAnimal default");
            }
        }
    }

    public static boolean buyFood(Player player){

        while(true){

            StoreCAM.printFoodMenu();

            int choice = IOFunctions.convertStringToInt();

            switch (choice){
                case 1: //FISH
                    if(buyProcess(player, fishPricePerKilo, "FISH")){
                        return true;
                    }
                    else{
                        continue;
                    }
                case 2: //MEAT
                    if(buyProcess(player, meatPricePerKilo, "MEAT")){
                        return true;
                    }else{
                        continue;
                    }
                case 3: //SALAD
                    if(buyProcess(player, saladPricePerKilo, "SALAD")){
                        return true;
                    }else{
                        continue;
                    }
                case 0: //Go back
                    return false;
                default:
                    System.out.println(IOFunctions.wrongInput);
            }
        }
    }

    private static boolean buyProcess(Player player, int price, String whatToBuy){

        //Prints a question about quantity
        System.out.println(StoreCAM.askQuantity);
        int quantity = IOFunctions.convertStringToInt();

        if(StoreCAM.askEnoughWithGold (player.getGold(), quantity * price)){

            player.setGold (player.getGold() - (quantity * price));

            switch (whatToBuy.toUpperCase()){
                case "WOLF", "BEAR", "PANDA", "EAGLE", "BABY JEDI" :
                    createAnimalToPlayersAnimalList(player, quantity, whatToBuy, false);
                    return true;
                case "FISH":
                    player.setStackOfKiloFish(player.getStackOfKiloFish() + quantity);
                    return true;
                case "MEAT":
                    player.setStackOfKiloFish(player.getStackOfKiloMeat() + quantity);
                    return true;
                case "SALAD":
                    player.setStackOfKiloFish(player.getStackOfKiloSalad() + quantity);
                    return true;
                default:
                    System.out.println("Something went wrong in buyProcess switch");
            }
        }
        return false;
    }

    //TODO Shorten this method!
    public static boolean sellAnimal(Player player, ArrayList<Animal> animals){

        System.out.println(IOFunctions.line);
        System.out.println("Selling animal ");
        System.out.println(IOFunctions.line + "\n");


        int number = 1;
        for (Animal animal : animals) {

            double sellPrice = ((double) animal.getHealth()/100)*animalPrices.get(animal.animalType);

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
        double sellPrice = ((double)animals.get(indexOfAnimalToSell).getHealth()/100)*animalPrices.get(animals.get(indexOfAnimalToSell).animalType);

        if(Game.players.size() > 1){
            System.out.println("Press 1 to sell to another player");
            System.out.println("Press 2 to sell to the store");
            int userChoiceSellToStoreOrPlayer = IOFunctions.convertStringToInt();

            if(userChoiceSellToStoreOrPlayer == 1){
                System.out.println("Choose which player you want to sell to");

                int counter = 1;
                for(Player players : Game.players){

                    if(!players.getPlayerName().equalsIgnoreCase(Game.players.get(0).getPlayerName())){
                        System.out.println("[" + counter + "] - " + players.getPlayerName());
                        counter++;
                    }

                }
                int indexOfPlayerToSellTo = IOFunctions.convertStringToInt();

                if(IOFunctions.areYouSure("You want to sell your " + animals.get(indexOfAnimalToSell).getName()
                        + " to " + Game.players.get(indexOfPlayerToSellTo).getPlayerName() + "?")) {

                    player.setGold((int) (player.getGold() + sellPrice));

                    //Add and remove animal between owners.
                    Game.players.get(indexOfPlayerToSellTo).animals().add(player.animals.get(indexOfAnimalToSell));
                    player.animals.remove(indexOfAnimalToSell);

                    //Remove gold from other player.
                    Game.players.get(indexOfPlayerToSellTo).setGold((int) (Game.players.get(indexOfPlayerToSellTo).getGold() - sellPrice));
                }
            }

        }else{
            if(IOFunctions.areYouSure("Sell your animal " + animals.get(indexOfAnimalToSell-1))){
                player.setGold((int) (player.getGold() + sellPrice));
                player.animals.remove(indexOfAnimalToSell-1);
                return true;
            }
        }

        return true;
    }

    //TODO Change "new Wolf" when having all animals classes ready (ENUMS!!!!!!!!?????????????????????)
    public static void createAnimalToPlayersAnimalList(Player player, int quantity, String animalType, boolean randomGender){

        for(int i = 0; i < quantity; i++){


            if(animalType.equalsIgnoreCase("WOLF")){
                player.animals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("PANDA")){
                player.animals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("BEAR")){
                player.animals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("EAGLE")){
                player.animals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("BABY JEDI")){
                player.animals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
        }
    }

    //<editor-fold desc="Getters & Setters">

    public static int getWolfPrice() {
        return wolfPrice;
    }
    public static void setWolfPrice(int wolfPrice) {
        Store.wolfPrice = wolfPrice;
    }
    public static int getPandaPrice() {
        return pandaPrice;
    }
    public static void setPandaPrice(int pandaPrice) {
        Store.pandaPrice = pandaPrice;
    }
    public static int getBearPrice() {
        return bearPrice;
    }
    public static void setBearPrice(int bearPrice) {
        Store.bearPrice = bearPrice;
    }
    public static int getEaglePrice() {
        return eaglePrice;
    }
    public static void setEaglePrice(int eaglePrice) {
        Store.eaglePrice = eaglePrice;
    }
    public static int getBabyJediPrice() {
        return babyJediPrice;
    }
    public static void setBabyJediPrice(int babyJediPrice) {
        Store.babyJediPrice = babyJediPrice;
    }
    public static int getMeatPricePerKilo() {
        return meatPricePerKilo;
    }
    public static void setMeatPricePerKilo(int meatPricePerKilo) {
        Store.meatPricePerKilo = meatPricePerKilo;
    }
    public static int getFishPricePerKilo() {
        return fishPricePerKilo;
    }
    public static void setFishPricePerKilo(int fishPricePerKilo) {
        Store.fishPricePerKilo = fishPricePerKilo;
    }
    public static int getSaladPricePerKilo() {
        return saladPricePerKilo;
    }
    public static void setSaladPricePerKilo(int saladPricePerKilo) {
        Store.saladPricePerKilo = saladPricePerKilo;
    }
    //</editor-fold>

}

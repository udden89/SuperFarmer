package Store;

import Animals.AnimalCAM;
import Animals.Wolf;
import Game.InputAndOutputFunctions;
import Player.Player;

public class Store {

    //Animal prices:
    private static int wolfPrice = 1000;
    private static int pandaPrice = 2500;
    private static int bearPrice = 1500;
    private static int eaglePrice = 1000;
    private static int babyJediPrice = 3000;

    //Food prices:
    private static int meatPricePerKilo = 200;
    private static int fishPricePerKilo = 220;
    private static int saladPricePerKilo = 150;

    public static boolean buyAnimal(Player player){

        while(true){

            StoreCAM.printBuyAnimalMenu();

            int choice = InputAndOutputFunctions.convertStringToInt();

            switch (choice){
                case 1: //WOLF

                    String animalType = "WOLF";

                    AnimalCAM.printAnimalInfo(animalType);
                    buyProcess(player, wolfPrice, animalType);

                    /*Prints a question about quantity
                    System.out.println(StoreCAM.askQuantity);
                    int quantity = InputAndOutputFunctions.convertStringToInt();

                    //Checks if enough with gold, and if so, create new animal and reduce gold.
                    if(StoreCAM.askEnoughWithGold (player.getGold(), quantity * wolfPrice)){

                        player.setGold (player.getGold() - (quantity * wolfPrice));
                        createAnimalToPlayersAnimalList(player,quantity,animalType);
                        System.out.println("Returned true in BuyAnimal 1");
                        return true;
                    }
                    break;

                     */

                case 9:
                return false;

                default:
                    System.out.println("Printed from BuyAnimal default");

            }
        }

    }

    public static boolean buyFood(Player player){

        while(true){

            StoreCAM.printFoodMenu();

            int choice = InputAndOutputFunctions.convertStringToInt();

            switch (choice){
                case 1: //FISH
                    if(buyProcess(player,fishPricePerKilo, "FISH")){
                        return true;
                    }
                case 2: //MEAT
                    if(buyProcess(player,meatPricePerKilo, "MEAT")){
                        return true;
                    }
                case 3: //SALAD
                    if(buyProcess(player,saladPricePerKilo, "SALAD")){
                        return true;
                    }
                case 9:
                    return false;

                default:
                    System.out.println(InputAndOutputFunctions.wrongInput);
            }
        }
    }

    private static boolean buyProcess(Player player, int price, String whatToBuy){

        //Prints a question about quantity
        System.out.println(StoreCAM.askQuantity);
        int quantity = InputAndOutputFunctions.convertStringToInt();

        //Checks if enough with gold, and if so, create new animal and reduce gold.
        if(StoreCAM.askEnoughWithGold (player.getGold(), quantity * price)){

            player.setGold (player.getGold() - (quantity * price));

            switch (whatToBuy.toUpperCase()){

                case "WOLF", "BEAR", "PANDA", "EAGLE", "BABY JEDI" :
                    createAnimalToPlayersAnimalList(player, quantity, whatToBuy);
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

    public static void createAnimalToPlayersAnimalList(Player player, int quantity, String animalType){

        for(int i = 0; i < quantity; i++){

            if(animalType.equals("WOLF")){
                player.getAnimalList().add(new Wolf(AnimalCAM.inputNameOfNewAnimal(animalType), AnimalCAM.inputGenderOfNewAnimal(animalType)));
            }
            else if(animalType.equals("PANDA")){
                player.getAnimalList().add(new Wolf(AnimalCAM.inputNameOfNewAnimal(animalType), AnimalCAM.inputGenderOfNewAnimal(animalType)));
            }
            else if(animalType.equals("BEAR")){
                player.getAnimalList().add(new Wolf(AnimalCAM.inputNameOfNewAnimal(animalType), AnimalCAM.inputGenderOfNewAnimal(animalType)));
            }
            else if(animalType.equals("EAGLE")){
                player.getAnimalList().add(new Wolf(AnimalCAM.inputNameOfNewAnimal(animalType), AnimalCAM.inputGenderOfNewAnimal(animalType)));
            }
            else if(animalType.equals("BABY JEDI")){
                player.getAnimalList().add(new Wolf(AnimalCAM.inputNameOfNewAnimal(animalType), AnimalCAM.inputGenderOfNewAnimal(animalType)));
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

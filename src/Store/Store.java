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

        StoreCAM.printBuyAnimalMenu();

        int choice = InputAndOutputFunctions.convertStringToInt();

        switch (choice){
            case 1:

                String animalType = "WOLF";

                AnimalCAM.printAnimalInfo(animalType);

                System.out.println(StoreCAM.askQuantity);
                int quantity = InputAndOutputFunctions.convertStringToInt();

                if(StoreCAM.askEnoughWithGold (player.getGold(), (quantity * getWolfPrice()))){

                    createAnimalsToPlayersAnimalList(player,quantity,animalType);

                }


                return true;

            default:
                return false;

        }
    }

    public static void createAnimalsToPlayersAnimalList(Player player, int quantity, String animalType){

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

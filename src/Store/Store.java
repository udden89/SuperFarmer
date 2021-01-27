package Store;

import Animals.Animal;
import Animals.AnimalCAM;
import Animals.Wolf;
import Game.IOFunctions;
import Player.Player;

public class Store {

    //Animal prices:
    private static int wolfPrice = 1000;
    private static int pandaPrice = 2500;
    private static int bearPrice = 1500;
    private static int eaglePrice = 1000;
    private static int babyJediPrice = 3000;

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
                    if(buyProcess(player, wolfPrice, animalType)){
                        return true;
                    }

                case 2: //Panda
                    animalType = "PANDA";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, pandaPrice, animalType)){
                        return true;
                    }
                case 3: //Bear
                    animalType = "BEAR";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, bearPrice, animalType)){
                        return true;
                    }
                case 4: //Eagle
                    animalType = "EAGLE";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, eaglePrice, animalType)){
                        return true;
                    }
                case 5: //Baby Jedi
                    animalType = "BABY JEDI";
                    AnimalCAM.printAboutAnimals(animalType);
                    if(buyProcess(player, babyJediPrice, animalType)){
                        return true;
                    }
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

            int choice = IOFunctions.convertStringToInt();

            switch (choice){
                case 1: //FISH
                    if(buyProcess(player, fishPricePerKilo, "FISH")){
                        return true;
                    }
                case 2: //MEAT
                    if(buyProcess(player, meatPricePerKilo, "MEAT")){
                        return true;
                    }
                case 3: //SALAD
                    if(buyProcess(player, saladPricePerKilo, "SALAD")){
                        return true;
                    }
                case 9: //Go back
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

    public static void createAnimalToPlayersAnimalList(Player player, int quantity, String animalType, boolean randomGender){

        for(int i = 0; i < quantity; i++){

           // if(animalType.equalsIgnoreCase(Animal.typeOfAnimal[i].)){
             //   player.getAnimals().add(new );
            //}

            if(animalType.equalsIgnoreCase("WOLF")){
                player.getAnimals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("PANDA")){
                player.getAnimals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("BEAR")){
                player.getAnimals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("EAGLE")){
                player.getAnimals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
            }
            else if(animalType.equalsIgnoreCase("BABY JEDI")){
                player.getAnimals().add(new Wolf(AnimalCAM.genderOfNewAnimal(animalType, randomGender), AnimalCAM.inputNameOfNewAnimal(animalType)));
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

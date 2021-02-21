package game;

import animals.Animal;
import player.*;
import store.Store;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//CAM Stands for calculations and menus
public class GameHelper extends Game implements Serializable {


    public static void printMainMenu(Player player){

        String star = "¤";

        System.out.println(star.repeat(50)+"\n");
        IOFunctions.printSomethingWithThreadSleep( "\t" + player.getPlayerName(), 50);
        System.out.println("");
        System.out.println("\t" + "Round: " + gameRounds);
        System.out.println("\tGold: "+ player.getGold() + " \t\t\tAnimals owned: " + player.animals.size());
        System.out.println("\tSick animals: "+player.getSickAnimals() + "\t\tTotal food in kg: " + PlayerHelper.totalKgFood(player) + "\n");
        System.out.println(star.repeat(50));
        System.out.println(
                "\nWhat do you want to do?\n\n" +
                        "[1] - Buy animals\n" +
                        "[2] - Buy food\n" +
                        "[3] - Feed animals\n" +
                        "[4] - Breed animals\n" +
                        "[5] - Sell animals\n" +
                        "[6] - To see veterinary\n" +
                        "[7] - To see your farm\n\n" +
                        "[9] - To save your game\n" +
                        "[0] - Skip turn\n");

    }

    public static void printRulesOfTheGame(){

        System.out.println("The rules are: \n" +
                "Buy animals, breed them and sell them. \n" +
                "The player with most money at the end wins the game. \n" +
                "You can only do one kind of action per turn");

    }


    public static void runEndgameProcess(){

        sellAllAnimals();
        setAWinner();
        printWinners();

    }

    private static void sellAllAnimals(){

        for(Player player : players){

            ArrayList<Animal> animals = player.animals;

            //Sells all animals and add gold to player.
            for(int i = 0; i < animals.size(); i++){
                Animal animal = animals.get(i);
                int value = (int)((double)animals.get(i).getHealth()/100)*Store.storePrices.get(animal.animalType);
                player.setGold(player.getGold() + value);
                animals.remove(animal);
                i--;

            }
        }
    }

    //TODO DOUBLE CHECK THIS LOOP LOGIC!!
    private static void setAWinner(){

        int length = players.size();

        //Sorts the player with most gold using bubble sorting
        for(int i = 0; i < length-1; i++ ){

            for(int j = 0; j < length-i-1; j++){

                if(players.get(j).getGold() < players.get(j+1).getGold()){
                    Player tempPlayer = players.get(j);
                    players.set(j, players.get(j+1));
                    players.set(j+1, tempPlayer);
                }

            }

        }

    }

    private static void printWinners(){
        int number = 1;
        IOFunctions.printLine();
        IOFunctions.printLine();
        IOFunctions.printLine();
        System.out.println("The winner are:");
        for(int i = 0; i < players.size(); i++){
            System.out.println("[" + number + "] - " + players.get(i).getPlayerName() + "!!! Total gold: " + players.get(i).getGold());
            number++;
        }
        IOFunctions.printLine();
        IOFunctions.printLine();
        IOFunctions.printLine();
    }

    public static boolean saveGame(){

        WhatToSave whatToSave = new WhatToSave();

        File file = new File("Saved games/");
        System.out.println("Enter name on saved game:");
        String savedGame = IOFunctions.inputString() + ".ser";

        if(!Files.exists(Paths.get("Saved games/" + savedGame))){
            if(!file.exists()){
                file.mkdir();
            }
            Serializer.serialize("Saved games/" + savedGame, whatToSave);
        }else {
            System.out.println("Already a saved game with that name.");
            if(IOFunctions.printAndAskIfUserAreSure("Do you want to overwrite the existing saved game?")){
                Serializer.serialize("Saved games/" + savedGame, whatToSave );
            }

        }
        return true;

    }

    public static void loadGame(){

        try{
            File[] savedGames = new File("Saved games").listFiles();

            printAllSavedGames(savedGames);

            if(savedGames.length != 0){
                String fileName = "Saved games/" + chooseSavedGame(savedGames);


                WhatToSave whatToSave = (WhatToSave) Serializer.deserialize(fileName);
                whatToSave.loadGame();
                Game.gameLoop();
            }else {
                System.out.println("Sorry, no saved game files!");
                System.out.println("So here is a new fresh game:");
            }


        }catch (Exception error){
            System.out.println(error);
            System.out.println("Error in loading your game");
        }

    }

    private static void printAllSavedGames(File[] savedGames){

        int number = 1;
        for(File gameFile : savedGames){
            System.out.println("[" + number+ "] - "+  gameFile.getName().replace(".ser", ""));
            number++;
        }

    }

    private static String chooseSavedGame(File[] savedGames){

        ArrayList<String> tempListOFSavedGames = new ArrayList<>();

        for(File gameFile : savedGames){
            tempListOFSavedGames.add(gameFile.getName());
        }

        System.out.println("Which game do you want to load?");
        int choice = IOFunctions.convertStringToInt(1,tempListOFSavedGames.size());
        return tempListOFSavedGames.get(choice-1);

    }

}

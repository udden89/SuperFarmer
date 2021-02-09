package Game;

import Animals.Animal;
import Player.*;
import Store.Store;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//CAM Stands for calculations and menus
public class GameCAM extends Game implements Serializable {

    public static void printMainMenu(Player player){

        String star = "¤";
        String mid = "                        ";

        System.out.println(star.repeat(50)+"\n");
        System.out.println(mid + player.getPlayerName() +"\n");
        System.out.println("\t" + "Round: " + gameRounds);
        System.out.println("\tGold: "+ player.getGold() + " \t\t\tAnimals owned: " + player.animals.size());
        System.out.println("\tSick animals: "+player.getSickAnimals() + "\t\tTotal food in kg: " + PlayerCAM.totalKgFood(player) + "\n");
        System.out.println(star.repeat(50));
        System.out.println(
                "What do you want to do?\n" +
                        "[1] - Buy animals\n" +
                        "[2] - Buy food\n" +
                        "[3] - Feed animals\n" +
                        "[4] - Breed animals\n" +
                        "[5] - Sell animals\n" +
                        "[6] - To see veterinary\n" +
                        "[7] - To see your farm\n");

    }

    public static void winner(){

        for(Player player : players){

            ArrayList<Animal> animals = player.animals;

            //Sells all animals and add gold to player.
            for(int i = 0; i < animals.size(); i++){
                Animal animal = animals.get(i);
                int value = (int)((double)animals.get(i).getHealth()/100)*Store.animalPrices.get(animal.animalType);
                player.setGold(player.getGold() + value);
                animals.remove(animal);
                i--;

            }
        }

        //Sorts the players by their gold and puts the players in a descending winner list.
        for(int i = players.size()-1; i > 0; i--){
            if(players.get(i).getGold() > players.get(0).getGold()){
                Player tempPlayer = players.get(i);
                players.remove(i);
                players.add(0,tempPlayer);
            }
        }

        for(int i = 0; i < players.size(); i++){
            int number = 1;
            System.out.println("The winner are:");
            System.out.println("[" + number + "] - " + players.get(i).getPlayerName() + "!!! Total gold: " + players.get(i).getGold());
        }

    }



}

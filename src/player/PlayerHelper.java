package player;

import animals.Animal;
import game.Game;
import game.IOFunctions;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class PlayerHelper {

    public static void printPlayerInfo(Player player){
        IOFunctions.printLine();
        System.out.println(player.getPlayerName() + ", you have in your farm: \n");
        System.out.println(player.getGold() + " gold");
        printAPlayersAnimals(player.animals());
    }

    public static int totalKgFood(Player player){
        return player.getStackOfKiloFish() + player.getStackOfKiloMeat() + player.getStackOfKiloSalad();
    }

    public static void printAPlayersAnimals(ArrayList<Animal> animals){

        IOFunctions.printLine();
        System.out.println("ANIMALS: ");

        for(Animal animal : animals){
            System.out.print("\n" + animal.getClass().getSimpleName() + ": " + animal.getName());
            if(animal.isSick) {
                System.out.println(" (needs to see veterinary)");
            }else{
                System.out.println("\t");
            }
            System.out.println("Gender: "  + animal.getGender());
            System.out.println("Age: " + animal.getAge());
            System.out.println("Health: " + animal.getHealth());
            System.out.print("Eats: ");
            if(animal.isEatsFish()){
                System.out.print("<FISH> ");
            }
            if(animal.isEatsMeat()){
                System.out.print("<MEAT> ");
            }
            if(animal.isEatsSalad()) {
                System.out.print("<SALAD> ");
            }
            System.out.println(" ");
        }
    }

    public static void checkIfPlayerLost(){

        int x = Game.players.size();

        for(int i = x-1; i >= 0; i--){
            if(Game.players.get(i).getGold() == 0 && Game.players.get(i).animals().size() < 1){
                System.out.println(Game.players.get(i).getPlayerName() + " has been eliminated and lost the game.");
                Game.players.remove(i);
            }
        }

    }

}

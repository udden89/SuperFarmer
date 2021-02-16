package player;

import animals.Animal;
import game.Game;
import game.IOFunctions;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class PlayerHelper {

    public static void printPlayerInfo(Player player){
        System.out.println(IOFunctions.line);
        System.out.println(player.getPlayerName() + ", you have in your farm: \n");
        System.out.println(player.getGold() + " gold");
        printAPlayersAnimals(player.animals());


    }

    public static int totalKgFood(Player player){
        return player.getStackOfKiloFish() + player.getStackOfKiloMeat() + player.getStackOfKiloSalad();
    }

    public static void printAPlayersAnimals(ArrayList<Animal> animals){

        System.out.println(IOFunctions.line);
        System.out.println("ANIMALS: ");

        for(Animal animal : animals){
            System.out.print("\n" + animal.getClass().getSimpleName() + ": \t\t" + animal.getName());
            if(animal.isSick) {
                System.out.println(" (needs to see veterinary)");
            }else{
                System.out.println("\t");
            }
            System.out.println("Gender: \t"  + animal.getGender());
            System.out.println("Age: \t\t" + animal.getAge());
            System.out.println("Health: \t" + animal.getHealth());
            System.out.print("Eats: \t\t");
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

        for(int i = 0; i < x; i++){
            if(Game.players.get(i).getGold() == 0 && Game.players.get(i).animals().size() < 1){
                System.out.println(Game.players.get(i).getPlayerName() + " has been eliminated and lost the game.");
                Game.players.remove(i);
                x--;
            }
        }

    }

}

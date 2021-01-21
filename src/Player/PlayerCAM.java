package Player;

import Animals.Animal;
import Game.InputAndOutputFunctions;

import java.util.ArrayList;

//CAM Stands for calculations and menus
public class PlayerCAM {

    public static void printPlayerInfo(Player player){
        System.out.println(InputAndOutputFunctions.line);
        System.out.println(player.getPlayerName() + ", you have in your farm: \n");
        System.out.println(player.getGold() + " gold");
        printAPlayersAnimals(player.getAnimalList());

        //Print food as well.

    }

    public static void printAPlayersAnimals(ArrayList<Animal> animals){

        System.out.println(InputAndOutputFunctions.line);
        System.out.println("ANIMALS: ");

        for(Animal animal : animals){
            System.out.println("\n" + animal.getClass().getSimpleName() + ": \t\t" + animal.getName());
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

}

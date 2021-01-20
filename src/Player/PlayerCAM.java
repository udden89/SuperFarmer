package Player;

import Game.InputAndOutputFunctions;

//CAM Stands for calculations and menus
public class PlayerCAM {

    public static void printPlayerInfo(Player player){
        System.out.println(InputAndOutputFunctions.line);
        System.out.println(player.getPlayerName() + ", you have in your farm: \n");
        System.out.println(player.getGold() + " gold");
        //Food.printAPlayersFood(foodList);
        //Animal.printAPlayersAnimals(animalList);

    }

}

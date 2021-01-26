package Game;

import Player.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

//CAM Stands for calculations and menus
public class GameCAM extends Game implements Serializable {

    public static void printMainMenu(Player player){

        System.out.println(IOFunctions.line);
        System.out.println(player.getPlayerName() + ", your turn:");
        System.out.println("Turns left: " + Game.gameRounds);
        System.out.println(IOFunctions.line);
        System.out.println(
                "What do you want to do?\n" +
                        "Press 1 - Buy animals\n" +
                        "Press 2 - Buy food\n" +
                        "Press 3 - Feed animals\n" +
                        "Press 4 - Breed animals\n" +
                        "Press 5 - Sell animals\n" +
                        "Press 6 - To see your farm\n");

    }






}

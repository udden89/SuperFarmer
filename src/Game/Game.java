package Game;
import Animals.AnimalCAM;
import Animals.Breeding;
import Player.*;
import Store.Store;

import java.util.ArrayList;

public class Game {

    // All the players of the game
    public static ArrayList<Player> players = new ArrayList<>();

    //Start variables
    public static int gameRounds;
    protected static int howManyPlayers;

    private boolean skipIfSaved = false;

    public Game() {
        settingUpTheGame();
        Store store = new Store();
        gameLoop();
    }


    private void settingUpTheGame() {

        if(!skipIfSaved){

            System.out.println(IOFunctions.line);
            System.out.println("Welcome to Farm of the year game!");


            //The rules of the game
            System.out.println(IOFunctions.line);
            System.out.println("\nThe rules are: \n" +
                    "Buy animals, breed them and sell them. \n" +
                    "The player with most money at the end wins the game");


            //Decides how many players.
            System.out.println(IOFunctions.line);
            System.out.println("\nHow many players (1-4)?");
            while (howManyPlayers < 1 || howManyPlayers > 4) {
                howManyPlayers = IOFunctions.convertStringToInt();
                if (howManyPlayers < 1 || howManyPlayers > 4) {
                    System.out.println("Please enter a player count between 1-4");
                }
                System.out.println(IOFunctions.line);
                System.out.println("You have chosen: " + howManyPlayers + " players");
            }


            //Sets the player names
            for (int i = 1; i <= howManyPlayers; i++) {
                System.out.println("Enter a name for player " + i + ": ");
                String playerName = IOFunctions.inputString();
                Player player = new Player(playerName);
                players.add(player);
                //players.add(Player.createNewPlayer(playerName));                          //
            }


            //Decides how many rounds in game.
            System.out.println(IOFunctions.line);
            System.out.println("\nHow many rounds do you want to play (5-30)?");
            while (gameRounds < 5 || gameRounds > 30) {
                gameRounds = IOFunctions.convertStringToInt();
                if (gameRounds < 5 || gameRounds > 30) {
                    System.out.println("Please enter rounds between 5-30");
                }
            }


            //Printing all the players and the start money
            System.out.println(IOFunctions.line);
            System.out.print("You have chosen: " + gameRounds + " rounds");
            System.out.println("\nAnd our players are: ");
            for (Player player : players) {
                System.out.println(player.getPlayerName() + " with " + player.getGold() + " gold");
            }

            System.out.println(IOFunctions.line);
            System.out.println("GOOD LUCK!!!!");

        }
    }

    private void gameLoop(){

        while(gameRounds != 0){

            for(int i = gameRounds; i > 0 ; i--){

                for(Player player : players){
                    if(actionOfPlayer(player)){

                        AnimalCAM.decreaseAnimalHealthAndAgePerRound(player, player.getAnimals());

                    }
                }
                gameRounds--;
            }
        }
    }

    public boolean actionOfPlayer(Player player){

        while(true){

            GameCAM.printMainMenu(player);
            int action = IOFunctions.convertStringToInt();

            switch (action){

                case 1:
                    if(Store.buyAnimal(player)){
                        return true;
                    }
                    break;

                case 2:
                    if(Store.buyFood(player)){
                        return true;
                    }
                    break;

                case 3:
                    if(Feeding.feedAnimalMenu(player, player.getAnimals())){
                        return true;
                    }
                    break;

                case 4:
                    if(Breeding.breedAnimalMenu(player, player.getAnimals())){
                        return true;
                    }
                    break;

                case 5:
                    if(Store.sellAnimal(player, player.getAnimals())){
                        return true;
                    }
                    break;

                case 6:
                    PlayerCAM.printPlayerInfo(player);
                    //InputAndOutputFunctions.pressEnterToContinue();
                    break;

                default:
                    System.out.println("Printed from actionOfPlayer default");

            }
        }
    }
}

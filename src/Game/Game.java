package Game;
import Animals.Animal;
import Animals.AnimalHelper;
import Animals.Breeding;
import Player.*;
import Store.Store;
import Store.Veterinary;

import java.util.ArrayList;

import static Game.IOFunctions.printSomethingWithThreadSleep;

public class Game {

    // All the players of the game
    public static ArrayList<Player> players = new ArrayList<>();

    //Start variables
    public static int gameRounds;
    protected static int howManyPlayers;

    private boolean skipIfSaved = false;

    public Game() {
        settingUpTheGame();
        Store.addAllItemsToStore();
        Animal.addAllAnimalTypes();
        gameLoop();
    }


    private void settingUpTheGame() {

        if(!skipIfSaved){

            IOFunctions.printLine();
            System.out.println("Welcome to Farm of the year game!");


            //The rules of the game
            IOFunctions.printLine();
            System.out.println("The rules are: \n" +
                    "Buy animals, breed them and sell them. \n" +
                    "The player with most money at the end wins the game");


            //Decides how many players.
            IOFunctions.printLine();
            System.out.println("How many players (1-4)?");
            while (howManyPlayers < 1 || howManyPlayers > 4) {
                howManyPlayers = IOFunctions.convertStringToInt();
                if (howManyPlayers < 1 || howManyPlayers > 4) {
                    System.out.println("Please enter a player count between 1-4");
                }
                IOFunctions.printLine();
                System.out.println("You have chosen: " + howManyPlayers + " players");
            }


            //Sets the player names
            for (int i = 1; i <= howManyPlayers; i++) {
                System.out.println("Enter a name for player " + i + ": ");
                String playerName = RandomGameMode.randomName(); //TODO remove this when not debugging and activate the line below
                //String playerName = IOFunctions.inputString();
                Player player = new Player(playerName);
                players.add(player);
                //players.add(Player.createNewPlayer(playerName));                          //
            }


            //Decides how many rounds in game.
            IOFunctions.printLine();
            System.out.println("\nHow many rounds do you want to play (5-30)?");
            while (gameRounds < 5 || gameRounds > 30) {
                //gameRounds = 30;    //TODO remove this when not debugging and activate the line below
                gameRounds = IOFunctions.convertStringToInt();
                if (gameRounds < 5 || gameRounds > 30) {
                    System.out.println("Please enter rounds between 5-30");
                }
            }


            //Printing all the players and the start money
            IOFunctions.printLine();
            System.out.println("You have chosen: " + gameRounds + " rounds");
            System.out.println("And our players are: \n");
            for (Player player : players) {
                System.out.println(player.getPlayerName() + " with " + player.getGold() + " gold");
            }

            IOFunctions.printLine();
            printSomethingWithThreadSleep("G O O D  L U C K !!", 25);
            IOFunctions.printLine();

        }
    }

    private void gameLoop(){

        while(gameRounds != 0){

            for(int i = gameRounds; i > 0 ; i--){

                for(Player player : players){
                    if(actionOfPlayer(player)){

                    }
                }

                AnimalHelper.decreaseAnimalHealthAndAgePerRound();
                PlayerHelper.checkIfPlayerLost();

                gameRounds--;
            }
        }

        GameHelper.runEndgameProcess();

    }

    public static boolean actionOfPlayer(Player player){

        while(true){

            GameHelper.printMainMenu(player);
            int action = IOFunctions.convertStringToInt();

            switch (action){

                case 1:
                    if(Store.startProcessOfBuyingAnimalFromStore(player)){
                        return true;
                    }
                    break;

                case 2:
                    if(Store.startProcessOfBuyingFoodFromStore(player)){
                        return true;
                    }
                    break;

                case 3:
                    if(Feeding.feedAnimalMenu(player, player.animals())){
                        return true;
                    }
                    break;

                case 4:
                    if(Breeding.breedAnimalMenu(player, player.animals())){
                        return true;
                    }
                    break;

                case 5:
                    if(Store.sellAnimal(player, player.animals())){
                        return true;
                    }
                    break;

                case 6:
                    if(Veterinary.cureAnimal(player, player.animals)){
                        return true;
                    }
                    break;

                case 7:
                    PlayerHelper.printPlayerInfo(player);
                    //InputAndOutputFunctions.pressEnterToContinue();
                    break;

                default:


            }
        }
    }
}

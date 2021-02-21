package game;
import animals.Animal;
import animals.AnimalHelper;
import animals.Breeding;
import player.*;
import store.Store;
import store.Veterinary;

import java.io.Serializable;
import java.util.ArrayList;

import static game.IOFunctions.printSomethingWithThreadSleep;

public class Game implements Serializable {


    // All the players of the game
    public static ArrayList<Player> players = new ArrayList<>();

    public static int whoseTurnInIndex = 0;

    //Start variables
    public static int gameRounds;
    protected static int howManyPlayers;


    public Game() {
        settingUpTheGame();
        Store.addAllItemsToStore();
        Animal.addAllAnimalTypes();
        gameLoop();
    }


    private void settingUpTheGame() {

        IOFunctions.printLine();
        System.out.println("Welcome to Farm of the year game!");

        GameHelper.printRulesOfTheGame();

        //Load game
        IOFunctions.printLine();
        boolean choice = IOFunctions.printAndAskIfUserAreSure("Do you want to load a game? ");
        if(choice){
                GameHelper.loadGame();
        }

        //Decides how many players.
        IOFunctions.printLine();
        System.out.println("How many players (1-4)?");
        while (howManyPlayers < 1 || howManyPlayers > 4) {
            howManyPlayers = IOFunctions.convertStringToInt();
            if (howManyPlayers < 1 || howManyPlayers > 4) {
                System.out.println("Please enter a player count between 1-4");
            }

        }

        IOFunctions.printLine();
        System.out.println("You have chosen: " + howManyPlayers + " players");


        //Sets the player names
        for (int i = 1; i <= howManyPlayers; i++) {
            System.out.println("Enter a name for player " + i + ": ");
            //String playerName = RandomGameMode.randomName();
            String playerName = IOFunctions.inputString();
            Player player = new Player(playerName);
            players.add(player);

        }


        //Decides how many rounds in game.
        IOFunctions.printLine();
        System.out.println("\nHow many rounds do you want to play (5-30)?");
        while (gameRounds < 5 || gameRounds > 30) {
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

    public static void gameLoop(){

        while(gameRounds != 0){

            for(int i = gameRounds; i > 0 ; i--){

                for(int j = whoseTurnInIndex; j < players.size(); j++){
                    actionOfPlayer(players.get(j));
                    whoseTurnInIndex++;
                }

                AnimalHelper.decreaseAnimalHealthAndAgePerRound();
                PlayerHelper.checkIfPlayerLost();

                whoseTurnInIndex = 0;
                gameRounds--;
            }
        }

        GameHelper.runEndgameProcess();
        System.exit(0);
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
                    if(Feeding.startProcessOfFeedingAnimal(player, player.animals())){
                        return true;
                    }
                    break;

                case 4:
                    if(Breeding.startProcessOfBreedingTwoAnimals(player)){
                        return true;
                    }
                    break;

                case 5:
                    if(Store.sellAnimal(player)){
                        return true;
                    }
                    break;

                case 6:
                    if(Veterinary.startProcessOfCuringAnimal(player, player.animals)){
                        return true;
                    }
                    break;

                case 7:
                    PlayerHelper.printPlayerInfo(player);
                    break;

                case 9:
                    GameHelper.saveGame();
                    System.exit(0);
                    break;

                case 0:
                    return true;

                default:


            }
        }
    }



}

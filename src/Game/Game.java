package Game;
import Player.*;
import java.util.ArrayList;

public class Game {

    // All the players of the game
    ArrayList<Player> players = new ArrayList<>();


    //Start variables
    public static int gameRounds;
    private int howManyPlayers;

    public Game() {
        settingUpTheGame();
        gameLoop();
    }


    private void settingUpTheGame() {

        System.out.println(InputAndOutputFunctions.line);
        System.out.println("Welcome to Farm of the year game!");


        //The rules of the game
        System.out.println(InputAndOutputFunctions.line);
        System.out.println("\nThe rules are: \n" +
                "Buy animals, breed them and sell them. \n" +
                "The player with most money at the end wins the game");


        //Decides how many players.
        System.out.println(InputAndOutputFunctions.line);
        System.out.println("\nHow many players (1-4)?");
            while (howManyPlayers < 1 || howManyPlayers > 4) {
                howManyPlayers = InputAndOutputFunctions.convertStringToInt();
                if (howManyPlayers < 1 || howManyPlayers > 4) {
                    System.out.println("Please enter a player count between 1-4");
                }
        System.out.println(InputAndOutputFunctions.line);
        System.out.println("You have chosen: " + howManyPlayers + " players");
        }


        //Sets the player names
        for (int i = 1; i <= howManyPlayers; i++) {
            System.out.println("Enter a name for player " + i + ": ");
            String playerName = InputAndOutputFunctions.inputString();
            Player player = new Player(playerName);
            players.add(player);
            //players.add(Player.createNewPlayer(playerName));                          //
        }


        //Decides how many rounds in game.
        System.out.println(InputAndOutputFunctions.line);
        System.out.println("\nHow many rounds do you want to play (5-30)?");
            while (gameRounds < 5 || gameRounds > 30) {
                gameRounds = InputAndOutputFunctions.convertStringToInt();
                if (gameRounds < 5 || gameRounds > 30) {
                    System.out.println("Please enter rounds between 5-30");
                }
        }


        //Printing all the players and the start money
        System.out.println(InputAndOutputFunctions.line);
        System.out.print("You have chosen: " + gameRounds + " rounds");
        System.out.println("\nAnd our players are: ");
            for (Player player : players) {
                System.out.println(player.getPlayerName() + " with " + player.getGold() + " gold");
            }


        System.out.println(InputAndOutputFunctions.line);
        System.out.println("GOOD LUCK!!!!");


    }

    private void gameLoop(){

        int movesBeforeNextRound = 0;

        while(gameRounds != 0){

            for(Player player : players){
                GameCAM.printMainMenu(player);
                actionOfPlayer(player);

                //New round
                movesBeforeNextRound ++;
                if(movesBeforeNextRound == howManyPlayers){

                    //animal.decreaseAnimalHealthAndAgePerRound(player, player.getAnimalList());

                    gameRounds--;
                    movesBeforeNextRound = 0;


                }

            }
        }
    }

    public boolean actionOfPlayer(Player player){

        int action = InputAndOutputFunctions.convertStringToInt();

        while(true){

            switch (action){
                case 1:
                    //store.buyAnimal(player);
                    return true;

                case 2:
                    //store.buyFood(player);
                    return true;

                case 3:
                    //food.feedAnimal(player, player.getAnimalList(), player.getFoodList());
                    return true;

                case 6:
                    PlayerCAM.printPlayerInfo(player);
                    //InputAndOutputFunctions.pressEnterToContinue();

                default:
                    GameCAM.printMainMenu(player);
                    actionOfPlayer(player);

            }

        }


    }




}

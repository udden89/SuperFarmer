package game;

import animals.Animal;
import player.Player;
import store.Store;

import java.io.Serializable;
import java.util.ArrayList;

public class WhatToSave implements Serializable {

    // All the players of the game
    ArrayList<Player> players;
    int whoseTurnInIndex;

    //Start variables
    int gameRounds;
    int howManyPlayers;

    WhatToSave(){

        this.players = Game.players;
        this.gameRounds = Game.gameRounds;
        this.howManyPlayers = Game.howManyPlayers;
        this.whoseTurnInIndex = Game.whoseTurnInIndex;
    }

    public void loadGame(){

        Store.addAllItemsToStore();
        Animal.addAllAnimalTypes();

        Game.players = this.players;
        Game.gameRounds = this.gameRounds;
        Game.howManyPlayers = this.howManyPlayers;
        Game.whoseTurnInIndex = this.whoseTurnInIndex;
    }

}
